package com.dreamteam.hola.config.auth;

import com.dreamteam.hola.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class PrincipalDetail implements UserDetails {

    private Member member;

    public PrincipalDetail(Member member){
        this.member = member;
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }
    //계정이 만료되 었는가?(true : 만료x)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 잠겨있는가?(true : 잠기지x)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호가 만료되었는가?(true : 만료x)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정이 활성화 상태인가?(true : 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
    //계정이 갖고 있는 권한 목록을 리턴합니다.
    //권한이 여러개 있을 수 있어서 루프를 돌아야 한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(()->
             "ROLE_"+member.getRole()
        );
        return collectors;
    }

}
