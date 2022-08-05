import React from 'react';
import { useForm } from 'react-hook-form';

import { getLoginFormSchema } from 'utils/validations/loginValidation';

import SocialButton from 'components/Button/SocialButton';
import ValidateMessage from 'components/ValidateMessage';

type Props = {
  handleModalRoute: () => void;
};
type FormTypes = {
  username: string;
  password: string;
};
const LoginForm = ({ handleModalRoute }: Props) => {
  const {
    register,
    setValue,
    handleSubmit,
    formState: { errors },
  } = useForm<FormTypes>({
    resolver: getLoginFormSchema(),
  });

  /**
   * handlers
   */
  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setValue(name, value, { shouldValidate: true });
  };
  const handleLogin = () => {
    console.log('로그인 클릭');
  };
  return (
    <>
      <div className="p-4">
        <form className="max-w-sm mx-auto">
          <div className="grid gap-y-2 mb-6">
            <input
              type="text"
              className="w-full px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded focus:text-gray-700 focus:bg-white focus:border-yellow-400 focus:outline-none"
              placeholder="Username을 입력해주세요."
              {...register('username')}
              onChange={handleChange}
            />
            {errors.username && <ValidateMessage result={errors.username} />}
            <input
              type="password"
              className="w-full px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded focus:text-gray-700 focus:bg-white focus:border-yellow-400 focus:outline-none"
              placeholder="비밀번호를 입력해주세요."
              {...register('password')}
              onChange={handleChange}
            />
            {errors.password && <ValidateMessage result={errors.password} />}
          </div>
          <button
            type="submit"
            className="inline-block px-7 py-3 bg-yellow-400 text-white leading-snug rounded shadow-md hover:bg-yellow-400 hover:shadow-lg focus:bg-yellow-400 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-yellow-400 w-full"
            onClick={handleSubmit(handleLogin)}
          >
            로그인
          </button>

          <div className="flex items-center my-4 before:flex-1 before:border-t before:border-gray-300 before:mt-0.5 after:flex-1 after:border-t after:border-gray-300 after:mt-0.5">
            <p className="text-center font-semibold mx-4 mb-0">OR</p>
          </div>

          <div className="flex justify-between">
            <SocialButton src="/assets/google_logo.svg" />
            <SocialButton
              src="/assets/github_logo.svg"
              backgroundColor="bg-black"
            />
            <SocialButton
              src="/assets/kakao_logo.svg"
              backgroundColor="bg-kakao"
            />
          </div>
        </form>
      </div>
      <div className="text-center mt-4" onClick={handleModalRoute}>
        회원가입
      </div>
    </>
  );
};
export default React.memo(LoginForm);
