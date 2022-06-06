import { Link, useLocation } from "react-router-dom";
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

const SmallNav = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0px;
`;

const Btn = styled.button`
  width: 150px;
  height: 50px;
  background-color: white;
  border-radius: 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  margin: 10px 20px;
  border: 1px solid black;
  :hover {
    transform: scale(1.04);
    background-color: #FE938B;
  }
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
const SignImg = styled.div`
  display: flex;
  justify-content: center;
  width:250px;
  height:150px;
  cursor: pointer;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("img/logofile/facebook_cover_photo_1.png");
`

const SignTitle = styled.div`
  margin-top: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 40px;
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
    <SignTitle>회원 가입</SignTitle>
      <SignForm>
        <ModalForm>
          <SignImg/>
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
