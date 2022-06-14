import Navbar from "../components/Navbar";
import { useForm } from "react-hook-form";
import styled from "styled-components";
import { useState } from "react";
import { LoginForm,LoginModalForm } from "../components/Formstyle/Formstyle";
import { LoginToggleBtn,GoogleBtn,GithubBtn,KaKaoBtn,LoginSubmitBtn } from "../components/Btnstyle/Btnstyle";
import {LoginToggleDiv,LoginBtnDiv} from '../components/DivStyle/Divstyle'
import { SmallLoginTitle,SmallTitle,LoginTitle } from "../components/Titlestyle/Titlestyle";
import {LoginInputForm} from '../components/Formstyle/Formstyle'

const LoginLogo =styled.div`
 text-transform: uppercase;
 background-image: ${(props)=>props.theme.logo};
  background-size: auto auto;
  background-clip: border-box;
  background-size: 200% auto;
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  display: inline-block;
  font-size: 55px;
  
  @keyframes textclip {
  to {
    background-position: 200% center;
  }
}
`

function Login() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({mode:"onChange"});
  const [login, setLogin] = useState("")
  const [logintoggle,setLoginToggle] =useState(false);
  const onSubmitValid=(data)=>{
    setLogin(data)
    reset()
  }
  const onToggle = (boolean) =>{
    setLoginToggle(boolean)
  }
  return (
    <>
      <Navbar/>
      <LoginTitle>Organiztion에 오신 것을 환영합니다!
      <SmallLoginTitle>(로그인)</SmallLoginTitle>
      </LoginTitle>
        <LoginForm>
        <LoginModalForm>
          <LoginToggleDiv>
            <LoginToggleBtn onClick={()=>onToggle(true)}>
              소셜 계정으로 로그인하기
              {logintoggle ? <div/> : null}
            </LoginToggleBtn>
            <LoginToggleBtn onClick={()=>onToggle(false)}>
              ID/PW 으로 로그인하기
              {logintoggle ? null:  <div/> }
            </LoginToggleBtn>
          </LoginToggleDiv>
          {logintoggle ? 
          <>
            <LoginBtnDiv style={{backgroundColor:"white"}}>
              <GoogleBtn/>
            </LoginBtnDiv>
            <SmallTitle>Google 로그인</SmallTitle>  
            <LoginBtnDiv style={{backgroundColor:"#1B1F23"}}>
              <GithubBtn/>
            </LoginBtnDiv>
            <SmallTitle>Github 로그인</SmallTitle>        
            <LoginBtnDiv  style={{backgroundColor:"#FAE300"}}>
              <KaKaoBtn/>
            </LoginBtnDiv>
            <SmallTitle>KaKao 로그인</SmallTitle>        
          </> : 
          <>
          <LoginLogo>Organization</LoginLogo> 
          <LoginInputForm onSubmit={handleSubmit(onSubmitValid)}>
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
              <LoginSubmitBtn type="submit" value="로그인" style={{ fontWeight: "bolder" }}/>
          </LoginInputForm>
          </>}
        </LoginModalForm>
        </LoginForm>
    </>
  )
}

export default Login;
