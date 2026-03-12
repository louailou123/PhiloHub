import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '../stores/auth'
import Home from '../views/HomeViews/home.vue'
import Auth from '../views/HomeViews/Auth.vue'
import About from '../views/HomeViews/AboutView.vue'

const routes = [
  { path: '/', redirect: '/home' },
  { path: '/home', name: 'Home', component: Home },
  { path: '/auth', name: 'Auth', component: Auth },
  { path: '/about', name: 'About', component: About },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

// Simple navigation guard
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore()
  const publicPages = ['/home', '/auth', '/about']
  const authRequired = !publicPages.includes(to.path)

  if (authRequired && !authStore.isAuthenticated) {
    return next('/auth')
  }

  next()
})

export default router