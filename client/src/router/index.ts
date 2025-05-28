import { createRouter, createWebHistory } from '@ionic/vue-router'
import Index from '@/pages/Index.vue'
import About from '@/pages/About.vue'
import Game from '@/pages/Game.vue'
import Rules from '@/pages/Rules.vue'
import GameResult from '@/pages/GameResult.vue'

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
  {
    path: '/rules',
    name: 'rules',
    component: Rules,
  },
  {
    path: '/game-result/:sessionId',
    name: 'game-result',
    component: GameResult,
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
