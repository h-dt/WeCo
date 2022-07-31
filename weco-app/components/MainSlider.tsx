import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay, Pagination, Navigation } from "swiper";
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
import Image from "next/image";

export function MainSlider() {
  return (
    <Swiper
      spaceBetween={30}
      centeredSlides={true}
      autoplay={{
        delay: 2500,
        disableOnInteraction: false,
      }}
      pagination={{
        clickable: true,
      }}
      modules={[Autoplay, Pagination, Navigation]}
      className="mySwiper"
    >
      <SwiperSlide>
        <div>
          <a className="w-full relative">
            <div className="bg-yellow-400">
              <div className="flex max-w-7xl h-80 py-0 px-6 my-0 mx-auto">
                <div className="flex-1 my-auto mx-0">
                  <button className="font-bold py-1.5 px-8 bg-violet-500 rounded-3xl mb-4">
                    공지사항
                  </button>
                  <div></div>
                  <h2 className="inline-block text-4xl font-bold p-0 mx-0 my-2.5">
                    새롭게 달라진 WECO!
                  </h2>
                  <span className="text-4xl">를 소개합니다</span>
                  <span className="block text-xl">
                    이제 마음 맞는 팀원을 더 쉽게 만나세요 🔍
                  </span>
                </div>
                <div className="flex-1 flex justify-center items-center">
                  <Image src="/img/search.png" width="260" height="260" />
                </div>
              </div>
            </div>
          </a>
        </div>
      </SwiperSlide>
      <SwiperSlide>
        <div>
          <a className="w-full relative">
            <div className="bg-blue-400">
              <div className="flex max-w-7xl h-80 py-0 px-6 my-0 mx-auto">
                <div className="flex-1 my-auto mx-0">
                  <h2 className="inline-block text-4xl font-bold p-0 mx-0 my-2.5">
                    스터디와 사이드 프로젝트를 찾는
                  </h2>
                  <h2 className="inline-block text-4xl font-bold p-0 mx-0 my-2.5">
                    가장 쉬운 방법
                  </h2>
                  <span className="text-2xl block mt-7">
                    WECO 에서 함께할 개발자를 찾으세요 ⭐️
                  </span>
                </div>
                <div className="flex-1 flex justify-center items-center">
                  <Image
                    src="/img/together.svg"
                    className="bg-white"
                    width="260"
                    height="260"
                  />
                </div>
              </div>
            </div>
          </a>
        </div>
      </SwiperSlide>
    </Swiper>
  );
}
