package com.dreamteam.hola.config.auth;

import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

//시큐리티 설정에서 loginProcessingUrl("/login")
//login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 되어있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
@Log4j2
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;
    //시큐리티 session(Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("PrincipalDetailsService.loadUserByUsername");
        log.info("LOGIN");

        Member member = memberMapper.findByUsername(username);
        if (member != null) {
            log.info("findByUsername쿼리 날라가?");
            return new PrincipalDetails(member);

        }

        return null;
        }
    public int signInUser(PrincipalDetails user){
        log.info("singInUser");
        if(memberMapper.findByUsername(user.getUsername()) == null){
            return memberMapper.joinMember(user.getMember());
        }else {
            return -1;
        }
    }


    }

