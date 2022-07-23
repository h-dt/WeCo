package com.dreamteam.hola.controller.api;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails){
        System.out.println("/test/login ============");
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        System.out.println("authentication = " + principalDetails.getMemberDto());

//        System.out.println("userDatils : "+principalDetails.getMember());


        return "세션 정보 확인하기";
  }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String oAuthLoginTest(Authentication authentication,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("/test/login ============");
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authencation = " + oauth2User.getAttributes());
        log.info("AuthenticationPrincipal.getMember : "+ principalDetails.getMemberDto());

        return "OAuth세션 정보 확인하기";
    }

    @GetMapping("/")
    public  String index(){
        return "/member/index";
    }
    @GetMapping("/user")
    public  @ResponseBody String member(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("authentication = " + principalDetails.getMemberDto());
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
    public  String join(Member member){
        member.setProfileImage("test");
        memberService.joinMember(member);
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
