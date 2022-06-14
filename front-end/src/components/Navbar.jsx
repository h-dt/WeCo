import styled from "styled-components";
import {Link, useLocation} from "react-router-dom";
import { NavbarRightDiv,Navbardiv } from "./DivStyle/Divstyle";
import { NavBtn } from "./Btnstyle/Btnstyle";
import {useSetRecoilState,useRecoilValue} from 'recoil';
import {isDarkAtom} from "../atom"
import DarkModeToggle from "react-dark-mode-toggle";

const NavLogo =styled.div`
 text-transform: uppercase;
  background-image: ${(props)=>props.theme.logo};
  background-size: auto auto;
  background-clip: border-box;
  background-size: 200% auto;
  :hover{
    transform: scale(1.05);
    transition-duration: 1s;
  }
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  display: inline-block;
  font-size: 35px;
  @keyframes textclip {
  to {
    background-position: 200% center;
  }
}
`

function Navbar() {
  const location= useLocation();
  const DarkAtom = useRecoilValue(isDarkAtom);
  const setDarkAtom = useSetRecoilState(isDarkAtom);
  const toggleDarkAtom = () => setDarkAtom((prev)=>!prev)
  return ( 
    <Navbardiv>
      <Link to={{pathname:"/"}}>
        <NavLogo>Organization</NavLogo> 
      </Link>
      <NavbarRightDiv>
        <Link to={{pathname : "/write"}}>
          <NavBtn>
            새 글 쓰기
            <div/>
          </NavBtn>
        </Link>
        {location.pathname === "/login" ?  <Link to={{pathname: "/sign"}}>
          <NavBtn>
            회원가입
            <div/>
          </NavBtn>
          </Link> :  <Link to={{pathname: "/login"}}>
            <NavBtn>
            로그인
            <div/>
            </NavBtn>
          </Link>}
          <DarkModeToggle
            onChange={toggleDarkAtom}
            checked={DarkAtom}
            size={70}
            speed={2}
          />
      </NavbarRightDiv>
    </Navbardiv>
  )
}

export default Navbar;
