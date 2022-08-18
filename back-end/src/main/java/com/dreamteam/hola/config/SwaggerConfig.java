package com.dreamteam.hola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo commonInfo(){
        return new ApiInfoBuilder()
                .title("API ")
                .description("API 상세소개 및 사용법")
                .version("1.0")
                .build();
    }


    @Bean
    public Docket needTokenApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder
                .name("X-AUTH-TOKEN")
                .description("JWT Access Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
//                .required(true)
                .build();

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Token Api")
                .globalOperationParameters(parameters)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dreamteam.hola"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(commonInfo());
    }

    @Bean
    public Docket allApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("USER")
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dreamteam.hola"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(commonInfo());
    }
}
