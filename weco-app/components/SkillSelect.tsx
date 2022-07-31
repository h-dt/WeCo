import { useState } from "react";
import Image from "next/image";

const dataSkill = [
  {
    name: "인기",
    value: [
      { img: "/img/javascript.svg", item: "JavaScript" },
      { img: "/img/typescript.svg", item: "TypeScript" },
      { img: "/img/react.svg", item: "React" },
      { img: "/img/vue.svg", item: "Vue" },
      { img: "/img/svelte.svg", item: "Svelte" },
      { img: "/img/nextjs.svg", item: "NextJs" },
      { img: "/img/nodejs.svg", item: "NodeJs" },
      { img: "/img/java.svg", item: "Java" },
      { img: "/img/spring.svg", item: "Spring" },
      { img: "/img/go.svg", item: "Go" },
    ],
  },
  {
    name: "프론트엔드",
    value: [
      { img: "/img/javascript.svg", item: "JavaScript" },
      { img: "/img/typescript.svg", item: "TypeScript" },
      { img: "/img/react.svg", item: "React" },
      { img: "/img/vue.svg", item: "Vue" },
      { img: "/img/nextjs.svg", item: "NextJs" },
      { img: "/img/svelte.svg", item: "Svelte" },
    ],
  },
  {
    name: "백엔드",
    value: [
      { img: "/img/java.svg", item: "Java" },
      { img: "/img/spring.svg", item: "Spring" },
      { img: "/img/nodejs.svg", item: "NodeJs" },
      { img: "/img/nestjs.svg", item: "NestJs" },
      { img: "/img/go.svg", item: "Go" },
      { img: "/img/kotlin.svg", item: "Kotlin" },
      { img: "/img/express.svg", item: "Express" },
      { img: "/img/mysql.svg", item: "MySQL" },
      { img: "/img/mongodb.svg", item: "MongoDB" },
      { img: "/img/python.svg", item: "Python" },
      { img: "/img/django.svg", item: "Django" },
      { img: "/img/php.svg", item: "PHP" },
      { img: "/img/graphql.svg", item: "GraphQL" },
      { img: "/img/firebase.svg", item: "FireBase" },
    ],
  },
  {
    name: "모바일",
    value: [
      { img: "/img/flutter.svg", item: "Flutter" },
      { img: "/img/swift.svg", item: "Swift" },
      { img: "/img/kotlin.svg", item: "Kotlin" },
      { img: "/img/reactnative.svg", item: "ReactNative" },
      { img: "/img/unity.svg", item: "Unity" },
    ],
  },
  {
    name: "기타",
    value: [
      { img: "/img/aws.svg", item: "AWS" },
      { img: "/img/kubernetes.svg", item: "Kubernetes" },
      { img: "/img/docker.svg", item: "Docker" },
      { img: "/img/git.svg", item: "Git" },
      { img: "/img/figma.svg", item: "Figma" },
      { img: "/img/zeplin.svg", item: "Zeplin" },
      { img: "/img/jest.svg", item: "Jest" },
      { img: "/img/c.svg", item: "C" },
    ],
  },
  {
    name: "모두보기",
    value: [
      { img: "/img/javascript.svg", item: "JavaScript" },
      { img: "/img/typescript.svg", item: "TypeScript" },
      { img: "/img/react.svg", item: "React" },
      { img: "/img/vue.svg", item: "Vue" },
      { img: "/img/nextjs.svg", item: "NextJs" },
      { img: "/img/svelte.svg", item: "Svelte" },
      { img: "/img/java.svg", item: "Java" },
      { img: "/img/spring.svg", item: "Spring" },
      { img: "/img/nodejs.svg", item: "NodeJs" },
      { img: "/img/nestjs.svg", item: "NestJs" },
      { img: "/img/go.svg", item: "Go" },
      { img: "/img/kotlin.svg", item: "Kotlin" },
      { img: "/img/express.svg", item: "Express" },
      { img: "/img/mysql.svg", item: "MySQL" },
      { img: "/img/mongodb.svg", item: "MongoDB" },
      { img: "/img/python.svg", item: "Python" },
      { img: "/img/django.svg", item: "Django" },
      { img: "/img/php.svg", item: "PHP" },
      { img: "/img/graphql.svg", item: "GraphQL" },
      { img: "/img/firebase.svg", item: "FireBase" },
      { img: "/img/flutter.svg", item: "Flutter" },
      { img: "/img/swift.svg", item: "Swift" },
      { img: "/img/kotlin.svg", item: "Kotlin" },
      { img: "/img/reactnative.svg", item: "ReactNative" },
      { img: "/img/unity.svg", item: "Unity" },
      { img: "/img/aws.svg", item: "AWS" },
      { img: "/img/kubernetes.svg", item: "Kubernetes" },
      { img: "/img/docker.svg", item: "Docker" },
      { img: "/img/git.svg", item: "Git" },
      { img: "/img/figma.svg", item: "Figma" },
      { img: "/img/zeplin.svg", item: "Zeplin" },
      { img: "/img/jest.svg", item: "Jest" },
      { img: "/img/c.svg", item: "C" },
    ],
  },
];

export function SkillSelect() {
  const newArray: string[] = [];
  const [currentTab, setCurrentTab] = useState(0);
  const [returnitem, setReturnItem] = useState<string[]>([]);
  const newList = dataSkill[currentTab].value;
  const selectMenuHandler = (index: number) => {
    setCurrentTab(index);
  };

  const onClick = (data: string) => {
    setReturnItem([...returnitem, data]);
    const deduplication = returnitem.includes(data);
    if (deduplication) {
      setReturnItem([...returnitem]);
    }
  };
  const onDelete = (x: string) => {
    const deleteItem = returnitem.indexOf(x);
    const cutone = returnitem.slice(0, deleteItem);
    const cuttwo = returnitem.slice(deleteItem + 1, returnitem.length);
    newArray.push(...cutone);
    newArray.push(...cuttwo);
    setReturnItem(newArray);
  };
  const onReset = () => {
    setReturnItem([]);
  };
  return (
    <section className="max-w-screen-xl w-full py-0 px-4 my-28 mx-auto">
      <ul className="flex gap-14 border-b-2 border-slate-200 pb-5 pl-4 mb-8 ">
        {dataSkill.map((ele, index) => {
          return (
            <li
              key={index}
              onClick={() => selectMenuHandler(index)}
              className={`flex font-bold text-2xl cursor-pointer relative text-zinc-400  ${
                currentTab === index
                  ? "text-black after:content-[''] after:absolute after:h-1.5 after:w-full after:-bottom-6 after:bg-yellow-500"
                  : ""
              }`}
            >
              {ele.name}
            </li>
          );
        })}
      </ul>
      <ul className="m-0 p-0 list-none flex flex-wrap w-full gap-3.5 mb-10">
        {newList.map((ele, index) => {
          return (
            <li
              key={index}
              onClick={() => onClick(ele.item)}
              className="flex gap-2.5 items-center border-2 border-slate-200 rounded-full px-5 py-3 ease-in duration-100 cursor-pointer"
            >
              <Image src={ele.img} width="36" height="36" />
              <span>{ele.item}</span>
            </li>
          );
        })}
      </ul>
      <ul className="flex flex-wrap items-center gap-6 list-none m-0 p-0">
        {returnitem.map((x) => (
          <li
            key={Math.random()}
            onClick={() => onDelete(x)}
            className="flex gap-2 bg-gray-200 px-3 py-2 h-10 rounded-lg font-medium text-xl text-gray-800 justify-center items-center hover:cursor-pointer hover:ease-in hover:duration-100"
          >
            <div>{x}</div>
            <Image src="/img/delete.svg" width="16" height="16" />
          </li>
        ))}
        {returnitem.length ? (
          <span className="text-xl cursor-pointer" onClick={onReset}>
            필터초기화
          </span>
        ) : null}
      </ul>
    </section>
  );
}
