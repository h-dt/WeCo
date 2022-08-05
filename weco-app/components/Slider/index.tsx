import { Swiper, SwiperProps, SwiperSlide } from 'swiper/react';
import { Autoplay, Pagination } from 'swiper';
import 'swiper/css';
import 'swiper/css/pagination';
import { SliderForm } from './SliderForm/SliderForm';

const settings: SwiperProps = {
  spaceBetween: 30,
  centeredSlides: true,
  autoplay: {
    delay: 2500,
    disableOnInteraction: false,
  },
  pagination: {
    clickable: true,
  },
  modules: [Autoplay, Pagination],
};

const firstSlide = {
  bgColor: 'bg-yellow-400',
  btnText: '공지사항',
  btnColor: 'bg-violet-500',
  h2Text: '새롭게 달라진 WECO!',
  SubspanText: '를 소개합니다',
  spanText: '이제 마음 맞는 팀원을 더 쉽게 만나세요 🔍',
  img: '/img/search.png',
};

const SecondSlide = {
  bgColor: 'bg-blue-400',
  h2Text: '스터디와 사이드 프로젝트를 찾는 가장 쉬운 방법',
  spanText: ' WECO 에서 함께할 개발자를 찾으세요 ⭐️',
  img: '/img/together.svg',
};

export function MainSlider() {
  return (
    <Swiper {...settings}>
      <SwiperSlide>
        <SliderForm {...firstSlide} />
      </SwiperSlide>
      <SwiperSlide>
        <SliderForm {...SecondSlide} />
      </SwiperSlide>
    </Swiper>
  );
}
