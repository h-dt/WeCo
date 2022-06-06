import styled from "styled-components";
import Navbar from "../components/Navbar";

const SkillListDiv = styled.div`
  margin-top: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 32px;

`

function Project() {
  const skillList = localStorage.getItem("skill");
  return (
    <>
    <Navbar/>
    <SkillListDiv>{`선택한 Skill : ${skillList}`}</SkillListDiv>
    </>
    
  )
  
}

export default Project;
