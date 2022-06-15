import { FcSurvey } from "react-icons/fc";
import { useState } from "react";
import Modal from 'react-modal';
import { useForm } from "react-hook-form";
import { Rating } from 'react-simple-star-rating'
import { PopUpStyle } from "./ScreenFunc/PopUpFunc";
import { PopUpDiv } from "./DivStyle/Divstyle";
import {PopupTitle,PopupSubTitle} from './Titlestyle/Titlestyle'
import {PopupForm} from "./Formstyle/Formstyle";
import { PopupSubmitBtn } from "./Btnstyle/Btnstyle";
import { PopupSubmitInput } from "./Inputstyle/Inputstyle";


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
            style={PopUpStyle}
            ariaHideApp={false}
            contentLabel="Example Modal">
                <PopupTitle>Organization 에 만족하셨나요?</PopupTitle>
                <PopupSubTitle>더 좋은 서비스를 위해서 평가를 남겨주시면 감사하겠습니다!</PopupSubTitle>
                <Rating onClick={handleRating} ratingValue={rating} fillColorArray={["#f5c3c3","#f19e9e","#f36262","#f33b3b","#fa0404"]} transition={true} />
                <PopupForm onSubmit={handleSubmit(onSubmitValid)}>
                <PopupSubmitInput
                {...register("feedbackvalue", {
                    required: "피드백을 남겨주세요",
                    })}
                    type="text"
                    placeholder="피드백을 남겨주세요"
                />
                <PopupSubmitBtn type="submit" value="제출하기" style={{ fontWeight: "bolder" }}/>
                </PopupForm>
            </Modal>
        </>
    )
}

export default Popup;