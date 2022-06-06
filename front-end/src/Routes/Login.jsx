import { Link, useLocation } from "react-router-dom";
import styled from "styled-components";
import Navbar from "../components/Navbar";
import { useForm } from "react-hook-form";
import { useState } from "react";

const LoginForm = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const ModalForm = styled.div`
width: 500px;
height: 800px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 20px;
  border: 1px solid black;
  border-radius: 10px;
`;

const BtnDiv = styled.div`
  width:250px;
  height:100px;
`

const GoogleBtn = styled.div`
  width: 200px;
  height: 70px;
  background-color: transparent;
  background-image: url("img/logofile/googleimage.png");
  background-position: center;
  background-size: cover;
  border-radius: 20px;
  cursor: pointer;
  margin: 10px 20px;
  :hover {
    transform: scale(1.04);
  }
`;

const GithubBtn = styled.div`
  width: 200px;
  height: 70px;
  border-radius: 20px;
  background-image: url("img/logofile/gitimage.png");
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  cursor: pointer;
  margin: 10px 20px;
  
  :hover {
    transform: scale(1.04);
  }
`;
const KaKaoBtn = styled.div`
  width: 200px;
  height: 70px;
  background-image: url("https://i0.wp.com/wjclinic.com/wp-content/uploads/2020/08/png-clipart-kakao-talk-logo-kakaotalk-apple-kakaostory-kakaostyle-kakao-talk-text-computer.png?ssl=1");
  background-position: center;
  background-repeat: none;
  background-size: cover;
  border-radius: 20px;
  font-size: 16px;
  cursor: pointer;
  margin: 10px 20px;
  :hover {
    transform: scale(1.04);
  }
`;


const Form = styled.form`
  display: flex;
  font-weight: 100;
  justify-content: center;
  height: 300px;
  align-items: center;
  flex-direction: column;
  input {
    font-size: 18px;
    cursor: pointer;
    width: 250px;
    height: 50px;
    margin: 5px;
    border: 1px solid "#fd8f8c";
    color: "#fd8f8c";
    border-radius: 10px;
    text-align: center;
    font-weight: 100;
    color: "#fd8f8c";
    :focus {
      color: "#fd8f8c";
      font-weight: bolder;
      transform: scale(1.09);
      background-color: "#fd8f8c";
    }
  }
  span {
    color: red;
  }
`;
const SubmitBtn = styled.input`
  color:  "#fd8f8c";
  :hover{
    transform: scale(1.1);
    background-color:#fd8f8c;
  }
`
const LoginImg = styled.div`
  display: flex;
  justify-content: center;
  width:450px;
  height:100px;
  cursor: pointer;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("img/logofile/facebook_cover_photo_1.png");
`
const LoginTitle = styled.div`
  margin-top: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 25px;
  font-weight: 600;
`

function Login() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({mode:"onChange"});
  const [login, setLogin] = useState("")
  const onSubmitValid=(data)=>{
    setLogin(data)
    reset()
  }
  return (
    <>
      <Navbar/>
      <LoginTitle>Organiztion에 오신 것을 환영합니다!</LoginTitle>
        <LoginForm>
        <ModalForm>
          <BtnDiv>
            <GoogleBtn/>
          </BtnDiv>
          <h1>Google 로그인</h1>  
          <BtnDiv>
            <GithubBtn/>
          </BtnDiv>
          <h1>Github 로그인</h1>        
          <BtnDiv>
            <KaKaoBtn/>
          </BtnDiv>
          <h1>KaKao 로그인</h1>        
          <LoginImg/>
          <Form onSubmit={handleSubmit(onSubmitValid)}>
              <input
              {...register("username", {
                 required: "아이디를 입력해주세요",
                })}
                type="text"
                placeholder="아이디를 입력해주세요"
              />
              <span>{errors.username?.message}</span>
              <input
              {...register("password", {
              required: "비밀번호를 입력해주세요",
              })}
              type="password"
              placeholder="비밀번호를 입력해주세요"
              />
              <span>{errors.password?.message}</span>
              <SubmitBtn type="submit" value="로그인" style={{ fontWeight: "bolder" }}/>
          </Form>
        </ModalForm>
        </LoginForm>
    </>
  )
}

export default Login;
