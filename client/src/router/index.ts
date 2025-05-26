import { createRouter, createWebHistory } from '@ionic/vue-router'
import Index from '@/pages/Index.vue'
import About from '@/pages/About.vue'
import Game from '@/pages/Game.vue'

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
    path: '/game/:id',
    component: Game,
    name: 'game',
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
