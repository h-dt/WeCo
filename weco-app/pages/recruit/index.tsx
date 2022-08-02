import type { NextPage } from 'next';
import React from 'react';
import RecruitCard from './RecruitCard';
import RecruitHeader from './RecruitHeader';

const RecruitMain: NextPage = () => {
  return (
    <>
      <p>toogle test</p>
      <p>toogle test</p>
      <p>toogle test</p>
      <div className="px-0 py-[10px] mx-auto my-0 flex-col max-w-[1200px] w-[100%] min-h-[60rem]">
        <RecruitHeader />
        <RecruitCard />
      </div>
    </>
  );
};

export default RecruitMain;
