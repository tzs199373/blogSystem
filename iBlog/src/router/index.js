import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Article from '@/components/Article'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: '/iblog/',

  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }, {
      path: '/article',
      name: 'Article',
      component: Article
    }
  ]
})
