package com.dreamteam.hola.config.auth;

import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.dto.member.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    /**
     * @param email 로그인 요청 온 email
     * @return Member 정보로 만들어진 Sercurity context에 저장될 UserDetails 객체
     * @throws UsernameNotFoundException 검사한 email이 DB에 존재하지 않는 경우 발생
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberInfoDto memberDto = memberMapper.findByEmail(email);
        if (memberDto != null) {
            return new PrincipalDetails(memberDto);
        }

        return null;
    }
}