export function MainHeader() {
  return (
    <nav className="m-auto max-w-6xl flex justify-between items-center h-20 py-0 px-2.5">
      <a className="w-28 h-8 text-4xl">WECO</a>
      <div className="flex gap-8 items-center">
        <button className="text-xl font-semibold">새글 쓰기</button>
        <button className="text-xl font-semibold">로그인</button>
      </div>
    </nav>
  );
}
