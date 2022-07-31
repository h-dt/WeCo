import type { NextPage } from "next";
import { MainHeader } from "../components/MainHeader";
import { SkillSelect } from "../components/SkillSelect";

const Home: NextPage = () => {
  return (
    <>
      <MainHeader />
      <SkillSelect />
    </>
  );
};

export default Home;
