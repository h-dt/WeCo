package com.dreamteam.hola.config.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Security Filter Start ");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        /**
         * 토큰 : token 이 필요하다. id/pw 가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들고 반환해줍니다.
         * 클라이언트는 요청할때 마다 header 에 Authorization - value  쌍으로 토큰을 넣으면 됩니다.
         * 이때 토큰이 서버가 갖고있는 토큰인지 검증만 하면 됩니다.
         */
        if(req.getMethod().equals(HttpMethod.POST.name())){
            log.info("POST요청");
            String headerAuth = req.getHeader("Authorization");
            if(headerAuth.equals("token")){
                log.info("CustomFilter");
                log.info(headerAuth);
                filterChain.doFilter(req,res);
            }else {
                log.error("인증 안됨");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
