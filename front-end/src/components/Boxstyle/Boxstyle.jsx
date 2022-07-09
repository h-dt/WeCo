import styled from 'styled-components'
const BoxShape = styled.div`
    cursor: pointer;
    border-radius: 10px;
    background-position: center;
    background-color: ${(props)=>props.theme.bgColor};
    background-size: cover;
    border-color:${(props)=>props.theme.borderColor};
`

export const ImgBox = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0px 20px;
    width:100%;
    height: 450px;
    border-radius: 10px;
    background-color: ${(props)=>props.theme.bgColor};
    @media all and (min-width: 480px) and (max-width: 767px) {
      margin-left:5px;
    }
    @media all and (max-width: 479px) {
      margin-left:5px;
    }
`
export const NotionImgBox= styled(BoxShape)`
  width:500px;
  height:270px;
  background-image: url("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FDqQLI%2FbtqGxm9oHFg%2FAajpAnXUHL9azGvStBeYgK%2Fimg.png");
  @media all and (min-width: 480px) and (max-width: 767px) {
    width:200px;
    height:100px;
    }
    @media all and (max-width: 479px) {
      width:200px;
    height:100px;
    }
`
export const GitImgBox= styled(BoxShape)`
  width:500px;
  height:270px;
  background-image: url("https://velog.velcdn.com/images/beenz/post/30f3c490-ab35-4298-be9d-b9b2a548e496/github(1).jpg");
  @media all and (min-width: 480px) and (max-width: 767px) {
    width:200px;
    height:100px;
    }
    @media all and (max-width: 479px) {
      width:200px;
    height:100px;
    }
`

export const OkkyImgBox= styled(BoxShape)`
  width:450px;
  height:300px;
  background-image: url("https://okky.kr/assets/images/okky_logo_fb.png");
  @media all and (min-width: 480px) and (max-width: 767px) {
      width:200px;
      height:130px;
    }
    @media all and (max-width: 479px) {
      width:200px;
      height:130px;
    }
`
export const RocketImgBox= styled(BoxShape)`
  width:500px;
  height:270px;
  background-image: url("https://static.rocketpunch.com/images/common/share_default.png");
  @media all and (min-width: 480px) and (max-width: 767px) {
    width:200px;
    height:100px;
    }
    @media all and (max-width: 479px) {
      width:200px;
    height:100px;
    }
`
export const WantedImgBox= styled(BoxShape)`
  width:500px;
  height:270px;
  background-image: url("https://file.mk.co.kr/meet/neds/2021/08/image_readtop_2021_777584_16287284604747853.jpg");
  @media all and (min-width: 480px) and (max-width: 767px) {
    width:200px;
    height:100px;
    }
    @media all and (max-width: 479px) {
      width:200px;
    height:100px;
    }
`
export const JobImgBox= styled(BoxShape)`
  width:500px;
  height:270px;
  background-image: url("https://jpassets.jobplanet.co.kr/production/uploads/material/media/7444/jp_og.png");
  @media all and (min-width: 480px) and (max-width: 767px) {
      width:200px;
      height:130px;
    }
    @media all and (max-width: 479px) {
      width:200px;
      height:130px;
    }
`

export const ResultBox= styled.div`
    display: flex;
    width:90%;
    justify-content: center;
    grid-gap: 35px;
    gap: 35px;
    flex-wrap: wrap;
    z-index: 3;
    @media all and (min-width: 480px) and (max-width: 767px) {
      width:100%;
    }
    @media all and (max-width: 479px) {
      width:100%;
    }
`