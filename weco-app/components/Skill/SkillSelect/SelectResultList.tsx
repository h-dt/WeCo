import Image from 'next/image';

export function SelectResultList({ returnItem, returnSetItem }: any) {
  const newArray: string[] = [];
  const onDelete = (x: string) => {
    const deleteItem = returnItem.indexOf(x);
    const cutone = returnItem.slice(0, deleteItem);
    const cuttwo = returnItem.slice(deleteItem + 1, returnItem.length);
    newArray.push(...cutone);
    newArray.push(...cuttwo);
    returnSetItem(newArray);
  };
  const onReset = () => {
    returnSetItem([]);
  };
  return (
    <ul className="flex flex-wrap items-center gap-6 list-none m-0 p-0">
      {returnItem.map((x: string, index: number) => (
        <li
          key={index}
          onClick={() => onDelete(x)}
          className="flex gap-2 bg-gray-200 px-3 py-2 h-10 rounded-lg font-medium text-xl text-gray-800 justify-center items-center hover:cursor-pointer hover:ease-in hover:scale-105 hover:duration-100"
        >
          <div>{x}</div>
          <Image src="/img/delete.svg" width="16" height="16" />
        </li>
      ))}
      {returnItem.length ? (
        <span
          className="text-xl cursor-pointer hover:ease-in hover:scale-105 hover:duration-100"
          onClick={onReset}
        >
          필터초기화
        </span>
      ) : null}
    </ul>
  );
}
