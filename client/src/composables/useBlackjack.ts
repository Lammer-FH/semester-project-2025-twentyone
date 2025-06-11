// src/composables/useBlackjack.ts
import { computed, ref } from 'vue'
import type { GameState } from '@/types/blackjack'
import { BlackjackService } from '@/services/blackjack.service'
import type { MoveDto } from '@/api'
import { createGameSession } from '@/services/game-session.service'
import { createMoveForSession, fetchMovesForSession } from '@/services/moves.service'
import { authStore } from '@/stores/authStore.ts'

const blackjackService = new BlackjackService()

export function useBlackjack() {
  // ——— State ———
  const gameState = ref<GameState | null>(null)
  const sessionId = ref<number | null>(null)
  const moves = ref<MoveDto[]>([])

  // ——— Computed Props ———
  const playerCards = computed(() => gameState.value?.playerHand.map((c) => c.code) ?? [])
  const dealerCards = computed(() => gameState.value?.dealerHand.map((c) => c.code) ?? [])
  const playerScore = computed(() => gameState.value?.playerScore ?? 0)
  const dealerScore = computed(() => gameState.value?.dealerScore ?? 0)
  const gameStatus = computed(() => gameState.value?.gameStatus ?? 'playing')
  const isGameOver = computed(() => gameStatus.value !== 'playing')
  const canDouble = computed(() => gameState.value?.canDouble ?? false)
  const canSplit = computed(() => gameState.value?.canSplit ?? false)

  const statusMessage = computed(() => {
    switch (gameStatus.value) {
      case 'playerBust':
        return 'Bust! You went over 21!'
      case 'dealerBust':
        return 'Dealer busts! You win!'
      case 'playerWin':
        return 'Congratulations! You win!'
      case 'dealerWin':
        return 'Dealer wins!'
      case 'push':
        return `Push! It's a tie!`
      default:
        return ''
    }
  })

  /** 1) Backend-Session anlegen, 2) Spiel starten, 3) Move-Log leeren */
  async function startNewGame() {
    console.log('currentPlayer in authStore:', authStore.currentPlayer)

    const playerId = authStore.currentPlayer?.id
    if (!playerId) {
      console.warn('Kein angemeldeter Spieler vorhanden!')
      return
    }

    // 1) Backend-Session anlegen mit der ID aus dem Store
    const session = await createGameSession({ playerId })
    if (!session) return
    console.log('neue Session:', session)

    // 2) local init
    sessionId.value = session.id!
    gameState.value = blackjackService.initializeGame()
    console.log(
      'Neue Start-Hand:',
      gameState.value.playerHand.map((c) => c.code),
    )
    moves.value = []
  }

  /** Alle bisherigen Züge aus der DB laden (z.B. beim Resume) */
  async function loadMoves() {
    if (!sessionId.value) return
    moves.value = await fetchMovesForSession(sessionId.value)
  }

  /** Hit: lokal verarbeiten + in DB speichern */
  async function hit() {
    if (!gameState.value || gameStatus.value !== 'playing') return
    gameState.value = blackjackService.playerHit(gameState.value)
    if (sessionId.value) {
      const m = await createMoveForSession(sessionId.value, 'HIT')
      if (m) moves.value.push(m)
    }
  }

  /** Stand: lokal verarbeiten + in DB speichern */
  async function stand() {
    if (!gameState.value || gameStatus.value !== 'playing') return
    gameState.value = blackjackService.playerStand(gameState.value)
    if (sessionId.value) {
      const m = await createMoveForSession(sessionId.value, 'STAND')
      if (m) moves.value.push(m)
    }
  }

  /** Double: lokal verarbeiten + in DB speichern */
  async function double() {
    if (!gameState.value || gameStatus.value !== 'playing') return
    gameState.value = blackjackService.playerDouble(gameState.value)
    if (sessionId.value) {
      const m = await createMoveForSession(sessionId.value, 'DOUBLE')
      if (m) moves.value.push(m)
    }
  }

  /** Split: lokal verarbeiten + in DB speichern */
  async function split() {
    if (!gameState.value || gameStatus.value !== 'playing') return
    gameState.value = blackjackService.playerSplit(gameState.value)
    if (sessionId.value) {
      const m = await createMoveForSession(sessionId.value, 'SPLIT')
      if (m) moves.value.push(m)
    }
  }

  return {
    // State / Computed
    playerCards,
    dealerCards,
    playerScore,
    dealerScore,
    gameStatus,
    isGameOver,
    canDouble,
    canSplit,
    statusMessage,
    moves,
    // Actions
    startNewGame,
    loadMoves,
    hit,
    stand,
    double,
    split,
  }
}
