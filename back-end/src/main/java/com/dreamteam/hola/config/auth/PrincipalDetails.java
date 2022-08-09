package com.dreamteam.hola.config.auth;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
public class PrincipalDetails implements UserDetails,OAuth2User{
//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행합니다.
//로그인 진행이 완료가 되면 시큐리티 내장 Session을 만들어줍니다.(Security ContextHolder)<- 여기에 세션정보를 저장
//들어갈수 오브젝트는 Authentication 타입의 객체
//Authentication 안에 Member 정보가 있어야함.
//Member Object의 타입은 UserDetails 타입의 객체여야 합니다.
//Security Session -> Authentication -> UserDetails 타입

    private MemberDto memberDto;//콤포지션
    private Map<String,Object> attributes;

    //일반 로그인
    public PrincipalDetails(MemberDto memberDto){
        this.memberDto = memberDto;
    }

    public PrincipalDetails(MemberDto memberDto, Map<String,Object>attributes){
        this.memberDto = memberDto;
        this.attributes = attributes;
    }


    @Override
    public String getPassword() {
        return memberDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDto.getNickname();
    }

    public Role getRole() {
        return memberDto.getRole();
    }

    //계정이 만료 되었는가?(true : 만료x)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 잠겨있는가?(true : 잠기지x)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호가 만료되었는가?(true : 만료x)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정이 활성화 상태인가?(true : 활성화)
    //만약 1년동안 회원이 로그인을 안하면 휴면 계정으로 하기로함.
    //현재시간 - 마지막로그인시간 -> 1년을 초과하면 return false 로 설정
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 갖고 있는 권한 목록을 리턴합니다.
    //권한이 여러개 있을 수 있어서 루프를 돌아야 한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return memberDto.getRole().toString();
            }
        });
        return collectors;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
