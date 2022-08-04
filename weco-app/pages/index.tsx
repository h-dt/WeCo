import type { NextPage } from 'next';
import { MainHeader } from '../components/Header/MainHeader';
import { MainSlider } from '../components/Slider/MainSlider';
import { SkillSelect } from '../components/Skill/SkillSelect';

const Home: NextPage = () => {
  return (
    <>
      <MainHeader />
      <MainSlider />
      <SkillSelect />
    </>
  );
};

export default Home;
