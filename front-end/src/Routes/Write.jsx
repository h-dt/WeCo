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
import { WriteDiv } from "../components/DivStyle/Divstyle";
import { WriteTitle } from "../components/Titlestyle/Titlestyle";
import { WriteForm } from "../components/Formstyle/Formstyle";
import { LoginSubmitBtn } from "../components/Btnstyle/Btnstyle";
import { GoHomeBtn } from "../components/Btnstyle/Btnstyle";
import { WriteBtnDiv } from "../components/DivStyle/Divstyle";
import skills from '../components/data/skillList'

const SmallTitle = styled.div`
  font-size: 20px;
  display: flex;
  margin-bottom: 20px;
  width: 30%;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  color: ${(props)=>props.theme.fonColor};
  font-weight: bold;
`

const ColumnDiv =styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width:90%;
  z-index: 2;
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
          <RiNumber1 style={{backgroundColor:"orangered" ,borderRadius:"50%", width:"30px",height:"30px",padding:"5px", marginRight:"10px"}}/>
          프로젝트 기본 정보를 입력해주세요.
          </WriteTitle>
          <WriteForm onSubmit={handleSubmit(onSubmitValid)}>
            <SmallTitle>모집 구분
                <select {...register("selecttitle")}>
                <option value="none" hidden>프로젝트/스터디</option>
                <option value="프로젝트">프로젝트</option>
                <option value="스터디">스터디</option>
              </select>
            </SmallTitle>
            <SmallTitle>모집 인원
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
            </SmallTitle>
            <SmallTitle>진행 방식
              <select {...register("selectonoff")}>
              <option value="none" hidden>온라인/오프라인</option>
                <option value="온라인">온라인</option>
                <option value="오프라인">오프라인</option>
              </select>
            </SmallTitle>
            <SmallTitle>진행 기간
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
            </SmallTitle>
            <SmallTitle>시작 예정일
              <input {...register("selectstart")} type="date"/>
            </SmallTitle>
            <SmallTitle>연락 방법
              <select {...register("selectContact")}>
              <option value="none" hidden>연락방법</option>
                <option value="kakaotalk">카카오톡 오픈채팅</option>
                <option value="email">Email</option>
                <option value="GoogleForm">GoogleForm</option>
              </select>
            </SmallTitle>
            <SmallTitle>기술 스택
              <FormControl sx={{ m: 1, width: "100%", mt: 3 }}>
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
            </SmallTitle>
            <SmallTitle>오픈 방 링크
              <input {...register("selectlink")} type="text" placeholder="링크를 입력해주세요"/>
            </SmallTitle>
          <ColumnDiv>
            <WriteTitle>
            <RiNumber2 style={{backgroundColor:"orangered" ,borderRadius:"50%", width:"30px",height:"30px",padding:"5px", marginRight:"10px"}}/>
            프로젝트에 대해 소개해주세요.
            </WriteTitle>
            <SmallTitle>제목
              <input {...register("selectname")} type="text" placeholder="글 제목을 입력해주세요"/>
            </SmallTitle>
            <Editor
              usageStatistics= {false}
              initialValue=" "
              previewStyle="vertical"
              width= "100%"
              height="700px"
              initialEditType="wysiwyg"
              useCommandShortcut={false}
              plugins={[colorSyntax]}
              language="ko-KR"/>
            <WriteBtnDiv>
              <LoginSubmitBtn type="submit" value="글 등록" style={{ fontWeight: "bolder", width: "150px", height:"70px" ,margin: "37px 20px 20px 20px"}}/>
                <GoHomeBtn>
                  <Link to={{pathname:"/"}} style={{textAlign:"center"}}>
                    취소
                  </Link>
                </GoHomeBtn>
            </WriteBtnDiv>
          </ColumnDiv>
          </WriteForm>
      </WriteDiv>
    </>
  )
}

export default Write;
