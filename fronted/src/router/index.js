import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Protected from '@/components/common/Protected'
import Login from '@/components/board/Login'
import NoticeBoard from '@/components/board/NoticeBoard'
import SignUp from '@/components/board/SignUp'

import store from '../store'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/protected',
      name: 'Protected',
      component: Protected,
      // meta: {
      //   requiresAuth: true
      // }

      //리다이렉트 과정에서 error 에러 로그 나옴
      beforeEnter: function(to, from, next) {
        if (!store.getters.isLoggedIn) {
          console.log(!store.getters.isLoggedIn);
          next({
            name: 'HelloWorld'
          });
        } else {
          next()
        }
      }
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/SignUp',
      name: 'SignUp',
      component: SignUp,
    },
    {
      path: '/NoticeBoard',
      name: 'NoticeBoard',
      component: NoticeBoard,
      beforeEnter: function(to, from, next) {
        if (!store.getters.isLoggedIn) {
          console.log(!store.getters.isLoggedIn);
          next({
            name: 'Login'
          });
        } else {
          next()
        }
      }
    },
    {
      path: '*',
      redirect: '/'
    },
  ],
  mode: 'history',
  base: process.env.BASE_URL,
})
