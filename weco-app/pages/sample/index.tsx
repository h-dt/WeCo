import React, { useState } from 'react';
import RegisterModal from '../../components/modal/RegisterModal';
/**
 * 로그인 모달창을 확인하기 위한 페이지입니다.
 */
const SamplePage = () => {
  const [modalVisible, setModalVisible] = useState<boolean>(false);
  const handleCLick = () => {
    setModalVisible(true);
  };
  return (
    <>
      <button onClick={handleCLick}>로그인하기</button>
      <RegisterModal
        isOpen={modalVisible}
        onClose={() => setModalVisible(!modalVisible)}
      />
    </>
  );
};
export default React.memo(SamplePage);
