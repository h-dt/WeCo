import Image from 'next/image';

type props = {
  bgColor: string;
  btnText?: string;
  btnColor?: string;
  h2Text: string;
  SubspanText?: string;
  spanText: string;
  img: string;
};

export function SliderForm({
  bgColor,
  btnColor,
  btnText,
  h2Text,
  SubspanText,
  spanText,
  img,
}: props) {
  return (
    <div>
      <a className="w-full relative">
        <div className={bgColor}>
          <div className="flex max-w-7xl h-80 py-0 px-6 my-0 mx-auto">
            <div className="flex-1 my-auto mx-0">
              <button
                className={`font-bold py-1.5 px-8 ${btnColor} rounded-3xl mb-4`}
              >
                {btnText}
              </button>

              <div></div>
              <h2 className="inline-block text-4xl font-bold p-0 mx-0 my-2.5">
                {h2Text}
              </h2>
              <span className="text-4xl">{SubspanText}</span>
              <span className="block text-xl">{spanText}</span>
            </div>
            <div className="flex-1 flex justify-center items-center">
              <Image src={img} width="260" height="260" />
            </div>
          </div>
        </div>
      </a>
    </div>
  );
}
