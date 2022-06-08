import styled from "styled-components";
import {Link, useLocation} from "react-router-dom";

const Navbardiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  position: -webkit-sticky;
  position: sticky;
  top: -5px;
  z-index: 2;
  height: 110px;
  min-height: 70px;
  background-color: #F6F6F6;
  box-shadow: 0 0.125rem 0.25rem 0 rgb(0 0 0 / 11%);
`

const NavLogo =styled.div`
  display: flex;
  width:250px;
  height:100px;
  margin-right: 100px;
  cursor: pointer;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("img/logofile/facebook_cover_photo_1.png");
  :hover{
    transform: scale(1.03);
  }
`

const NavbarRightDiv = styled.div`
margin-left: 200px;
  width:300px;
  height:50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
`

const NavBtn = styled.button`
  width:120px;
  font-weight: bold;
  margin-right: 20px;
  border: none;
  cursor: pointer;
  background-color: transparent;
  font-size: 18px;
  height:50px;
  :hover{
    transform: scale(1.03);
    div{
    background-color: #F9758F;
        width:100%;
        height:10px;
        display: flex;
        justify-content: center;
        align-items: center;
  }
  }

`

function Navbar() {
  const location= useLocation();
  return (
    <Navbardiv>
      <Link to={{pathname:"/"}}>
        <NavLogo/>
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
      </NavbarRightDiv>
    </Navbardiv>
  )
}

export default Navbar;
