import { Link, useLocation } from "react-router-dom"
import Navbar from "../components/Navbar";
import styled from "styled-components";
import { useForm } from "react-hook-form";
import { ProjectUnderLine } from "../components/DivStyle/Divstyle";
import { useState } from "react";
import { FaArrowLeft } from "react-icons/fa";

const InfoDiv =styled.div`
    display: flex;
    align-items: center;
    flex-direction: column;
    padding: 150px 10% 0px 10%;
    margin : 0 auto;
    @media all and (min-width: 480px) and (max-width: 767px) {
        margin : 0 auto;
        padding: 150px 5% 0px 5%;
    }
    @media all and (max-width: 479px) {
        margin : 0 auto;
        padding: 150px 5% 0px 5%;
    }
`

const InfoSubTitleDivSeperate =styled.div`
    display: flex;
    margin:20px 0px;
    width: 40%;
    @media all and (min-width: 480px) and (max-width: 767px) {
        flex-direction: column;
    }
    @media all and (max-width: 479px) {
        flex-direction: column;
    }
`
const InfoSubTitleDiv= styled.div`
    display:flex;
    flex-wrap: wrap;
    row-gap: 10px;
    color:${(props)=>props.theme.fontColor};
    font-size: 24px;
`

const InfoTitle = styled.div`
    font-size: 60px;
    color:${(props)=>props.theme.fontColor};
    font-weight: bold;
    margin-bottom: 50px;
    @media all and (min-width: 480px) and (max-width: 767px) {
        font-size: 40px;
        line-height: 1.4em;
    }
    @media all and (max-width: 479px) {
        font-size: 40px;
        line-height: 1.4em;
    }
`

const InfoTitleWrite = styled.div`
    font-size: 24px;
    color:#9c9c9e;
    @media all and (min-width: 480px) and (max-width: 767px) {
        font-size: 16px;
        
    }
    @media all and (max-width: 479px) {
        font-size: 16px;
        
    }
`

const InfoSubListTitle = styled.div`
    color: grey;
    width: 40%;
    margin: 10px 0px;
    @media all and (min-width: 480px) and (max-width: 767px) {
        width: 60%;
        font-size: 18px;
    }
    @media all and (max-width: 479px) {
        width: 60%;
        font-size: 18px;
    }
`

const InfoSubListObject = styled.div`
    font-weight: bold;
    margin: 10px 0px;
    width: 60%;
    color:${(props)=>props.theme.fontColor};
    @media all and (min-width: 480px) and (max-width: 767px) {
        line-height: 1.4em;
    }
    @media all and (max-width: 479px) {
        line-height: 1.4em;
        
    }
`

const InfoExplanationDiv = styled.div`
    display: flex;
    justify-content: center;
    width:100%;
    flex-direction: column;
    align-items: center;
`

const InfoExplanationTitle =styled.div`
    font-size:36px;
    margin-bottom: 20px;
    font-weight: bold;
    color:${(props)=>props.theme.fontColor};
    @media all and (min-width: 480px) and (max-width: 767px) {
        font-size: 30px;
        
    }
    @media all and (max-width: 479px) {
        font-size: 30px;
        
    }
`

const InfoExplanationSubtitle=styled.div`
    font-size: 20px;
    color:${(props)=>props.theme.fontColor};
    line-height: 60px;
    @media all and (min-width: 480px) and (max-width: 767px) {
            font-size: 14px;
        
    }
    @media all and (max-width: 479px) {
        font-size: 14px;
        
    }
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
    @media all and (min-width: 480px) and (max-width: 767px) {
        width:350px;
    }
    @media all and (max-width: 479px) {
        width:350px;
    }
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

const LeftDiv =styled.div`
    width: 85%;
    margin-right: 20px;
    @media all and (min-width: 480px) and (max-width: 767px) {
        margin: 0 auto;
    }
    @media all and (max-width: 479px) {
        margin: 0 auto;
    }
`

const RightDiv =styled.div`
    width: 15%;
    display: flex;
    flex-direction: column;
    text-align: center;
    margin: 0 auto;
    @media all and (min-width: 480px) and (max-width: 767px) {
        display: none;
    }
    @media all and (max-width: 479px) {
        display: none;
    }
`

const RightTitle =styled.div`
    font-size: 16px;
    margin-bottom: 10px;
`

const RightTitleBox = styled.div`
    width: 100%;
    height: 300px;
    background-color: grey;
`

const InfoTotalDiv = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
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
            <InfoTotalDiv>
                <LeftDiv>
                    <InfoDiv>
                        <InfoTitle>{listdata.title}</InfoTitle>
                        <InfoTitleWrite>{` ${listdata.write} 〡 ${listdata.year}`}</InfoTitleWrite>
                        <ProjectUnderLine style={{margin:"60px 0px 40px 0px"}}/>
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
                                <InfoSubListObject>{listdata.skills.join("  ")}</InfoSubListObject>
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
                </LeftDiv>
                <RightDiv>
                    <RightTitle>좋아하는 글목록</RightTitle>
                    <RightTitleBox/>
                </RightDiv>
            </InfoTotalDiv>
        </>
        
    )


}
