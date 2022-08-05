import { Dispatch, SetStateAction } from 'react';
import { dataSkill } from '../SkillDataList';

type Props = {
  item: number;
  setItem: Dispatch<SetStateAction<number>>;
};

export function SkillCategory({ item, setItem }: Props) {
  const selectMenuHandler = (index: number) => {
    setItem(index);
  };
  return (
    <ul className="flex gap-14 border-b-2 border-slate-200 pb-5 pl-4 mb-8 ">
      {dataSkill.map((ele, index) => {
        return (
          <li
            key={index}
            onClick={() => selectMenuHandler(index)}
            className={`flex font-bold text-2xl cursor-pointer relative text-zinc-400  ${
              item === index
                ? "text-black after:content-[''] after:absolute after:h-1.5 after:w-[calc(100%+26px)] after:-left-4 after:-bottom-6 after:bg-yellow-500"
                : ''
            }`}
          >
            {ele.name}
          </li>
        );
      })}
    </ul>
  );
}
