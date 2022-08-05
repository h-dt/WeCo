import type { NextPage } from 'next';
import { MainHeader } from '../components/Header';
import { MainSlider } from '../components/Slider';
import { SkillSelect } from '../components/Skill';

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
