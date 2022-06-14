import styled from "styled-components";

export const PopupTitle =styled.div`
width:400px;
height: 50px;
font-size: 24px;
color:black;
font-weight: bold;
`

export const PopupSubTitle= styled.div`
width:400px;
height: 50px;
font-size: 12px;
color: ${(props)=>props.theme.bannerHover};
font-weight: 300;
`

export const SmallTitle = styled.div`
  font-size: 18px;
  margin-bottom: 10px;
  color: ${(props)=>props.theme.underLineColor};
  font-weight: bold;
`
export const LoginTitle = styled.div`
  margin-top: 50px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  font-size: 25px;
  font-weight: 600;
  color: ${(props)=>props.theme.fontColor};
`

export const SmallLoginTitle = styled.div`
  font-size: 20px;
  font-weight: 600;
  margin-top: 20px;
`
export const WriteTitle = styled.div`
display: flex;
justify-content: center;
align-items: center;
margin: 20px 0px;
width:100%;
height:100px;
color: ${(props)=>props.theme.fontColor};
font-size: 24px;
font-weight: 600;
text-decoration-line: underline;
text-decoration-style: dashed;
text-underline-position : under;
`

export const ProjectDayTitle =styled.div`
    font-size: 16px;
    color: #9c9c9e;
    margin:15px 10px;
`
export const ProjectTitle =styled.div`
    font-size: 20px;
    color: ${(props)=>props.theme.fontColor};
`
export const ProjectTagTitle =styled.div`
    font-size:16px;
    margin:15px 10px;
    color: #9c9c9e;
`
