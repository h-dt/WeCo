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
  padding:70px;
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
color: ${(props)=>props.theme.fontColor};
margin:20px;
padding:30px;
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
  display: flex;
  margin-left: 70px;
  margin-bottom: 30px;
  font-weight: 100;
  justify-content: center;
  gap: 5px;
  flex-wrap: wrap;
  color: ${(props)=>props.theme.fontColor};
  input,select{
    font-size: 18px;
    cursor: pointer;
    width: 400px;
    color: ${(props)=>props.theme.fontColor};
    background-color:  ${(props)=>props.theme.submitBtnColor};
    border: 1px solid #dcdde1;
    height: 60px;
    margin: 30px 10px 15px 0px;
    border-radius: 10px;
    text-align: center;
    font-weight: 100;
    :focus {
      transform: scale(1.05);
    }
  }
  option{
    color: ${(props)=>props.theme.fontColor};
  }
`;