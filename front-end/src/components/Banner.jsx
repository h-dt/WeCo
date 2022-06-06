import styled from 'styled-components'
const BannerDiv = styled.div`
    display: flex;

    justify-content: center;
    align-items: center;
    flex-direction: column;
    width:100%;
    height:400px;
    background-image: linear-gradient(to right, #f78ca0 0%, #f9748f 19%, #fd868c 60%, #fe9a8b 100%);
`

const NoticeBtn = styled.button`
    width:100px;
    height:50px;
    background-color: white;
    border: none;
    text-align: center;
    font-size: 22px;
    margin-bottom: 20px;
    border-radius: 10px;
    cursor: pointer;
    :hover{
        transform: scale(1.02);
        background-color: #F74053;
    }
`

const NoticeH1= styled.div`
    
    width:500px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 10px;
    height:100px;
    font: 24px bolder;
`

function Banner (){
    return(
        <BannerDiv>
            <NoticeBtn>Notion</NoticeBtn>
            <NoticeH1>Organiztion에 대한 정보를 얻어보세요!</NoticeH1>
        </BannerDiv>
    )
}

export default Banner