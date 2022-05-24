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