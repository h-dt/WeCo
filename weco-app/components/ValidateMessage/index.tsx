import React from 'react';
type Props = {
  result?: any;
  [key: string]: any;
};
const ValidateMessage = ({ result, ...args }: Props) => {
  return (
    <>
      {result && (
        <span className="text-xs text-red-500" {...args}>
          {result.message || '필드를 확인해주세요.'}
        </span>
      )}
    </>
  );
};

export default React.memo(ValidateMessage);
