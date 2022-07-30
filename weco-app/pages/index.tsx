import type { NextPage } from "next";
import { SkillSelect } from "../components/SkillSelect";

const Home: NextPage = () => {
  return (
    <>
      <h1 className="text-3xl font-bold underline">Hello world!</h1>
      <SkillSelect />
    </>
  );
};

export default Home;
