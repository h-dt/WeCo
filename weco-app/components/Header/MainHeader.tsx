import Image from "next/image";

export function MainHeader() {
  return (
    <nav className="m-auto max-w-7xl flex justify-between items-center h-20 py-0 px-2.5">
      <Image src="/img/weco.png" width="150" height="150" />
      <div className="flex gap-8 items-center">
        <button className="text-xl font-semibold">새글 쓰기</button>
        <button className="text-xl font-semibold">로그인</button>
      </div>
    </nav>
  );
}
