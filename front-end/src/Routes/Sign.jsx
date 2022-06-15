import styled from "styled-components";
import Navbar from "../components/Navbar";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { LoginForm ,LoginModalForm,LoginInputForm} from "../components/Formstyle/Formstyle";
import {LoginSubmitBtn} from '../components/Btnstyle/Btnstyle'
import { LoginTitle,SmallLoginTitle } from "../components/Titlestyle/Titlestyle";

const SignLogo = styled.div`
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
  margin: 30px 0px 10px 0px;
  @keyframes textclip {
  to {
    background-position: 200% center;
  }
}
`

function Sign() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({mode:"onChange"});
  const [sign, setSign] = useState("")
  const onSubmitValid=(data)=>{
    setSign(data)
    reset()
  }
  return (
    <>
    <Navbar/>
    <LoginTitle>
    Organiztion에 오신 것을 환영합니다!
    <SmallLoginTitle>(회원가입)</SmallLoginTitle>
    </LoginTitle>
      <LoginForm>
        <LoginModalForm>
        <SignLogo>Organization</SignLogo>
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
              <input
              {...register("passwordConfirm", {
              required: "비밀번호 한번 더  입력해주세요",
              })}
              type="password"
              placeholder="비밀번호를 한번 더 입력해주세요"
              />
              <span>{errors.passwordConfirm?.message}</span>
              <LoginSubmitBtn type="submit" value="회원가입" style={{ fontWeight: "bolder" }}/>
          </LoginInputForm>
        </LoginModalForm>
      </LoginForm>
  </>
  )
}

export default Sign;
