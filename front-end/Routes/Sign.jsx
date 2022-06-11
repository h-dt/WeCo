import styled from "styled-components";
import Navbar from "../components/Navbar";
import { useForm } from "react-hook-form";
import { useState } from "react";

const SignForm = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`

const ModalForm = styled.div`
width: 500px;
height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 20px;
  border: 1px solid black;
  border-radius: 10px;
`;

const Form = styled.form`
  display: flex;
  font-weight: 100;
  justify-content: center;
  height: 100%;
  align-items: center;
  flex-direction: column;
  input {
    font-size: 18px;
    cursor: pointer;
    margin: 10px;
    width: 250px;
    height: 50px;
    margin: 5px;
    border-radius: 10px;
    text-align: center;
    font-weight: 100;
    :focus {
      font-weight: bolder;
      transform: scale(1.09);
    }
  }
  span {
    color: red;
  }
`;
const SubmitBtn = styled.input`
  :hover{
    transform: scale(1.1);
  }
`

const SignTitle = styled.div`
  margin-top: 50px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  font-size: 25px;
  font-weight: 600;
`
const SmallSignTitle = styled.div`
   font-size: 20px;
  font-weight: 600;
  margin-top: 20px;
`

function Sign() {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm({mode:"onChange"});
  const [sign, setSign] = useState("")
  console.log(sign)
  const onSubmitValid=(data)=>{
    setSign(data)
    reset()
  }
  return (
    <>
    <Navbar/>
    <SignTitle>
    Organiztion에 오신 것을 환영합니다!
    <SmallSignTitle>(회원가입)</SmallSignTitle>
    </SignTitle>
    
      <SignForm>
        <ModalForm>
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
              <input
              {...register("passwordConfirm", {
              required: "비밀번호 한번 더  입력해주세요",
              })}
              type="password"
              placeholder="비밀번호를 한번 더 입력해주세요"
              />
              <span>{errors.passwordConfirm?.message}</span>
              <SubmitBtn type="submit" value="회원가입" style={{ fontWeight: "bolder" }}/>
          </Form>
        </ModalForm>
      </SignForm>
  </>
  )
}

export default Sign;
