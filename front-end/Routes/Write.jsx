import Navbar from "../components/Navbar";
import styled from 'styled-components'
import { RiNumber1 ,RiNumber2 } from "react-icons/ri";
import { useForm } from "react-hook-form";
import OutlinedInput from '@mui/material/OutlinedInput';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { useState } from "react";
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import colorSyntax from '@toast-ui/editor-plugin-color-syntax';
import "tui-color-picker/dist/tui-color-picker.css";
import "@toast-ui/editor-plugin-color-syntax/dist/toastui-editor-plugin-color-syntax.css";
import "@toast-ui/editor/dist/i18n/ko-kr";
import { Link } from "react-router-dom";


const WriteDiv= styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width:100%;
  height:100%;
  margin: 30px;
`
const WriteTitle = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px 0px;
  width:100%;
  height:100px;
  font-size: 24px;
  font-weight: 600;
  text-decoration-line: underline;
  text-decoration-style: dashed;
  text-underline-position : under;
`
const Form = styled.form`
  display: flex;
  margin-left: 70px;
  margin-bottom: 30px;
  font-weight: 100;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  input,select{
    font-size: 18px;
    cursor: pointer;
    width: 400px;
    height: 60px;
    margin: 10px 0px 15px 0px;
    border: 1px solid "#fd8f8c";
    color: "#fd8f8c";
    border-radius: 10px;
    text-align: center;
    font-weight: 100;
    color: "#fd8f8c";
    :focus {
      font-weight: bolder;
      transform: scale(1.09);
    }
  }
`;
const SubmitBtn = styled.input`
  :hover{
    transform: scale(1.05)
    
  }
`
const GoHomeBtn = styled.div`
  display: flex;
  justify-content: center;
  font-size: 18px;
  padding: 1px 6px;
  margin: 10px 0px 15px 0px;
  align-items: center;
  width:150px;
  height: 70px;
  border: 1px solid black;
  border-radius: 10px;
  :hover{
    transform: scale(1.05)
  }
`
const BtnDiv = styled.div`
  display: flex;
  align-items: center;
`

const SmallTitle = styled.div`
  font-size: 20px;
  font-weight: bold;
`

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 400,
    },
  },
};

const skills = [
  "JavaScript",
  "TypeScript",
  "React",
  "Vue",
  "Nodejs",
  "Spring",
  "Java",
  "Nextjs",
  "Nestjs",
  "Express",
  "Go",
  "C",
  "Python",
  "Django",
  "Swift",
  "Kotlin",
  "MySQL",
  "MongoDB",
  "php",
  "GraphQl",
  "Firebase",
  "ReactNative",
  "Unity",
  "Flutter",
  "AWS",
  "Kubernetes",
  "Docker",
  "Git",
  "Figma",
  "Zeplin",
];

function Write() {
  const {
    register,
    handleSubmit,
    reset,
  } = useForm({mode:"onChange"});
  const onSubmitValid=(data)=>{
    Object.assign(data, [skillName])
    reset()
    console.log(data)
  }
  const [skillName, setSkillName] = useState([]);
  const handleChange = (event) => {
    const {
      target: { value },
    } = event;
    setSkillName(
      typeof value === 'string' ? value.split(',') : value,
    );
  };
  return (
    <>
      <Navbar/>
      <WriteDiv>
        <WriteTitle>
          <RiNumber1 style={{backgroundColor:"orangered" ,borderRadius:"50%", width:"30px",height:"30px",padding:"5px", marginRight:"5px"}}/>
          프로젝트 기본 정보를 입력해주세요.
          </WriteTitle>
      </WriteDiv>
      <Form onSubmit={handleSubmit(onSubmitValid)}>
          <SmallTitle>모집 구분</SmallTitle>
          <select {...register("selecttitle")}>
            <option value="none" hidden>프로젝트/스터디</option>
            <option value="프로젝트">프로젝트</option>
            <option value="스터디">스터디</option>
          </select>
          <SmallTitle>모집 인원</SmallTitle>
          <select {...register("selectperson")}>
          <option value="none" hidden>미정 ~ 10명이상</option>
            <option value="1명">1명</option>
            <option value="2명">2명</option>
            <option value="3명">3명</option>
            <option value="4명">4명</option>
            <option value="5명">5명</option>
            <option value="6명">6명</option>
            <option value="7명">7명</option>
            <option value="8명">8명</option>
            <option value="9명">9명</option>
            <option value="10명">10명</option>
            <option value="인원미정">인원미정</option>
          </select>
          <SmallTitle>진행 방식</SmallTitle>
          <select {...register("selectonoff")}>
          <option value="none" hidden>온라인/오프라인</option>
            <option value="온라인">온라인</option>
            <option value="오프라인">오프라인</option>
          </select>
          <SmallTitle>진행 기간</SmallTitle>
          <select {...register("selectperiod")}>
          <option value="none" hidden>기간 미정 ~ 6개월 이상</option>
            <option value="기간미정">기간미정</option>
            <option value="1개월">1개월</option>
            <option value="2개월">2개월</option>
            <option value="3개월">3개월</option>
            <option value="4개월">4개월</option>
            <option value="5개월">5개월</option>
            <option value="6개월 이상">6개월 이상</option>
          </select>
          <SmallTitle>시작 예정일</SmallTitle>
          <input {...register("selectstart")} type="date"/>
          <SmallTitle>연락 방법</SmallTitle>
          <select {...register("selectContact")}>
          <option value="none" hidden>연락방법</option>
            <option value="kakaotalk">카카오톡 오픈채팅</option>
            <option value="email">Email</option>
            <option value="GoogleForm">GoogleForm</option>
          </select>
          <WriteTitle>
          <RiNumber2 style={{backgroundColor:"orangered" ,borderRadius:"50%", width:"30px",height:"30px",padding:"5px", marginRight:"5px"}}/>
          프로젝트에 대해 소개해주세요.
          </WriteTitle>
          <SmallTitle>제목</SmallTitle>
          <input {...register("selectname")} type="text" placeholder="글 제목을 입력해주세요"/>
          <SmallTitle>기술 스택</SmallTitle>
          <FormControl sx={{ m: 1, width: 400, mt: 3 }}>
            <Select
              multiple
              displayEmpty
              value={skillName}
              onChange={handleChange}
              input={<OutlinedInput />}
              renderValue={(selected) => {
                if (selected.length === 0) {
                  return <em>기술 스택을 선택해주세요</em>;
                }
                return selected.join(', ');
              }}
              MenuProps={MenuProps}
              inputProps={{ 'aria-label': 'Without label' }}
            >
              <MenuItem disabled value="">
                <em>기술 스택</em>
              </MenuItem>
              {skills.map((skill) => (
                <MenuItem
                  key={skill}
                  value={skill}
                >
                  {skill}
                </MenuItem>
              ))}
            </Select>
          </FormControl>
          <Editor
            usageStatistics= {false}
            initialValue="안녕하세요 Oraganization 입니다! 프로젝트에 대해 소개해주세요~!"
            previewStyle="vertical"
            width= "100%"
            height="700px"
            initialEditType="wysiwyg"
            useCommandShortcut={false}
            plugins={[colorSyntax]}
            language="ko-KR"/>
          <BtnDiv>
            <SubmitBtn type="submit" value="글 등록" style={{ fontWeight: "bolder", width: "150px", height:"70px" ,marginRight:"10px"}}/>
            <Link to={{pathname:"/"}}>
              <GoHomeBtn>취소</GoHomeBtn>
            </Link>
          </BtnDiv>
          </Form>
    </>
  )
}

export default Write;
