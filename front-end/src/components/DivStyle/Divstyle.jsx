import styled from 'styled-components'

export const BannerDiv = styled.div`

`

export const NavbarRightDiv = styled.div`
    width:70%;
    display:flex;
    justify-content: flex-end;
    padding-right: 10%;
    text-align: center;
    align-items: center;
    @media all and (min-width: 480px) and (max-width: 767px) {
        width:60%;
        padding-right: 2%;
    }
    @media all and (max-width: 479px) {
        width:60%;
        padding-right: 2%;
    }
`
export const Navbardiv = styled.div`
    width:100%;
    position: fixed;
    justify-content: space-around;
    display: flex;
    height:120px;
    top: 0;
    z-index: 10;
    background-color: ${(props)=>props.theme.bgColor};
    opacity: 0.97;
    box-shadow:${(props)=>props.theme.nav};
    @media all and (min-width: 480px) and (max-width: 767px) {
        padding:0px 20px;
    }
    @media all and (max-width: 479px) {
        padding:0px 20px;
    }
`

export const PopUpDiv= styled.div`
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    width:100px;
    height:100px;
    border-radius: 50%;
    background-color: ${(props)=>props.theme.underLineColor};
    left:20px;
    bottom: 20px;  
    z-index: 4;
    cursor: pointer;
    :hover{
        transform: scale(1.2);
        transition-duration: 1s;
    }
    @media all and (min-width: 480px) and (max-width: 767px) {
        width:50px;
        height:50px;
        svg{
            width:30px;
            height:30px;
        }
    }
    @media all and (max-width: 479px) {
        width:50px;
        height:50px;
        svg{
            width:30px;
            height:30px;
        }
    }
`

export const SelectDiv= styled.div`
    margin-bottom: 50px;
    width:100%;
    height:100%;
    background-color: ${(props)=>props.theme.bgColor};
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    @media all and (min-width: 480px) and (max-width: 767px) {
       display:none;
    }
    @media all and (max-width: 479px) {
       display:none;
    }
`

export const ShowDiv = styled.ul`
    display: flex;
    gap: 5px;
    width:62%;
    flex-wrap: wrap;
    font-size: 24px;
    color:black;
    background-color: ${(props)=>props.theme.bgColor};
    color:${(props)=>props.theme.fontColor};
    border-radius: 10px;
    margin: 20px 100px;
    justify-content: center;
    align-items: center;
`

export const ShowItemDiv= styled.li`
display: flex;
align-items: center;
justify-content: center;
width:160px;
height:50px;
margin: 5px;
border: 1px solid ${(props)=>props.theme.underLineColor};
border-radius: 10px;
:hover{
    cursor: pointer;
    transform: scale(1.05);
    transform: translateY(-5px);
    transition-duration: 1s;
    background-color: ${(props)=>props.theme.bannerHover};
    color:${(props)=>props.theme.boxFontHoverColor};
}
`

export const ResultDiv =styled.div`
    width:90%;
    height:100%;
    background-color: ${(props)=>props.theme.bgColor};
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    margin: 0 auto;
    @media all and (min-width: 480px) and (max-width: 767px) {
       padding-top: 150px;
       margin: 0 auto;
    }
    @media all and (max-width: 479px) {
       padding-top: 150px;
       margin: 0 auto;
    }
`
export const ResultSelectBtnDiv= styled.div`
    width:90%;
    display: flex;
    align-items: center;
    position: relative;
    margin-bottom: 2rem;
    color:${(props)=>props.theme.fontColor};
    @media all and (min-width:480px) and (max-width:767px) {
        flex-direction: column;
    } 
    @media all and (max-width:479px) {
        flex-direction: column;
    }   
`

export const SelectBarDiv = styled.div`
    display: flex;
    width:80%;
    height:60px;
    margin: 15px 30px;
    align-items: center;
    justify-content: center;
    background-color: ${(props)=>props.theme.bgColor};
    border-radius: 10px;
`

export const ShowSelectListDiv = styled.div`
    display: flex;
    gap: 5px;
    width:80%;
    flex-wrap: wrap;
    margin : 20px 0px 20px 80px;
    background-color: ${(props)=>props.theme.bgColor};
    border-radius: 10px;
    color:${(props)=>props.theme.fontColor};
`

export const ShowSelectListItemDiv = styled.div`
    font-size: 24px;
    width: 170px;
    height:50px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0px 5px 0px 0px;
    border-radius: 10px;
    background-color: ${(props)=>props.theme.bannerHover};
    color:${(props)=>props.theme.fontColor};
    border: 1px solid ${(props)=>props.theme.fontColor};
    :hover{
        cursor: pointer;
        transform: scale(1.05);
        transform: translateY(-5px);
        transition-duration: 1s;
        background-color: ${(props)=>props.theme.bgColor};
        color:${(props)=>props.theme.fontColor};
    }
`



export const LoginBtnDiv = styled.div`
  border-radius: 10px;
  display: flex;
  width:60px;
  height: 60px;
  justify-content: center;
  align-items: center;
  margin: 10px 0px;
  box-shadow: 2px 2px 2px 2px gray;
  cursor: pointer;
  :hover {
    transform: translateY(-5px);
    transition-duration: 1s;
  }
`
export const WriteDiv= styled.div`
    display: flex;
    gap: 5px;
    flex-wrap: wrap;
    padding-top: 100px;
`

export const WriteBtnDiv = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
`

export const ProjectDiv = styled.div`
    display:flex;
    width:400px;
    align-items: center;
    flex-direction: column;
    border: 1px solid #dcdde1;
    border-radius: 10px;
    margin-bottom: 50px;
    padding: 50px 10px;
    cursor: pointer;
    :hover{
        transform: scale(1.05);
        transform: translateY(-5px);
        transition-duration: 1s;

    }
`
export const ProjectSkillDiv =styled.div`
    margin:10px;
    color: #9c9c9e;
`
export const ProjectUnderLine = styled.div`
    width: 100%;
    margin: 10px;
    height:2px;
    background-color: #F2F2F2;
`
export const ProjectUnderDiv = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;
`

export const ProjectSeperate = styled.div`
    margin:0px 10px;
    display: flex;
    color: ${(props)=>props.theme.fontColor};
`