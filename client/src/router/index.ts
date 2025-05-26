import { createRouter, createWebHistory } from '@ionic/vue-router'
import Index from '@/pages/Index.vue'
import About from '@/pages/About.vue'
import Rules from '@/pages/Rules.vue'

const routes = [
  {
    path: '/',
    component: Index,
  },
  {
    path: '/about',
    component: About,
  },
  {
    path: '/rules',
    component: Rules,
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
