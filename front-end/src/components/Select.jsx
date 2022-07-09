import { faBorderAll,  faFolderMinus, faMobileScreenButton, faX } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {  useState } from "react";
import { Link } from "react-router-dom";
import dataSkill from './data/dataSkill'
import { SelectDiv,ShowDiv,ShowItemDiv,ResultDiv,ResultSelectBtnDiv,SelectBarDiv,ShowSelectListDiv ,ShowSelectListItemDiv} from "./DivStyle/Divstyle";
import { SelectBtn ,ResetBtn,ResultSelectBtn } from "./Btnstyle/Btnstyle";
import {ResultBox} from "./Boxstyle/Boxstyle"
import { projectData } from "./data/projectData";
import { studyData } from "./data/studyData";
import { AiOutlineEye,AiOutlineComment } from "react-icons/ai";
import { ProjectDiv,ProjectUnderLine,ProjectSkillDiv,ProjectUnderDiv,ProjectSeperate} from "./DivStyle/Divstyle";
import {ProjectDayTitle ,ProjectTitle,ProjectTagTitle} from './Titlestyle/Titlestyle';
import styled from "styled-components";

const LeftDiv= styled.div`
    display:flex;

`

const RightDiv =styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-end;
    font-size: 24px;
    cursor: pointer;
    :hover{
        transform: scale(1.1);
        transform:translateY(-5px);
        transition-duration: 1s;
    }
   @media all and (min-width:480px) and (max-width:767px) {
        margin-top: 30px;
        justify-content: center;
    } 
    @media all and (max-width:479px) {
        margin-top: 30px;
        justify-content: center;
    } 
`


function Select(){
    const [currentTab, setCurrentTab] = useState(0);
    const [selectSubject , setSelectSubject] = useState(0);
    console.log(selectSubject);
    const [all ,setAll] = useState(false);
    const [returnitem, setReturnItem] = useState("");
    const newArray= []
    const onResetBtn = () =>{
        setReturnItem("")
    }
    const onSelectAll = ()=>[
        setSelectSubject(0)
    ]

    const onSelectProject=()=>{
        setSelectSubject(1)
    }

    const onSelectStudy=()=>{
        setSelectSubject(2)
    }
    localStorage.setItem("skill",returnitem);
    const selectMenuHandler = (index) => {
        setCurrentTab(index);
    };
    const newList = dataSkill[currentTab].value;
    const onClick = (data) =>{
       setReturnItem([...returnitem,data])
       const deduplication = returnitem.includes(data);
       if(deduplication){
           setReturnItem([...returnitem])
       }
    }
    const onDelete = (x)=>{
        const deleteItem = returnitem.indexOf(x);
        const cutone =returnitem.slice(0,deleteItem);
        const cuttwo =returnitem.slice(deleteItem+1,returnitem.length)
        newArray.push(...cutone)
        newArray.push(...cuttwo)
        setReturnItem(newArray)
    }
    return (
        <>
        <SelectDiv>
            <SelectBarDiv>
            {dataSkill.map((ele,index)=>{
                return (
                    <SelectBtn key={index} onClick={()=>selectMenuHandler(index)} color={index === currentTab ? "#6190E8": ""} >
                        {ele.name}
                    </SelectBtn>
                )
            })}
            </SelectBarDiv>
            <ShowDiv>
               {newList.map((ele,index)=>{
                   return (
                       <ShowItemDiv  key={index} onClick={()=>onClick(ele)} >
                           {ele}
                       </ShowItemDiv>
                   )
               })}
            </ShowDiv>
            {returnitem === "" ? null  : (
                <>
                    <ShowSelectListDiv>
                        {returnitem === "" ? null : 
                            returnitem.map((x)=> <ShowSelectListItemDiv key={Math.random()} onClick={()=>onDelete(x)}>{x}<FontAwesomeIcon icon={faX} style={{marginLeft:10}}/></ShowSelectListItemDiv>)}
                    </ShowSelectListDiv>
                    <ResetBtn onClick={onResetBtn}>
                        Reset
                    </ResetBtn>
                </>
            )}
        </SelectDiv>
        <ResultDiv>
            <ResultSelectBtnDiv>
                <LeftDiv>
                    <ResultSelectBtn onClick={onSelectAll} select={selectSubject === 0 && "#6190E8"}>
                        <FontAwesomeIcon icon={faBorderAll} style={{marginRight:"10px"}}/>
                        전체 
                    </ResultSelectBtn>
                    <ResultSelectBtn onClick={onSelectProject} select={selectSubject === 1 && "#6190E8"}>
                        <FontAwesomeIcon icon={faFolderMinus} style={{marginRight:"10px"}}/>
                        프로젝트
                    </ResultSelectBtn>
                    <ResultSelectBtn onClick={onSelectStudy} select={selectSubject === 2 && "#6190E8"}>
                        <FontAwesomeIcon icon={faMobileScreenButton} style={{marginRight:"10px"}}/>
                        스터디 
                    </ResultSelectBtn>
                </LeftDiv>
                <RightDiv onClick={()=>setAll(prev=>!prev)}>
                    {all ? "모집중만 보기" : "전체보기"}
                </RightDiv>
            </ResultSelectBtnDiv>
            <ResultBox>
                {selectSubject ===0 ? 
                    <>
                    <ProjectDiv>
                        <h1>전체 내용이 들어가야 합니다</h1>
                    </ProjectDiv>
                    </>
                 : selectSubject ===1 ?   <>
                 {projectData.map((project)=> {
                     return (
                             <ProjectDiv key={Math.random()}>
                                 <Link to={{pathname:`/info/${project.url}`}} state={{project}} key={Math.random()} >
                                     <ProjectDayTitle>
                                         {`시작 예정일 | ${project.year}`}
                                     </ProjectDayTitle>
                                     <ProjectTitle>
                                         {project.title}
                                     </ProjectTitle>
                                     <ProjectTagTitle>
                                         {project.tag}
                                     </ProjectTagTitle>
                                     {project.skills.map((x)=> (
                                     <ProjectSkillDiv key={Math.random()}>
                                         {x}
                                     </ProjectSkillDiv>    
                                     ))}
                                     <ProjectUnderLine/>
                                     <ProjectUnderDiv>
                                         <div>
                                             <ProjectSeperate>
                                             {`작성자 : ${project.write}`}
                                             </ProjectSeperate>
                                         </div>
                                         <div style={{display:"flex"}}>
                                             <ProjectSeperate style={{alignsItems:"center"}}>
                                                 <AiOutlineEye style={{marginRight:"3px"}}/>{project.view}
                                             </ProjectSeperate>
                                             <ProjectSeperate>
                                                 <AiOutlineComment style={{marginRight:"3px"}}/>{project.comment}
                                             </ProjectSeperate>
                                         </div>
                                     </ProjectUnderDiv>
                                 </Link>
                             </ProjectDiv>
                         
                     )
                 })}
             </>  : 
                     <>
                     {studyData.map((study)=> {
                         return (
                                 <ProjectDiv key={Math.random()}>
                                     <Link to={{pathname:`/info/${study.url}`}} state={{study}}  key={Math.random()}>
                                         <ProjectDayTitle>
                                             {`시작 예정일 | ${study.year}`}
                                         </ProjectDayTitle>
                                         <ProjectTitle>
                                             {study.title}
                                         </ProjectTitle>
                                         <ProjectTagTitle>
                                             {study.tag}
                                         </ProjectTagTitle>
                                         {study.skills.map((x)=> (
                                         <ProjectSkillDiv key={Math.random()}>
                                             {x}
                                         </ProjectSkillDiv>    
                                         ))}
                                         <ProjectUnderLine/>
                                         <ProjectUnderDiv>
                                             <div>
                                                 <ProjectSeperate>
                                                 {`작성자 : ${study.write}`}
                                                 </ProjectSeperate>
                                             </div>
                                             <div style={{display:"flex"}}>
                                                 <ProjectSeperate style={{alignsItems:"center"}}>
                                                     <AiOutlineEye style={{marginRight:"3px"}}/>{study.view}
                                                 </ProjectSeperate>
                                                 <ProjectSeperate>
                                                     <AiOutlineComment style={{marginRight:"3px"}}/>{study.comment}
                                                 </ProjectSeperate>
                                             </div>
                                         </ProjectUnderDiv>
                                     </Link>
                                 </ProjectDiv>
                         )
                     })}
                 </>
                }
                
            </ResultBox>
        </ResultDiv>
        </>
        
    )
}

export default Select