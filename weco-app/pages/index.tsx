import { MainHeader } from 'components/Header';
import { SkillSelect } from 'components/Skill';
import { MainSlider } from 'components/Slider';
import type { NextPage } from 'next';
import Head from 'next/head';
import Image from 'next/image';
import styles from '../styles/Home.module.css';
import RecruitMain from './recruit';
import RecruitCard from './recruit/RecruitCard';
import RecruitHeader from './recruit/RecruitHeader';

const Home: NextPage = () => {
  return (
    <>
      <MainHeader />
      <MainSlider />
      <SkillSelect />
      <RecruitMain />
    </>
  );
};

export default Home;
