package com.dreamteam.hola.controller;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public  String index(){
        return "index";
    }
    @GetMapping("/member/user")
    public  @ResponseBody String member(){
        return "user";
    }
    @GetMapping("/member/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/member/login")
    public  void login(){

    }

    @GetMapping("/member/join")
    public  void join(){

    }
    @PostMapping("/member/join")
    public  String join(MemberDto memberDto){
        System.out.println("11111");
        memberDto.setRole(Role.USER);
        memberDto.setProfile_image("test");
        String rawPassword = memberDto.getPassword();
        String encPassword = encoder.encode(rawPassword);
        memberDto.setPassword(encPassword);
        memberService.joinMember(memberDto);
        return "redirect:/";
    }

}
