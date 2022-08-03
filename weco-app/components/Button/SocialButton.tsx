import React from 'react';
type Props = {
  src?: string;
  backgroundColor?: string;
};
const SocialButton = ({ src, backgroundColor = 'bg-white' }: Props) => {
  return (
    <button
      className={`flex justify-center items-center ${backgroundColor} rounded-xl drop-shadow-xl w-28 h-28`}
    >
      <embed src={src} className="w-12 h-12" />
    </button>
  );
};
export default React.memo(SocialButton);
