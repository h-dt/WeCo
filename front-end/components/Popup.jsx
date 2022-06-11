import styled from "styled-components";
import { FcSurvey } from "react-icons/fc";
import { useState } from "react";
import Modal from 'react-modal';
import { useForm } from "react-hook-form";
import { Rating } from 'react-simple-star-rating'


const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        overflow: "hidden",
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
        display: "flex",
        justifyContent:"center",
        alignsItem : "center",
        flexDirection:"column",
        textAlign:"center",
        borderRadius : "20px",
    },
};

const PopUpDiv= styled.div`
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
    width:100px;
    height:100px;
    border-radius: 50%;
    background-color: #acac97;
    left:20px;
    bottom: 20px;   
    cursor: pointer;
    :hover{
        transform: scale(1.1);
    }
`

const ModalTitle =styled.div`
    width:400px;
    height: 50px;
    font-size: 24px;
    color:black;
    font-weight: bold;
`

const ModalSubTitle= styled.div`
    width:400px;
    height: 50px;
    font-size: 12px;
    color: grey;
    font-weight: 300;
`
const Form = styled.form`
  display: flex;
  font-weight: 100;
  justify-content: center;
  margin-top: 20px;
  height: 100%;
  align-items: center;
  span {
    color: red;
  }
`;

const SubmitInput = styled.input`
    font-size: 18px;
    cursor: pointer;
    margin: 10px;
    width: 300px;
    height: 40px;
    margin: 5px;
    border-radius: 10px;
    text-align: center;
    font-weight: 100;
    margin-right: 15px;
    :focus {
      font-weight: bolder;
      transform: scale(1.05);
  }
`

const SubmitBtn = styled.input`
    width:100px;
    height:40px;
    border-radius:15px;
  :hover{
    transform: scale(1.05);
  }
`

function Popup (){
    const {
        register,
        handleSubmit,
        reset,
      } = useForm({mode:"onChange"});
    const [modalOpen,setModalOpen] = useState(false);
    const openModal = ()=>{
        setModalOpen(true);
    }
    const closeModal = ()=>{
        setModalOpen(false)
    }
    const [rating, setRating] = useState(0)
    const [feedback,setFeedback] = useState("")
    const handleRating = (rate) => {
        setRating(rate) 
    }
    const onSubmitValid=(data)=>{
        setFeedback(data.feedbackvalue);
        reset()
      }
      localStorage.setItem("별점",rating);
      localStorage.setItem("피드백",feedback);
    return (
        <>
        <PopUpDiv onClick={openModal}><FcSurvey style={{fontSize:"70px"}}/></PopUpDiv>
            <Modal 
            isOpen={modalOpen}
            onRequestClose={closeModal}
            style={customStyles}
            ariaHideApp={false}
            contentLabel="Example Modal">
                <ModalTitle>Organization 에 만족하셨나요?</ModalTitle>
                <ModalSubTitle>더 좋은 서비스를 위해서 평가를 남겨주시면 감사하겠습니다!</ModalSubTitle>
                <Rating onClick={handleRating} ratingValue={rating}/>
                <Form onSubmit={handleSubmit(onSubmitValid)}>
                <SubmitInput
                {...register("feedbackvalue", {
                    required: "피드백을 남겨주세요",
                    })}
                    type="text"
                    placeholder="피드백을 남겨주세요"
                />
                <SubmitBtn type="submit" value="제출하기" style={{ fontWeight: "bolder" }}/>
                </Form>
            </Modal>
        </>
    )
}

export default Popup;