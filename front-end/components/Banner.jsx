import styled from 'styled-components'
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import {SiNotion,SiGithub} from 'react-icons/si'
import "slick-carousel/slick/slick-theme.css";


const StyledSlider = styled(Slider)`
    margin: 0px 150px;
    display: flex;
    justify-content: center;
    align-items: center;
    .slick-list{
      margin:0px 20px;
    }
    .slick-track {
    display: flex;
    justify-content: center;
    align-items: center;
    }
  .slick-arrow.slick-next::before{
    font-size: 30px;
    color: black;
  }
  .slick-arrow.slick-prev::before{
    font-size: 30px;
    color: black;
  }
  .slick-dots{
    margin: 10px 0px;
  }
  .slick-dots li {
    margin: 0 3.5px;
  }

  .slick-dots li button {
    width: 6px;
    height: 6px;
  }

  .slick-dots li button:before {
    width: 6px;
    height: 6px;
    color: black;
  }

  .slick-dots li.slick-active button:before {
    color: black !important;
  }

  li {
    margin: 0;
    padding: 0;
  }
`

const ImgBox = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width:100%;
    height: 450px;
    border-radius: 10px;
    background-color: white;
`

const OkkyImgBox= styled.div`
  width:500px;
  height:350px;
  cursor: pointer;
  border-radius: 10px;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("https://okky.kr/assets/images/okky_logo_fb.png");
`
const RocketImgBox= styled.div`
  width:520px;
  height:200px;
  cursor: pointer;
  border-radius: 10px;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("https://static.rocketpunch.com/images/common/share_default.png");
`
const WantedImgBox= styled.div`
  width:450px;
  height:250px;
  cursor: pointer;
  border-radius: 10px;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("https://file.mk.co.kr/meet/neds/2021/08/image_readtop_2021_777584_16287284604747853.jpg");
`
const JobImgBox= styled.div`
  width:450px;
  height:250px;
  cursor: pointer;
  border-radius: 10px;
  background-position: center;
  background-color: white;
  background-size: cover;
  border-color:white;
  background-image: url("http://www.ekoreanews.co.kr/news/photo/202001/41870_51828_054.png");
`
    

const BannerDiv = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    
`
const NoticeBtn = styled.div`
    width: 300px;
    height:150px;
    background-color: #ddddd3;
    border: none;
    text-align: center;
    margin-right: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 22px;
    border-radius: 10px;
    cursor: pointer;
    :hover{
        transform: scale(1.02);
        
    }
`
function NextArrow(props) {
    const { className, style, onClick } = props;
    return (
      <div
        className={className}
        style={{ ...style, display: "block"  }}
        onClick={onClick}
      />
    );
  }

function PrevArrow(props) {
    const { className, style, onClick } = props;
    return (
      <div
        className={className}
        style={{ ...style, display: "block",  }}
        onClick={onClick}
      />
    );
  }
  
function Banner (){
    const settings = {
        dots: true,
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        speed: 2000,
        autoplaySpeed: 3000,
        cssEase: "linear",
        nextArrow: <NextArrow />,
        prevArrow: <PrevArrow />
      }
    return(
        <StyledSlider {...settings}>
            <BannerDiv >
              <ImgBox>
                <NoticeBtn>
                  <a href="https://rustic-tray-8dd.notion.site/0c464036142a430d97ace8b2e9217d13" rel="noreferrer" target='_blank'> 
                    Organization Notion   
                  </a>
                </NoticeBtn>
                <SiNotion size="300"/>
              </ImgBox> 
            </BannerDiv>
            <BannerDiv>
                <ImgBox>
                <NoticeBtn>
                    <a href='https://github.com/h-dt' rel="noreferrer" target="_blank">
                        Organization Git
                    </a>
                </NoticeBtn>
                <SiGithub size="300"/>
                </ImgBox>
            </BannerDiv>
            <BannerDiv>
                <ImgBox>
                <NoticeBtn>
                    <a href='https://okky.kr/' rel="noreferrer" target="_blank">
                        Okky
                    </a>
                </NoticeBtn>
                <OkkyImgBox/></ImgBox>
            </BannerDiv>    
            <BannerDiv>
                <ImgBox>
                <NoticeBtn>
                    <a href='https://www.rocketpunch.com/' rel="noreferrer" target="_blank">
                        로켓펀치
                    </a>
                </NoticeBtn>
                <RocketImgBox/></ImgBox>
            </BannerDiv>
            <BannerDiv>
                <ImgBox>
                <NoticeBtn>
                    <a href='https://www.wanted.co.kr/jobsfeed?utm_source=google&utm_medium=sa&utm_campaign=kr_recruit_web_sa_signup_brand&utm_term=%EC%9B%90%ED%8B%B0%EB%93%9C&utm_content=brand&gclid=CjwKCAjw7vuUBhBUEiwAEdu2pILbav9cmZbSGJI-ytJn2bgrt-U8pO3Kt8pxkkgHvTP5sTWUHGzhbRoC27YQAvD_BwE' rel="noreferrer" target="_blank">
                        원티드
                    </a>
                </NoticeBtn>
                <WantedImgBox/></ImgBox>
            </BannerDiv>
            <BannerDiv>
                <ImgBox>
                <NoticeBtn>
                    <a href='https://www.jobplanet.co.kr/job' rel="noreferrer" target="_blank">
                        잡 플래닛
                    </a>
                </NoticeBtn>
                <JobImgBox/></ImgBox>
            </BannerDiv>           
            </StyledSlider>
    )
}

export default Banner