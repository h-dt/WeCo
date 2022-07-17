import styled from "styled-components";
export const PopupForm = styled.form`
  display: flex;
  font-weight: 100;
  justify-content: center;
  margin-top: 20px;
  height: 100%;
  align-items: center;
`;

export const LoginForm = styled.div`
  display: flex;
  padding-top: 120px;
  justify-content: center;
  
`

export const LoginModalForm = styled.div`
width: 500px;
height: 650px;
  display: flex;
  flex-direction: column;
  color: ${(props)=>props.theme.fontColor};
  margin: 20px;
  border: 1px solid ${(props)=>props.theme.fontColor};
  border-radius: 10px;
`;

export const LoginInputForm = styled.form`
display: flex;
font-weight: 100;
height: 400px;
padding: 30px;
color: ${(props)=>props.theme.fontColor};
flex-direction: column;
span {
  font-weight: bold;
  font-size:18px;
  color:${(props)=>props.theme.reset};
  display:flex;
  justify-content: center;
}
`;

export const WriteForm = styled.form`
  margin-bottom: 30px;
  font-weight: 100;
  gap: 10px;
  justify-content: center;
  display: flex;
  flex-wrap: wrap;
  color: ${(props)=>props.theme.fontColor};
  input,select{
    font-size: 18px;
    padding: 10px;
    width: 100%;
    margin-top: 10px;
    cursor: pointer;
    color: ${(props)=>props.theme.fontColor};
    background-color:  ${(props)=>props.theme.submitBtnColor};
    border: 1px solid #dcdde1;
    height: 60px;
    border-radius: 10px;
    text-align: center;
    font-weight: 100;
    @media all and (min-width:480px) and (max-width:767px) {
      width: 400px;
  } 
  @media all and (max-width:479px) {
    width: 400px;
  }
    :focus {
      transform: scale(1.05);
    }
  }
  option{
    color: ${(props)=>props.theme.fontColor};
  }
  @media all and (min-width:480px) and (max-width:767px) {
    flex-wrap: nowrap;
    flex-direction: column;
  } 
  @media all and (max-width:479px) {
    flex-wrap: nowrap;
    flex-direction: column;
  }
`;