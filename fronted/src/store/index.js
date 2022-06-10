import axios from 'axios';
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        fruits : [],
        loginSuccess: false,
        loginError: false,
        userName: null,
        userPass: null,
        noticeBoard: [
            {
                id : 1,
                name : '성산교양대학',
                department : [
                    '자유전공학부',
                ]
            },
            {
                id : 2,
                name : '인문대학',
                department : [
                    '한국어교육학부',
                    '중국어중국학전공',
                    '일본어일본학전공',
                    '영어영문학전공',
                    '문화예술학부',
                    '체육학과',
                    '스포츠레저학과'
                ]
            },
            {
                id : 3,
                name : '법·행정대학',
                department : [
                    '법학부',
                    '행정학과',
                    '경찰학부',
                    '부동산·지적학과',
                    '군사학과'
                ]
            },
            {
                id: 4,
                name : '경영대학',
                department : [
                    '경영학전공',
                    '회계학전공',
                    '(구)무역학과전공',
                    '경제금융전공',
                    '금융보험전공',
                    '호텔관광전공',
                    '관광경영전공'
                ]
            },
            {
                id: 5,
                name : '사회과학대학',
                department: [
                    '사회학과',
                    '국제관계학과',
                    '미디어커뮤니케이션학과',
                    '심리학과',
                    '문헌정보학과',
                    '아동가정복지학과',
                    '청소년상담복지학과',
                    '사회복지학과',
                    '지역사회개발·복지학과'
                ]
            },
            {
                id: 6,
                name : '과학생명융합대학',
                department: [
                    '빅데이터학과',
                    '나노화학전공',
                    '생명과학전공',
                    '원예학전공',
                    '바이오산업전공',
                    '동물자원학과',
                    '산림자원학과',
                    '반려동물산업학과',
                ]
            },
            {
                id: 7,
                name: '공과대학',
                department: [
                    '건축공학과',
                    '건설시스템공학과',
                    '환경기술공학과',
                    '기계공학부',
                    '식품공학과',
                    '식품영양학과',
                    '생명공학과',
                    '화학공학과',
                    '조경학과',
                    '신소재에너지공학전공',
                    '에너지시스템공학전공',
                    '기계융복합공학과'
                ]
            },
            {
                id: 8,
                name: '정보통신대학',
                department: [
                    '전자공학전공',
                    '전기공학전공',
                    '정보통신공학전공',
                    '컴퓨터공학전공',
                    '컴퓨터소프트웨어전공',
                    '정보보호전공',
                    'IT융합학과',
                    '메카트로닉스공학과'
                ]
            },
            {
                id: 9,
                name: '조형예술대학',
                department: [
                    '아트앤디자인전공',
                    '영상애니매이션디자인학전공',
                    '생활조형디자인학전공',
                    '시각디자인전공',
                    '서비스디자인전공',
                    '산업디자인학과',
                    '패션디자인학과',
                    '실내건축디자인학과'
                ]
            },
            {
                id: 10,
                name : '사범대학',
                department: [
                    '국어교육과',
                    '영어교육과',
                    '역사교육과',
                    '일반사회교육과',
                    '지리교육과',
                    '유아교육과',
                    '특수교육과',
                    '초등특수교육과',
                    '유아특수교육과',
                    '수학교육과',
                    '물리교육과',
                    '화학교육과',
                    '생물교육과',
                    '지구과학교육과',
                    '교직부'
                ]
            },
            {
                id: 11,
                name: '재활과학대학',
                department: [
                    '직업재활학과',
                    '언어치료학과',
                    '재활심리학과',
                    '재활공학과',
                    '물리치료학과',
                    '작업치료학과',
                    '재활건강증진학과'
                ]
            },
            {
                id: 12,
                name : '간호대학',
                department: [
                    '간호학과',
                    '보건행정학과(야간)'
                ]
            },
            {
                id: 13,
                name: 'AI학부',
                department: [
                    'AI학부'
                ]
            },
            {
                id: 14,
                name: '미래융합대학',
                department: [
                    '미래융합학부',
                    '실버복지상담학전공',
                    '자산관리·6차산업학전공',
                    '평생교육·청소년학전공'
                ]
            }
            
        ]

    },
    mutations: {
        loginSuccess(state, {user, password}) {
            state.loginSuccess = true;
            state.userName = user
            state.userPass = password
        },
        loginError(state, {user, password}) {
            state.loginError = true,
            state.userName = user,
            state.userPass = password
        }
    },
    actions: {
        login: function({commit}, {user, password}) {
            try {
                axios.get('/api/login', {
                    auth: {
                        username: user,
                        password: password
                    }
                })
                .then((response) => {
                    if (response.status == 200) {
                        commit('loginSuccess', {
                            user: user,
                            paswword: password
                        })
                    }
                })
            } catch (err) {
                commit('loginError', {
                    userName: user
                });
                throw new Error(err)
            }
        }
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError,
        getUserName: state => state.userName,
        getUserPass: state => state.userPass,
    },
    modules: {

    }
})