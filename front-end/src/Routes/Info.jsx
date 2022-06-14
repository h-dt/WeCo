import { useLocation } from "react-router-dom"
import Navbar from "../components/Navbar";
import styled from "styled-components";
import { useForm } from "react-hook-form";
import { ProjectUnderLine } from "../components/DivStyle/Divstyle";
import { useState } from "react";

const InfoDiv =styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;
    margin: 50px;
    padding:50px;
`
const InfoTitleDiv =styled.div`
    display: flex;
    
`

const InfoSubTitleDivSeperate =styled.div`
    display: flex;
    margin:20px;
`
const InfoSubTitleDiv= styled.div`
    display:flex;
    flex-wrap: wrap;
    justify-content: center;
    grid:10px;
    color:${(props)=>props.theme.fontColor};
    font-size: 24px;
`

const InfoTitle = styled.div`
    font-size: 60px;
    color:${(props)=>props.theme.fontColor};
    font-weight: bold;
    margin-bottom: 50px;
    background-color: ${(props)=>props.theme.infoBgColor};
`

const InfoTitleWrite = styled.div`
    font-size: 32px;
    text-align: center;
    margin: 0px 20px;
    color:#9c9c9e;
`

const InfoSubListTitle = styled.div`
    color: grey;
    margin: 10px;
`

const InfoSubListObject = styled.div`
    font-weight: bold;
    margin:10px;
    color:${(props)=>props.theme.fontColor};
`

const InfoExplanationDiv = styled.div`
    display: flex;
    justify-content: center;
    width:80%;
    flex-direction: column;
    align-items: center;
`

const InfoExplanationTitle =styled.div`
    font-size:36px;
    margin-bottom: 20px;
    font-weight: bold;
    color:${(props)=>props.theme.fontColor};
    background-color: ${(props)=>props.theme.infoBgColor};
`

const InfoExplanationSubtitle=styled.div`
    font-size: 20px;
    color:${(props)=>props.theme.fontColor};
    line-height: 60px;
`

const InfoCommentForm = styled.form`
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin-bottom: 50px;
`

const InfoCommentInputText = styled.input`
    border-radius: 20px;
    padding: 20px;
    width:800px;
    height: 150px;
    font-size: 24px;
    margin : 20px 0px;
`
const InfoCommentInputSubmit =styled.input`
    display: flex;
    justify-content: center;
    font-size: 18px;
    padding: 1px 6px;
    margin: 25px 0px 15px 0px;
    align-items: center;
    width:150px;
    height: 70px;
    border: 1px solid #dcdde1;
    border-radius: 10px;
    cursor: pointer;
    background-color: ${(props)=>props.theme.bgColor};
    :hover{
        transform: scale(1.05);
        background-color: ${(props)=>props.theme.underLineColor};
        color:${(props)=>props.theme.boxFontHoverColor}
}
`

const CommentList =styled.div`
    width:1000px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    
`

const CommentListitem= styled.div`
    font-size: 24px;
`

export function Info(){
    const {
        register,
        handleSubmit,
        reset,
      } = useForm({mode:"onChange"});
    const [commentlist,setCommentlist] = useState([]);
    const onSubmitValid=(data)=>{
        setCommentlist([...commentlist,data])
        reset()
    }
    const location = useLocation();
    const projectlist = location.state?.project
    const studylist = location.state?.study
    const listdata = projectlist=== undefined? studylist : projectlist;
    return (
        <>
            <Navbar/>
            <InfoDiv>
                <InfoTitle>{`${listdata.type} : ${listdata.title}`}</InfoTitle>
                <InfoTitleDiv>
                    <InfoTitleWrite>{`작성자 : ${listdata.write} `}</InfoTitleWrite>
                    <InfoTitleWrite>{`시작일 : ${listdata.year}`}</InfoTitleWrite>
                </InfoTitleDiv>
                <ProjectUnderLine style={{margin:"60px 0px 40px 0px"}}/>
                <InfoExplanationTitle>프로젝트 내용</InfoExplanationTitle>
                <InfoSubTitleDiv>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>모집 구문</InfoSubListTitle>
                        <InfoSubListObject>{listdata.type}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>진행 방식</InfoSubListTitle>
                        <InfoSubListObject>{listdata.tag[0].slice(1)}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>모집 인원</InfoSubListTitle>
                        <InfoSubListObject>{listdata.tag[1].slice(1)}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>시작 예정</InfoSubListTitle>
                        <InfoSubListObject>{listdata.year}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>연락 방법</InfoSubListTitle>
                        <InfoSubListObject>{listdata.contact}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>예상 기간</InfoSubListTitle>
                        <InfoSubListObject>{listdata.tag[2].slice(1)}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                    <InfoSubTitleDivSeperate>
                        <InfoSubListTitle>사용 언어</InfoSubListTitle>
                        <InfoSubListObject>{listdata.skills.join(",")}</InfoSubListObject>
                    </InfoSubTitleDivSeperate>
                </InfoSubTitleDiv>
                <ProjectUnderLine style={{margin:"60px 0px 40px 0px"}}/>
                <InfoExplanationDiv>
                    <InfoExplanationTitle>프로젝트 소개</InfoExplanationTitle>
                    <InfoExplanationSubtitle>{listdata.explan}</InfoExplanationSubtitle>
                </InfoExplanationDiv>
                <ProjectUnderLine style={{margin:"60px 0px 40px 0px"}}/>
                <InfoExplanationDiv>
                    <InfoExplanationTitle>댓글</InfoExplanationTitle>                   
                    <InfoCommentForm onSubmit={handleSubmit(onSubmitValid)}>
                        <InfoCommentInputText {...register('comment' , {
                            required: "댓글을 입력해주세요",
                        })}
                        type="text"
                        placeholder="댓글을 입력해주세요"
                        />
                        <InfoCommentInputSubmit type="submit" value="댓글등록"/>
                    </InfoCommentForm>
                    <CommentList>{commentlist.map((x)=>(
                                <CommentListitem key={Math.random()}>{x.comment}
                                <ProjectUnderLine style={{width:"100%", margin:"20px 0px 20px 0px"}}/>
                                </CommentListitem>
                    )
                        )}</CommentList>
                </InfoExplanationDiv>
            </InfoDiv>
        </>
        
    )


}
