package com.dreamteam.hola.service;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.auth.PrincipalDetailsService;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import com.dreamteam.hola.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Value("${file.upload.location}")
    private String fileDir;

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;




    @Override
    @Transactional
    public void joinMember(Member member) {

    }

    @Override
    @Transactional
    public boolean signup(MemberDto memberDto,MultipartFile multipartFile) throws IOException {
        log.info("Call Service SignUp");

        memberDto.setRole(Role.ROLE_USER);
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        log.info("multipart ={}",multipartFile);
        String uuid = UUID.randomUUID().toString();
        String originalFilename = multipartFile.getOriginalFilename();
        String imageFileName = uuid + "_" + originalFilename.substring(originalFilename.lastIndexOf("."));

        Path imageFilePath =  Paths.get(fileDir + "/"+imageFileName);

        File file = new File("");
        if(!new File(fileDir).exists()){
            new File(fileDir).mkdirs();
        }

        if(!multipartFile.isEmpty()) {
            memberDto.setProfileImage(imageFilePath.toString());
            multipartFile.transferTo(new File(imageFilePath.toString()));
        }

        try{
            return memberMapper.signup(memberDto) == 1;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Token signin(MemberDto memberDto) {
        PrincipalDetails findMember = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberDto.getUsername());
        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword())) {
            return new Token("access token create fail", "refresh token create fail");
        }
        return jwtTokenProvider.createtoken(findMember.getUsername(), findMember.getRole());
    }

    @Override
    public MemberDto getProfile(Long id) {
        MemberDto member = memberMapper.findById(id);
        return member;
    }

    @Override
    public void update(Long id,MemberDto memberDto, MultipartFile multipartFile) throws IOException {

        memberDto.setMemberId(id);
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        String uuid = UUID.randomUUID().toString();
        String originalFilename = multipartFile.getOriginalFilename();
        String imageFileName = uuid + "_" + originalFilename.substring(originalFilename.lastIndexOf("."));

        Path imageFilePath =  Paths.get(fileDir + "/"+imageFileName);

        if(!multipartFile.isEmpty()) {
            memberDto.setProfileImage(imageFilePath.toString());
            multipartFile.transferTo(new File(imageFilePath.toString()));
        }


        memberMapper.update(memberDto);
    }


}
