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
                    {
                        id: 1,
                        name: '자유전공학부',
                        noticeUrl : ''
                    }
                ]
            },
            {
                id : 2,
                name : '인문대학',
                department : [
                    '한국어교육전공',
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
                department : []
            },
            {
                id: 5,
                name : '사회과학대학',
                department: []
            },
            {
                id: 6,
                name : '과학생명융합대학',
                department: []
            },
            {
                id: 7,
                name: '정보통신대학',
                department: [
                    '전자공학전공',
                    '전기공학전공',
                    '정보통신공학전공',
                    '컴퓨터공학전공',
                    '컴퓨터소프트웨어전공',
                    '정보보호전공'
                ]
            },
            {
                id: 8,
                name: '조형예술대학',
                department: []
            },
            {
                id:9,
                name : '사범대학',
                department: []
            },
            {
                id: 10,
                name: '재활과학대학',
                department: []
            },
            {
                id: 11,
                name : '간호대학',
                department: []
            },
            {
                id: 12,
                name: 'AI학부',
                department: []
            },
            {
                id: 13,
                name: '미래융합대학',
                department: []
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