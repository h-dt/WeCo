package com.dreamteam.hola.service;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.auth.PrincipalDetailsService;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.dto.member.MemberInfoDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberSignupDto;
import com.dreamteam.hola.dto.member.MemberUpdateDto;
import com.dreamteam.hola.exception.member.PasswordNotMatchException;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import com.dreamteam.hola.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;
    private final S3FileUploadService s3FileUploadService;

    @Value("${weco.default.profile}")
    private String DEFAULT_PROFILE;

    @Override
    @Transactional
    public void signup(MemberSignupDto requestDto) {
        log.info("Call Service SignUp");

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        memberMapper.signup(requestDto);
    }

    @Override
    @Transactional
    public Token signin(MemberLoginDto memberDto) {
        PrincipalDetails findMember = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberDto.getEmail());
        log.info("FIND-MEMBER={}",findMember);
        log.info("FIND-MEMBER={}",findMember.getRole());
        log.info("FIND-MEMBER={}",findMember.getMemberDto());

        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 올바르지 않습니다.");
        }
        return jwtTokenProvider.createtoken(findMember.getUsername(), findMember.getRole());
    }

    @Override
    @Transactional
    public Long updateProfile(Long memberId, MultipartFile profile) throws IOException {
        MemberInfoDto findMember = memberMapper.findById(memberId);

        if(!findMember.getProfileImage().equals(DEFAULT_PROFILE)) {
            log.info("현재 이미지가 기본 이미지가 아니므로 S3 상에서 삭제!!!");
            s3FileUploadService.remove(findMember.getProfileImage());
        }

        if(profile == null){
            log.info("profile이 null이므로 기본 이미지로 setting!!!");
            findMember.setProfileImage(DEFAULT_PROFILE);
        }else {
            log.info("설정된 이미지로 profile setting!!!");
            String uploadPath = s3FileUploadService.upload(profile);
            findMember.setProfileImage(uploadPath);
        }
        return memberMapper.updateProfile(findMember);
    }

    @Override
    @Transactional
    public Long update(Long memberId, MemberUpdateDto memberDto){
        MemberInfoDto findMember = memberMapper.findById(memberId);
        findMember.setNickname(memberDto.getNickname());

        return memberMapper.update(findMember);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        return memberMapper.delete(id);
    }

    @Override
    public MemberInfoDto getLoginMember(Long id) {
        return memberMapper.findById(id);
    }
}
