import { createRouter, createWebHistory } from '@ionic/vue-router'
import Index from '@/pages/Index.vue'
import About from '@/pages/About.vue'

const routes = [
  {
    path: '/',
    component: Index,
  },
  {
    path: '/about',
    component: About,
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
