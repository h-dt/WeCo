import styled from "styled-components";
import Navbar from "../components/Navbar";
import { useForm } from "react-hook-form";
import { useState } from "react";
import { LoginForm ,LoginModalForm} from "../components/Formstyle/Formstyle";
import {LoginSubmitBtn} from '../components/Btnstyle/Btnstyle'
import { InputTitle } from "../components/Titlestyle/Titlestyle";
import { Inputs } from "../components/Inputstyle/Inputstyle";

const SignLogo = styled.div`
    margin: 30px 0px 10px 0px;
    text-align: center;
    font-size: 55px;
    height:70px;
    font-weight: 600;
`

const SignInputForm =styled.form`
   display: flex;
  font-weight: 100;
  height: 500px;
  color: ${(props)=>props.theme.fontColor};
  margin:20px;
  padding:30px;
  flex-direction: column;
  span {
    font-weight: bold;
    font-size:18px;
    color:${(props)=>props.theme.reset};
    display:flex;
    justify-content: center;
  }
`

function Sign() {
  const {
    register,
    handleSubmit,
    reset,
    setError,
    formState: { errors },
  } = useForm({mode:"onChange"});
  const [sign, setSign] = useState("")
  console.log(sign)
  const onSubmitValid=(data)=>{
    setSign(data)
    reset()
  }
  const [password, setPassword] = useState();
  const [confirm, setConfirm] = useState();
  const notSame = password !== confirm
  return (
    <>
    <Navbar/>
      <LoginForm>
        <LoginModalForm>
        <SignLogo>Sign</SignLogo>
          <SignInputForm onSubmit={handleSubmit(onSubmitValid)}>
          <InputTitle>Username</InputTitle>
              <Inputs
              {...register("username", {
                 required: "Usename 을 반드시 입력해주세요",
                })}
                type="text"
                placeholder="Type your Username"
              />
              <span>{errors.username?.message}</span>
              <InputTitle>Password</InputTitle>
              <Inputs
              {...register("password", {
              required: "Password 를 반드시 입력해주세요",
              
              })}
              name="password"
              type="password"
              onChange={(event)=>setPassword(event.target.value)}
              placeholder="Type your Password"
              />
              <span>{errors.password?.message}</span>
              <InputTitle>Password Confirm</InputTitle>
              <Inputs
              {...register("passwordConfirm", {
              required: "Password 를 한번 더 입력해주세요",
              
              })}
              name="passwordConfirm"
              type="password"
              onChange={(event)=>setConfirm(event.target.value)}
              placeholder="Please enter your password again"
              />
              <span>{errors.passwordConfirm?.message}</span>
              <span>{notSame ? "비밀번호가 서로 일치하지 않습니다" : ""}</span>
              <LoginSubmitBtn type="submit" value="로그인" style={{ fontWeight: "bolder" }}/>   
          </SignInputForm>
        </LoginModalForm>
      </LoginForm>
  </>
  )
}

export default Sign;
