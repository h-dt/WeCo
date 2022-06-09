
import { faX } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {  useState } from "react";
import styled from 'styled-components'
import dataSkill from './data/dataSkill'
import { FaFolderOpen,FaBookOpen } from "react-icons/fa";


const SelectDiv= styled.div`

    width:100%;
    height:100%;
    background-color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
`
const SelectBar = styled.div`
    display: flex;
`

const SelectBtn = styled.button`
    width: 120px;
    height:50px;
    border:none; 
    background-color: white;
    margin  : 10px 20px ;
    font-size: 18px;
    cursor: pointer;
    :hover{
        transform: scale(1.03);
    }
    div{
        background-color: #F9758F;
        width:100%;
        height:10px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
`

const ShowDiv = styled.ul`
    display: flex;
    flex-wrap: wrap;
    margin-left: 50px;
    margin-bottom: 20px;
    width:1000px;
    height:100%;
    font-size: 24px;
    background-color: white;
    color:black;
`
const ShowItem= styled.li`
    display: flex;
    align-items: center;
    justify-content: center;
    width:150px;
    height:50px;
    margin: 5px;
    border: 1px solid grey;
    border-radius: 10px;
    :hover{
        cursor: pointer;
        transform: scale(1.05);
        background-color: #FD8E8B;
        color:white;
    }
`

const ShowSelectList = styled.div`
display: flex;
flex-wrap: wrap;
column-gap: 20px;
justify-content: center;
align-items: center;
width: 800px;
height: 200px;
margin-bottom: 10px;
`

const ShowSelectListItem = styled.div`
    font-size: 16px;
    width: 120px;
    height:50px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0px 5px 0px 0px;
    border-radius: 10px;
    background-color:  #FD8E8B;
    color:white;
    border: 1px solid black;
    :hover{
        cursor: pointer;
        transform: scale(1.05);
        background-color: white;
        color: black;
    }
`

const ResetBtn =styled.div`
    background-color: red;
    color:white;
    width:150px;
    height:70px;
    font-size: 24px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
    cursor: pointer;
    border-radius: 20px;
    :hover{
        transform: scale(1.1);

    }
    `

const ResultDiv =styled.div`
    width:100%;
    height:100%;
    background-color: white;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin: 30px 40px;
`
const ResultSelectBtnDiv= styled.div`
    display: flex;
    justify-content: space-between;
`

const ResultSelectBtn =styled.div`    
    font-size: 24px;
    display: flex;
    justify-content: center;
    margin:10px;
    cursor: pointer;
    :hover{
        transform: scale(1.1);
    }
`

const ResultBox= styled.div`
    width:100%;
    height:100vh;
    display: grid;
    grid-template-columns: repeat(1fr);
`


function Select(){
    const [currentTab, setCurrentTab] = useState(0);
    const [returnitem, setReturnItem] = useState("");
    const newArray= []
    const onResetBtn = () =>{
        setReturnItem("")
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
            <SelectBar>
            {dataSkill.map((ele,index)=>{
                return (
                    <SelectBtn key={index} onClick={()=>selectMenuHandler(index)}>
                        {ele.name}
                        {index === currentTab && (
                            <div/>
                        )}                        
                    </SelectBtn>
                )
            })}
            </SelectBar>
            <ShowDiv>
               {newList.map((ele,index)=>{
                   return (
                       <ShowItem  key={index} onClick={()=>onClick(ele)} >
                           {ele}
                       </ShowItem>
                   )
               })}
            </ShowDiv>
            {returnitem === "" ? null  : (
                <>
                    <ShowSelectList>
                    {returnitem === "" ? null : 
                        returnitem.map((x)=> <ShowSelectListItem key={Math.random()} onClick={()=>onDelete(x)}>{x}<FontAwesomeIcon icon={faX} style={{marginLeft:10}}/></ShowSelectListItem>)}
                    </ShowSelectList>
                    <ResetBtn onClick={onResetBtn}>
                        Reset
                    </ResetBtn>
                </>
            )}
        </SelectDiv>
        <ResultDiv>
            <ResultSelectBtnDiv>
                <ResultSelectBtn>
                    <FaFolderOpen style={{marginRight:"10px"}}/>프로젝트
                </ResultSelectBtn>
                <ResultSelectBtn>
                    <FaBookOpen  style={{marginRight:"10px"}}/>
                    스터디
                </ResultSelectBtn>
            </ResultSelectBtnDiv>
            <ResultBox>

            </ResultBox>
        </ResultDiv>
        </>
        
    )
}

export default Select