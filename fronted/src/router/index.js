import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Protected from '@/components/common/Protected'

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
    }
  ],
  mode: 'history',
})
