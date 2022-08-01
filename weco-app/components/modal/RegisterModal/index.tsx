import React, { useState } from 'react';
import Modal from 'react-modal';

import { AiFillCloseCircle as Close } from 'react-icons/ai';
import { IoArrowBack as Back } from 'react-icons/io5';

import LoginForm from './LoginForm';
import RegisterForm from './RegisterForm';

const ModalStyles: Modal.Styles = {
  overlay: {
    backgroundColor: 'rgba(0, 0, 0, 0.3)',
  },
  content: {
    position: 'absolute',
    background: '#fff',
    overflow: 'auto',
    WebkitOverflowScrolling: 'touch',
    borderRadius: '8px',
    outline: 'none',
    width: 800,
    left: '50%',
    top: '50%',
    transform: 'translate(-50%, -50%)',
  },
};
type Props = {
  isOpen: boolean;
  onClose: () => void;
  [key: string]: any;
};

const RegisterModal = ({ onClose, ...args }: Props) => {
  const [registerVisible, setRegisterVisible] = useState<boolean>(false);
  const handleModalRoute = () => {
    setRegisterVisible(!registerVisible);
  };
  return (
    <Modal
      style={ModalStyles}
      onRequestClose={onClose}
      ariaHideApp={false}
      className="sm:flex-1 sm:w-full sm:h-full sm:rounded-none"
      {...args}
    >
      {/* header */}
      <div className="flex justify-between items-center bg-gray-100 px-4 py-2">
        <div className="flex items-center">
          {registerVisible && (
            <Back size="20" onClick={handleModalRoute} className="mr-2" />
          )}
          <div>weco</div>
        </div>
        <div onClick={onClose}>
          <Close size="20" />
        </div>
      </div>

      <div className="py-20">
        <div className="text-center text-3xl font-bold tracking-tighter pb-6">
          WECO에 오신 것을 환영합니다!
        </div>
        {/* 로그인 */}
        {!registerVisible && <LoginForm handleModalRoute={handleModalRoute} />}
        {/* 회원가입 */}
        {registerVisible && <RegisterForm />}
      </div>
    </Modal>
  );
};
export default React.memo(RegisterModal);
