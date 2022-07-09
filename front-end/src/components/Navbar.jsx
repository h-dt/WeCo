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
  text-align: center;
  background-clip: border-box;
  width:30%;
  margin:10px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-size: 200% auto;
  :hover{
    transform: scale(1.05);
    transition-duration: 1s;
  }
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  font-size: 35px;
  @keyframes textclip {
  to {
    background-position: 200% center;
  }
}
  @media all and (min-width: 480px) and (max-width: 767px) {
        width: 40%;
        font-size: 20px;
    }
    @media all and (max-width: 479px) {
        width: 40%;
        font-size: 20px;
    }
`

function Navbar() {
  const location= useLocation();
  const DarkAtom = useRecoilValue(isDarkAtom);
  const setDarkAtom = useSetRecoilState(isDarkAtom);
  const toggleDarkAtom = () => setDarkAtom((prev)=>!prev)
  return ( 
    <Navbardiv>
        <NavLogo>
          <Link to={{pathname:"/"}}>
            Organization
          </Link>
        </NavLogo> 
      <NavbarRightDiv>
          <NavBtn>
            <Link to={{pathname : "/write"}}>
              새 글 쓰기
            </Link>
          </NavBtn>
        {location.pathname === "/login" ? 
          <NavBtn>
              <Link to={{pathname: "/sign"}}>
                회원가입
              </Link>
          </NavBtn>: 
            <NavBtn>
              <Link to={{pathname: "/login"}}>
                로그인
              </Link>
            </NavBtn>}
          <DarkModeToggle
            onChange={toggleDarkAtom}
            checked={DarkAtom}
            size={60}
            speed={2}
          />
      </NavbarRightDiv>
    </Navbardiv>
  )
}

export default Navbar;
