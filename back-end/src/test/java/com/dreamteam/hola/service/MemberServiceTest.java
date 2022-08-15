//package com.dreamteam.hola.service;
//
//import com.dreamteam.hola.config.SecurityConfiguration;
//import com.dreamteam.hola.config.auth.PrincipalDetails;
//import com.dreamteam.hola.config.auth.PrincipalDetailsService;
//import com.dreamteam.hola.dao.MemberMapper;
//import com.dreamteam.hola.domain.Role;
//import com.dreamteam.hola.dto.MemberDto;
//import com.dreamteam.hola.util.jwt.JwtTokenProvider;
//import net.bytebuddy.utility.dispatcher.JavaDispatcher;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@RunWith(SpringRunner.class)
//@Transactional
//@AutoConfigureMockMvc
//public class MemberServiceTest {
//
//    @Autowired
//    private MemberService memberService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Value("${file.upload.location}")
//    private String fileDir;
//
//    @BeforeEach
//    void setUp(){
//        PrincipalDetails principalDetails = new PrincipalDetails();
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.setAuthentication(new UsernamePasswordAuthenticationToken(principalDetails.getPassword(),principalDetails.getAuthorities()));
//    }
//
//
//    @Test
//    @DisplayName("회원가입 테스트")
//    public void testSignUp() throws IOException {
//
//
//        //given
//        MemberDto memberDto = MemberDto.builder()
//                .memberId(4L)
//                .username("jyyoun1022")
//                .password("yjy@@48912891")
//                .email("jyyoun1022@gmail.com")
//                .profileImage("defaut Image")
//                .role(Role.ROLE_ADMIN)
//                .socialType("null")
//                .build();
//
//
//        MockMultipartFile image = new MockMultipartFile("files", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());
//        //when
//        memberService.signup(memberDto, image);//memberDto,MultipartFile
//
//        //then
//        Assertions.assertThat(memberDto.getNickname()).isEqualTo("codeJ");
//    }
//
//    @Test
//    @DisplayName("로그인 테스트 및 프로픽 가져오기")
//    public void testSignIn() throws IOException {
//        //given
//        MemberDto memberDto = MemberDto.builder()
//                .memberId(4L)
//                .("jyyoun1022")
//                .nickname("codeJ")
//                .password("yjy@@48912891")
//                .email("jyyoun1022@gmail.com")
//                .profileImage("defaut Image")
//                .role(Role.ROLE_ADMIN)
//                .socialType("null")
//                .build();
//        MockMultipartFile image = new MockMultipartFile("files", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());
//        memberService.signup(memberDto,image);
//
//        //when
//        memberService.signin(memberDto);
//        MemberDto resultMember = memberService.getProfile(4L);
//
//        //then
//        Assertions.assertThat(memberDto.getNickname()).isNotEmpty().isEqualTo(resultMember.getNickname());
//
//
//    }
//
//    @Test
//    @DisplayName("사용자 업데이트")
//    public void testUpdate() throws IOException {
//        //given
//        //회원가입 할때의 dto
//        MemberDto memberDto = MemberDto.builder()
//                .memberId(4L)
//                .("jyyoun1022")
//                .nickname("codeJ")
//                .password("yjy@@48912891")
//                .email("jyyoun1022@gmail.com")
//                .profileImage("defaut Image")
//                .role(Role.ROLE_ADMIN)
//                .socialType("null")
//                .build();
//        MockMultipartFile image = new MockMultipartFile("files", "imagefile.jpeg", "image/jpeg", "<<jpeg data>>".getBytes());
//        memberService.signup(memberDto,image);
//
//        //변경될 dto
//        MemberDto updateDto = MemberDto.builder()
//                .memberId(4L)
//                .("jyyoun1022")
//                .nickname("codeK")
//                .password("yjy@@48912891")
//                .email("jyyoun1022@gmail.com")
//                .profileImage("defaut Image")
//                .role(Role.ROLE_ADMIN)
//                .socialType("null")
//                .build();
//
//        //when
//        memberService.update(4L,updateDto,image);
//        //then
//        Assertions.assertThat(memberDto.getUsername()).isEqualTo(updateDto.getUsername());
//
//    }
//
//}
//
