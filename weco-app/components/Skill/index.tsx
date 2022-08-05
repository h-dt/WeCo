import { useState } from 'react';
import { SelectResultList } from './SkillSelect/SelectResultList';
import { SkillCategory } from './SkillSelect/SkillCategory';
import { SkillSelectList } from './SkillSelect/SkillSelectList';

export function SkillSelect() {
  const [currentTab, setCurrentTab] = useState<number>(0);
  const [returnitem, setReturnItem] = useState<string[]>([]);
  return (
    <section className="max-w-7xl w-full py-0 px-4 my-28 mx-auto">
      <SkillCategory item={currentTab} setItem={setCurrentTab} />
      <SkillSelectList
        item={currentTab}
        returnItem={returnitem}
        returnSetItem={setReturnItem}
      />
      <SelectResultList returnItem={returnitem} returnSetItem={setReturnItem} />
    </section>
  );
}
