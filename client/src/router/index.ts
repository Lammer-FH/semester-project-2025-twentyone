import { createRouter, createWebHistory } from '@ionic/vue-router'
import { authStore } from '@/stores/authStore'
import Index from '@/pages/Index.vue'
import About from '@/pages/About.vue'
import Game from '@/pages/Game.vue'
import Rules from '@/pages/Rules.vue'
import GameResult from '@/pages/GameResult.vue'
import Login from '@/pages/Login.vue'
import Profile from '@/pages/Profile.vue'
import GameResults from "@/pages/GameResults.vue";

const routes = [
  {
    path: '/login',
    component: Login,
    name: 'login',
  },
  {
    path: '/',
    component: Index,
    meta: { requiresAuth: true },
  },
  {
    path: '/about',
    component: About,
    meta: { requiresAuth: true },
  },
  {
    path: '/game/:id',
    component: Game,
    name: 'game',
    props: true,
    meta: { requiresAuth: true },
  },
  {
    path: '/rules',
    name: 'rules',
    component: Rules,
    meta: { requiresAuth: true },
  },
  {
    path: '/game-result/:sessionId',
    name: 'game-result',
    component: GameResult,
    props: true,
    meta: { requiresAuth: true },
  },
  {
    path: '/game-results',
    name: 'game-results',
    component: GameResults,
    meta: { requiresAuth: true },
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
    meta: { requiresAuth: true },
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/login')
  } else {
    next()
  }
})

export default router
