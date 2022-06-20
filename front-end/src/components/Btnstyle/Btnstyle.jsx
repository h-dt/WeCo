import styled from 'styled-components'

const BtnShape =styled.button`
    width:120px;
    font-weight: bold;
    margin-right: 20px;
    border: none;
    cursor: pointer;
    background-color: transparent;
    font-size: 18px;
    height:50px;
`
const LoginShape = styled.div`
      background-position: center;
  background-repeat: none;
  background-size: cover;
  border-radius: 20px;
  background-color: ${(props)=>props.theme.bgColor};
`

export const SelectBtn = styled(BtnShape)`
    color:${(props)=>props.theme.fontColor};
    :hover{
        transform: scale(1.03);
    }
    div{
        background-color: ${(props)=>props.theme.underLineColor};
        width:100%;
        margin-top: 5px;
        height:5px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
`

export const NavBtn = styled(BtnShape)`
        color:${(props)=>props.theme.fontColor};
        margin-bottom: 5px;
      :hover{
        transform: scale(1.03);
        div{
            background-color: ${(props)=>props.theme.underLineColor};
            width:100%;
            height:5px;
            margin-top: 5px;
            display: flex;
            justify-content: center;
            align-items: center;
    }
  }
`

export const BannerBtn = styled.div`
    width: 250px;
    height:250px;
    background-color: ${(props)=>props.theme.bannerBtnColor};
    border: none;
    text-align: center;
    margin-right: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 24px;
    font-weight: 500;
    border-radius: 50%;
    color:${(props)=>props.theme.fontColor};
    cursor: pointer;
    line-height: 40px;
    :hover{
        transform: scale(1.05); 
        transition-duration: 0.5s;
        background-color: ${(props)=>props.theme.bannerHover};
    }
`

export const PopupSubmitBtn = styled.input`
    width:100px;
    height:40px;
    border-radius:15px;
    background-color: transparent;
    cursor: pointer;
    :hover{
        transform: scale(1.05);
    }
`

export const ResetBtn =styled.div`
    background-color: ${(props)=>props.theme.reset};
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
    
export const ResultSelectBtn =styled.div`    
    font-size: 28px;
    display: flex;
    justify-content: center;
    margin:10px 60px;
    flex-direction: column;
    cursor: pointer;
    :hover{
        transform: scale(1.1);
    }
    div{
            background-color: ${(props)=>props.theme.underLineColor};
            width:100%;
            height:5px;
            margin-top: 5px;
            display: flex;
            justify-content: center;
            align-items: center;
    }
`

export const GoogleBtn = styled(LoginShape)`
  width: 50px;
  height: 50px;
  background-color: transparent;
  background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjzC2JyZDZ_RaWf0qp11K0lcvB6b6kYNMoqtZAQ9hiPZ4cTIOB");
`;

export const GithubBtn = styled(LoginShape)`
width: 50px;
  height: 50px;
  background-image: url("img/logofile/gitimage.png");
  
`;
export const KaKaoBtn = styled(LoginShape)`
    width: 50px;
  height: 50px;
  background-image: url("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fem7IrN%2Fbtq7snvbsK4%2FEKApcyLKGqBGWr8tmsbkXK%2Fimg.png");

`;

export const LoginSubmitBtn = styled.input`
    margin:auto;
    margin-top:40px;
    width:50%;
    height:40px;
    cursor: pointer;
    color: ${(props)=>props.theme.fontColor};
    border:  1px solid  ${(props)=>props.theme.fontColor};
    background-color: ${(props)=>props.theme.submitBtnColor};
    border-radius: 10px;
    :hover{
        transform: scale(1.05);
        background-color: ${(props)=>props.theme.underLineColor};
        color:${(props)=>props.theme.boxFontHoverColor}
    }
`

export const GoHomeBtn = styled.div`
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
background-color: ${(props)=>props.theme.bgColor};
:hover{
    transform: scale(1.05);
    background-color: ${(props)=>props.theme.underLineColor};
    color:${(props)=>props.theme.boxFontHoverColor}
}
`