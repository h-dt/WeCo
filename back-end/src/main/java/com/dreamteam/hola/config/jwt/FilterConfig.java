//package com.dreamteam.hola.config.jwt;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    /**
//    직접 만든 Filter 들은 Spring Security Filter 이후에 적용된다.
//     더 앞에서 적용하고 싶다면 Security Config 에서 addFilterBefore()로 적용하면된다.
//     -> http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class);
//     이런식으로 Spring Security에 넣으면 된다.
//     */
//
//    @Bean
//    public FilterRegistrationBean<CustomFilter> filter1(){
//        FilterRegistrationBean<CustomFilter> bean = new FilterRegistrationBean<>(new CustomFilter());
//        bean.addUrlPatterns("/*");//해당 url에 맞는 요청에 Filter가 적용
//        bean.setOrder(0);//숫자가 작은 순서대로 적용된다.
//        return bean;
//    }
//
//}
