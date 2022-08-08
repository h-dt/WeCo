import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';

export const getRegisterFormSchema = () =>
  yupResolver(
    yup.object({
      username: yup.string().required('Username을 정확하게 입력해주세요.'),
      password: yup
        .string()
        .trim()
        .required('비밀번호를 입력해주세요.')
        .min(8, '최소 8자 이상 입력해주세요.')
        .max(16, '최대 16자를 입력해주세요.')
        .matches(
          /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[~`!@#$%^&*()-+=]).*$/,
          '비밀번호 생성 조건에 맞지 않습니다. 다시 입력해주세요.'
        ),
      passwordConfirm: yup
        .string()
        .trim()
        .required('비밀번호를 확인해주세요.')
        .oneOf(
          [yup.ref('password')],
          '비밀번호가 일치하지 않습니다. 다시 입력해주세요.'
        ),
    })
  );
