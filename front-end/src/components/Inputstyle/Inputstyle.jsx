import styled from "styled-components";
export const PopupSubmitInput = styled.input`
    font-size: 18px;
    cursor: pointer;
    margin: 10px;
    width: 300px;
    height: 40px;
    margin: 5px;
    border-radius: 10px;
    text-align: center;
    color: ${(props)=>props.theme.fontColor};
    font-weight: 100;
    margin-right: 15px;
    :focus {
      font-weight: bolder;
      transform: scale(1.05);
  }
`

export const Inputs = styled.input`
    font-size: 18px;
  cursor: pointer;
  height: 50px;
  border:none;
  border-bottom: 1px solid ${(props)=>props.theme.loginColor};
  margin: 10px;
  color: ${(props)=>props.theme.fontColor};
  text-align: center;
  font-weight: 100;
  :focus {
    border:none;
    border-bottom: 2px solid ${(props)=>props.theme.loginColor};
  }
`