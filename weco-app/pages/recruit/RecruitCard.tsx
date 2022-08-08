import { useState } from 'react';
import type { NextPage } from 'next';
import Link from 'next/link';
import { projectData } from '../../data/SampleData.js';
import { AiOutlineEye, AiOutlineComment } from 'react-icons/ai';

interface SampleProject {
  id: number;
  type: string;
  year: string;
  title: string;
  tag: string[];
  skills: string[];
  write: string;
  view: string;
  comment: string;
  contact: string;
  url: string;
  explan: string;
}

const RecruitCard: NextPage = () => {
  const [project, setProject] = useState<SampleProject[]>(projectData);
  return (
    <div className="flex-initial">
      <ul className="flex flex-wrap justify-center items-center gap-[2.8rem] p-0">
        {project.map((items) => (
          <Link key={items.id} href="/recruit/LinkSample">
            <a className=" hover:scale-[1.03] duration-300 cursor-pointer px-10 pt-20  flex box-border justify-center flex-col w-[370px] h-[450px] rounded-3xl border-gray-300 border-[3px] border-solid">
              <li className="p-0 m-0">
                <div className="flex gap-3.5 text-lg text-neutral-400 ">
                  <p>시작 예정일 |</p>
                  <p>{items.year}</p>
                </div>
                <h1 className="my-[16px] min-h-[67px] text-2xl line-clamp-2">
                  {items.title}
                </h1>
                <ul className="flex gap-1 text-lg my-[30px] text-neutral-400">
                  {items.tag.map((item) => (
                    <li key={item} className="p-0 m-0">
                      {item}
                    </li>
                  ))}
                </ul>
                <ul className="flex gap-4 mb-9">
                  {/* 스킬 이미지는 어떻게 처리할 것인가 프론트에서 저장 아니면 서버에서 url내려주는지? skill이름도 같이 주는지 alt필요 */}
                  {items.skills.map((skills, idx) => (
                    <li key={idx} className="w-[48px] h-[48px]">
                      <img src={skills} alt="" />
                    </li>
                  ))}
                </ul>
                <section className="flex border-solid border-gray-300 border-t-[1px] pt-[16px] justify-between">
                  <div className="flex items-center">
                    <img
                      className="w-[36px] h-[36px] mr-[12px]"
                      src="https://hola-post-image.s3.ap-northeast-2.amazonaws.com/default.PNG"
                      alt=""
                    ></img>
                    <div className="text-lg font-normal">{items.write}</div>
                  </div>
                  <div className="flex gap-4">
                    <div className="flex items-center gap-1 ">
                      <AiOutlineEye className="w-[28px] h-[28px]" />
                      <p>{items.view}</p>
                    </div>
                    <div className="flex items-center gap-1">
                      <AiOutlineComment className=" w-[28px] h-[28px]" />
                      <p>{items.comment}</p>
                    </div>
                  </div>
                </section>
              </li>
            </a>
          </Link>
        ))}
      </ul>
    </div>
  );
};

export default RecruitCard;
