/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx}',
    './components/**/*.{js,ts,jsx,tsx}',
  ],
  theme: {
    screens: {
      ssm: { max: '554px' },

      xm: { max: '554px' },
      sm: { max: '767px' },
      // => @media (min-width: 640px and max-width: 767px) { ... }
    },

    extend: {
      colors: {
        kakao: '#FEE500',
      },
    },
  },

  plugins: [require('@tailwindcss/line-clamp')],
};
