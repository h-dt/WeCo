package com.dreamteam.hola.util.jwt;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String) request.getAttribute("exception");

        if(exception == null){
            log.info("error code : {}", exception);
            setResponse(response, ExceptionCode.UNKNOWN_ERROR);
        } else if(exception.equals(ExceptionCode.WRONG_TYPE_TOKEN.getMessage())){
            log.info("error code : {}", exception);
            setResponse(response, ExceptionCode.WRONG_TYPE_TOKEN);
        } else if (exception.equals(ExceptionCode.EXPIRED_TOKEN.getMessage())) {
            log.info("error code : {}", exception);
            setResponse(response, ExceptionCode.EXPIRED_TOKEN);
        } else if (exception.equals(ExceptionCode.UNSUPPORTED_TOKEN.getMessage())) {
            log.info("error code : {}", exception);
            setResponse(response, ExceptionCode.UNSUPPORTED_TOKEN);
        } else {
            log.info("error code : {}", exception);
            setResponse(response, ExceptionCode.ACCESS_DENIED);
        }
    }

    // 한글 출력을 위해 response 생성을 위한 getWriter() 사용
    private void setResponse(HttpServletResponse response, ExceptionCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());
        responseJson.put("code", exceptionCode.getCode());

        response.getWriter().print(responseJson);
    }
}
