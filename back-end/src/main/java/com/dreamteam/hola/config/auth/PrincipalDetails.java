package com.dreamteam.hola.config.auth;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.member.MemberInfoDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Log4j2
@Data
@NoArgsConstructor
public class PrincipalDetails implements UserDetails,OAuth2User{

    private MemberInfoDto memberDto;
    private Map<String,Object> attributes;


    public PrincipalDetails(MemberInfoDto memberDto){
        this.memberDto = memberDto;
    }

    public PrincipalDetails(MemberInfoDto memberDto, Map<String,Object>attributes){
        this.memberDto = memberDto;
        this.attributes = attributes;
    }

    @Override
    public String getPassword() {
        return memberDto.getPassword();
    }

    @Override
    public String getUsername() {
        return memberDto.getEmail();
    }

    public Role getRole() {
        return memberDto.getRole();
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                log.info(memberDto.getRole());
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
