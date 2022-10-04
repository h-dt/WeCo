package com.dreamteam.hola.config;

import com.dreamteam.hola.dto.board.BoardDetailDto;
import com.dreamteam.hola.dto.board.BoardListDto;
import com.dreamteam.hola.dto.board.RecommendedBoardDto;
import com.dreamteam.hola.dto.comment.CommentDto;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import com.dreamteam.hola.dto.comment.CommentResDto;
import com.dreamteam.hola.dto.comment.CommentUpdateDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dreamteam.hola"))
                .paths(PathSelectors.any())
                .build()
                /** 타입리졸버 설정 */
                .additionalModels(
                typeResolver.resolve(BoardDetailDto.class),
                typeResolver.resolve(ErrorResponse.class),
                typeResolver.resolve(RecommendedBoardDto.class),
                typeResolver.resolve(BoardListDto.class),
                typeResolver.resolve(CommentReqDto.class),
                typeResolver.resolve(CommentUpdateDto.class),
                typeResolver.resolve(CommentDto.class),
                typeResolver.resolve(CommentResDto.class)



        );
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("X-AUTH-TOKEN", authorizationScopes));

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("API")
                .description("API 상세소개 및 사용법")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey(){
        return new ApiKey("X-AUTH-TOKEN", "X-AUTH-TOKEN", "header");
    }
}
