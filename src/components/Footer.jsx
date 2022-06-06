import styled from "styled-components";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { twitter } from "fontawesome";
import { faGithub, faTwitter } from "@fortawesome/free-brands-svg-icons";

const FooterDiv= styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width:100%;
  height: 100px;
  margin: 20px 0px;
`
const FooterIcon = styled.div`
  display: flex;
  width:70px;
  cursor: pointer;
  height:70px;
  justify-content: center;
  margin-right: 30px;
  border-radius: 10px;
  padding:5px;
  :hover{
    transform: scale(1.03);
    background-color: #FD8F8C;
  }
`
const NotionImg = styled.div`
  display: flex;
  width:70px;
  height:70px;
  cursor: pointer;
  background-position: center;
  background-size: cover;
  border-color:white;
  border-radius:10px;
  background-image: url("https://img.icons8.com/ios/452/notion.png");
  :hover{
    transform: scale(1.03);
    background-color: #FD8F8C;
  }
`

function Footer() {
  return (
    <FooterDiv>
      <FooterIcon>
        <FontAwesomeIcon icon={faGithub} size="4x"/>
      </FooterIcon>
      <NotionImg/>
    </FooterDiv>
  )
}

export default Footer;
