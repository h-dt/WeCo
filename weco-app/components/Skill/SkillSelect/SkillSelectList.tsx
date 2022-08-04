import Image from 'next/image';
import { useState } from 'react';
import { dataSkill } from '../SkillDataList';
export function SkillSelectList({ item, returnItem, returnSetItem }: any) {
  const newList = dataSkill[item].value;
  const onClick = (data: string) => {
    returnSetItem([...returnItem, data]);
    const deduplication = returnItem.includes(data);
    if (deduplication) {
      returnSetItem(returnItem.filter((x: string) => x !== data));
    }
  };
  return (
    <ul className="m-0 p-0 list-none flex flex-wrap w-full gap-3.5 mb-10">
      {newList.map((ele, index) => {
        return (
          <li
            key={index}
            onClick={() => onClick(ele.item)}
            className={`flex gap-2.5 items-center border-2 border-slate-200 rounded-full px-5 py-2 hover:ease-in hover:scale-105 hover:duration-100 cursor-pointer ${
              returnItem.includes(ele.item) ? 'opacity-30' : ''
            }`}
          >
            <Image src={ele.img} width="36" height="36" />
            <span>{ele.item}</span>
          </li>
        );
      })}
    </ul>
  );
}
