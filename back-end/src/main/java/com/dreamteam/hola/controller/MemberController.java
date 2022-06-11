package com.dreamteam.hola.controller;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
//    private final BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public  String index(){
        return "/member/index";
    }
    @GetMapping("/user")
    public  @ResponseBody String member(){
        return "user";
    }
    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/loginForm")
    public String login(){

        return "/member/login";
    }

    @GetMapping("/member/join")
    public  void join(){

    }
    @PostMapping("/member/join")
    public  String join(MemberDto memberDto){
        memberDto.setProfileImage("test");
        memberService.joinMember(memberDto);
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")//    1개만 걸고 싶을떄!
    @GetMapping("/info")
    public @ResponseBody String info(){

        return "개인정보";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")  //"hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){

        return "데이터정보";

    }

}
