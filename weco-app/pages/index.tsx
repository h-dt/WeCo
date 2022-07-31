import type { NextPage } from "next";
import { MainHeader } from "../components/MainHeader";
import { MainSlider } from "../components/MainSlider";
import { SkillSelect } from "../components/SkillSelect";

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
