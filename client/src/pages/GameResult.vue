<template>
  <IonPage>
    <IonHeader>
      <IonToolbar>
        <IonButtons slot="start">
          <IonBackButton default-href="/"></IonBackButton>
        </IonButtons>
        <IonTitle>
          <IonIcon :icon="trophyOutline" class="ion-margin-end"></IonIcon>
          Game Results
        </IonTitle>
      </IonToolbar>
    </IonHeader>

    <IonContent class="ion-padding">
      <div class="game-results-content">
        <section class="result-section">
          <h2>Total Win/Loss</h2>
          <div class="stats">
            <div class="stat-item">
              <strong>{{ gameResults?.totalWins || 0 }}</strong>
              <span>Wins</span>
            </div>
            <div class="stat-item">
              <strong>{{ gameResults?.totalLosses || 0 }}</strong>
              <span>Losses</span>
            </div>
          </div>
        </section>

        <section class="result-section">
          <h2>Previous Games</h2>
          <div v-if="gameResults?.games?.length" class="games-list">
            <div v-for="game in gameResults.games" :key="game.id" class="game-item">
              <div class="game-info">
                <span class="game-result" :class="game.result">{{ game.result }}</span>
                <span class="game-score">Score: {{ game.playerScore }}</span>
                <span class="game-date">{{ new Date(game.playedAt).toLocaleDateString() }}</span>
              </div>
            </div>
          </div>
          <div v-else class="no-games">
            No games played yet
          </div>
        </section>
      </div>
    </IonContent>
  </IonPage>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonBackButton,
  IonButtons,
  IonIcon,
} from '@ionic/vue'
import { trophyOutline } from 'ionicons/icons'
import axios from 'axios'
import { BASE_PATH } from '@/api/base'

interface Game {
  id: number
  result: 'WIN' | 'LOSS' | 'PUSH'
  playerScore: number
  playedAt: string
}

interface GameResults {
  totalWins: number
  totalLosses: number
  games: Game[]
}

const route = useRoute()
const gameResults = ref<GameResults | null>(null)

onMounted(async () => {
  try {
    const sessionId = route.params.sessionId
    const response = await axios.get<GameResults>(`${BASE_PATH}/game-sessions/${sessionId}/game-results`)
    gameResults.value = response.data
  } catch (error) {
    console.error('Failed to fetch game results:', error)
  }
})
</script>

<style scoped>
.game-results-content {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  color: var(--ion-color-primary);
  margin-bottom: 30px;
  text-align: center;
}

h2 {
  color: var(--ion-color-primary);
  margin: 25px 0 15px;
  font-size: 1.5em;
}

.result-section {
  margin-bottom: 30px;
  padding: 20px;
  background: var(--ion-background-color);
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.stats {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.stat-item {
  text-align: center;
}

.stat-item strong {
  display: block;
  font-size: 2em;
  color: var(--ion-color-primary);
}

.stat-item span {
  color: var(--ion-color-medium);
}

.games-list {
  margin-top: 20px;
}

.game-item {
  padding: 15px;
  border-bottom: 1px solid var(--ion-color-light);
}

.game-item:last-child {
  border-bottom: none;
}

.game-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.game-result {
  font-weight: bold;
  padding: 4px 8px;
  border-radius: 4px;
}

.game-result.WIN {
  color: var(--ion-color-success);
  background: var(--ion-color-success-tint);
}

.game-result.LOSS {
  color: var(--ion-color-danger);
  background: var(--ion-color-danger-tint);
}

.game-result.PUSH {
  color: var(--ion-color-warning);
  background: var(--ion-color-warning-tint);
}

.game-score {
  color: var(--ion-color-dark);
}

.game-date {
  color: var(--ion-color-medium);
}

.no-games {
  text-align: center;
  color: var(--ion-color-medium);
  padding: 20px;
}
</style> 