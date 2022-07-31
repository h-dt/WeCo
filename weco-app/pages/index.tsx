import Head from 'next/head';
import Image from 'next/image';
import styles from '../styles/Home.module.css';
import RecruitMain from './recruit';
import RecruitCard from './recruit/RecruitCard';
import RecruitHeader from './recruit/RecruitHeader';

import { MainHeader } from '../components/MainHeader';
import { SkillSelect } from '../components/SkillSelect';
import type { NextPage } from 'next';

import { MainSlider } from '../components/MainSlider';

const Home: NextPage = () => {
  return (
    <>
      {/* <h1 className="text-3xl font-bold underline">Hello world!</h1> */}
      <RecruitMain />
      <h1 className="text-3xl font-bold underline">Hello world!</h1>

      <MainHeader />

      <MainSlider />

      <SkillSelect />
    </>
  );
};

export default Home;
