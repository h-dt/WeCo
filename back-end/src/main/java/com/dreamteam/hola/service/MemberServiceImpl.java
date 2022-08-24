package com.dreamteam.hola.service;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.auth.PrincipalDetailsService;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.member.MemberDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberUpdateDto;
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

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;
    private final S3FileUploadService s3FileUploadService;

    @Value("${weco.default.profile}")
    private String defaultProfile;


    @Override
    @Transactional
    public boolean signup(MemberDto memberDto) throws IOException {
        log.info("Call Service SignUp");

        memberDto.setRole(Role.ROLE_USER);
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        if(memberDto.getProfileImage()==null){
            memberDto.setProfileImage(defaultProfile);
        }
        return memberMapper.signup(memberDto) == 1;
    }

    @Override
    @Transactional
    public Token signin(MemberLoginDto memberDto) {
        PrincipalDetails findMember = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberDto.getEmail());
        log.info("FIND-MEMBER={}",findMember);
        log.info("FIND-MEMBER={}",findMember.getRole());
        log.info("FIND-MEMBER={}",findMember.getMemberDto());

        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword())) {
            return new Token("access token create fail", "refresh token create fail");
        }
        return jwtTokenProvider.createtoken(findMember.getUsername(), findMember.getRole());
    }

    @Override
    @Transactional
    public boolean updateProfile(Long memberId, MultipartFile profile) throws IOException {
        MemberDto findMember = memberMapper.findById(memberId);

        if(!findMember.getProfileImage().equals(defaultProfile)) {
            log.info("현재 이미지가 기본 이미지가 아니므로 S3 상에서 삭제!!!");
            s3FileUploadService.remove(findMember.getProfileImage());
        }

        if(profile == null){
            log.info("profile이 null이므로 기본 이미지로 setting!!!");
            findMember.setProfileImage(defaultProfile);
        }else {
            log.info("설정된 이미지로 profile setting!!!");
            String uploadPath = s3FileUploadService.upload(profile);
            findMember.setProfileImage(uploadPath);
        }

        memberMapper.updateProfile(findMember);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Long memberId, MemberUpdateDto memberDto){
        MemberDto findMember = memberMapper.findById(memberId);
        findMember.setNickname(memberDto.getNickname());

        return memberMapper.update(findMember) == 1;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return memberMapper.delete(id) == 1;
    }

    @Override
    public MemberDto getLoginMember(Long id) {
        return memberMapper.findById(id);
    }
}
