import { NextPage } from 'next';
import React, { useEffect, useState } from 'react';
import RecruitToggle from '../../components/toggle/RecruitToggle';

interface Category {
  id: string;
  text: string;
  commonValue: string;
  img: string;
}

const RecruitHeader: NextPage = () => {
  const categoryList: Category[] = [
    {
      id: 'A',
      text: '전체',
      commonValue: 'category',
      img: './images/all_icon.png',
    },
    {
      id: 'P',
      text: '프로젝트',
      commonValue: 'category',
      img: './images/project_icon.png',
    },
    {
      id: 'S',
      text: '스터디',
      commonValue: 'category',
      img: './images/study_icon.png',
    },
  ];

  const [activeTitle, setActiveTitle] = useState<string>('전체');
  const [isChecked, setIsChecked] = useState<boolean>(true);

  useEffect(() => {
    const name = '전체';
    const divElement = document.querySelectorAll('#category');
    divElement.forEach((element) => {
      if ((element as HTMLDivElement).title === name) {
        element.classList.add('text-black');
      }
    });
  }, []);

  useEffect(() => {
    // 전체, 프로젝트 , 스터디 중 하나를 클릭했을 때 각각 맞는 api 호출
    console.log(activeTitle);
  }, [activeTitle]);

  const activeHandler = (e: React.MouseEvent<HTMLDivElement>) => {
    const target = e.currentTarget;
    const divElement = document.querySelectorAll('#category');
    divElement.forEach((element) => {
      element.classList.remove('text-black');
    });
    target.classList.add('text-black');
    setActiveTitle(target.title);
  };

  const inputChangeHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    const checked = e.target.checked;
    setIsChecked(checked);
  };

  return (
    <>
      <div className="flex items-center mb-8 relative">
        <section className="flex">
          {categoryList.map((items) => (
            <div
              key={items.id}
              className="flex items-center mr-6 font-bold text-2xl cursor-pointer text-neutral-400  "
              id={items.commonValue}
              title={items.text}
              onClick={(e) => activeHandler(e)}
            >
              <img
                className="w-[28px] h-[28px]"
                src={items.img}
                alt={items.text}
              ></img>
              <span className="ml-2">{items.text}</span>
            </div>
          ))}
        </section>

        <div className="flex items-center absolute right-0 ssm:-top-14">
          <span className="font-bold text-2xl mr-6 tracking-tight">
            모집 중만 보기
          </span>
          <RecruitToggle isChecked={isChecked} onChange={inputChangeHandler} />
        </div>
      </div>
    </>
  );
};

export default RecruitHeader;
