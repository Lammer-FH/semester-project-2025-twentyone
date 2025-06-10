<template>
  <IonPage>
    <IonHeader>
      <IonToolbar>
        <template v-slot:start>
          <IonButtons>
            <IonBackButton default-href="/"></IonBackButton>
          </IonButtons>
        </template>
        <IonTitle>
          <IonIcon :icon="trophyOutline" class="ion-margin-end"></IonIcon>
          Game Results
        </IonTitle>
      </IonToolbar>
    </IonHeader>

    <IonContent class="ion-padding">
      <div class="game-results-content">
        <section class="result-section" v-if="gameResult">
          <h2>Game Outcome</h2>
          <div class="game-info">
            <div class="outcome-display">
              <span class="game-result" :class="gameResult.outcome">{{ gameResult.outcome }}</span>
            </div>
            <div class="payout-display">
              <strong>Payout:</strong>
              <span class="payout-amount">${{ gameResult.payout.toFixed(2) }}</span>
            </div>
          </div>
        </section>
        <div v-else-if="hasError" class="error-message">
          <IonIcon :icon="alertCircleOutline" class="error-icon"></IonIcon>
          <p>Unable to load game results</p>
          <IonButton router-link="/" class="ion-margin-top">Return to Home</IonButton>
        </div>
        <div v-else class="no-result">Loading game result...</div>
      </div>
    </IonContent>
  </IonPage>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import {
  IonBackButton,
  IonButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonIcon,
  IonPage,
  IonTitle,
  IonToolbar,
} from '@ionic/vue'
import { alertCircleOutline, trophyOutline } from 'ionicons/icons'
import axios from 'axios'
import { BASE_PATH } from '@/api/base'
import { showToast } from '@/composables/useToast'

interface GameResult {
  id: number
  gameSessionId: number
  outcome: string
  payout: number
}

const route = useRoute()
const gameResult = ref<GameResult | null>(null)
const hasError = ref(false)

onMounted(async () => {
  try {
    const sessionId = route.params.sessionId
    const response = await axios.get<GameResult>(
      `${BASE_PATH}/game-sessions/${sessionId}/game-results`,
    )
    gameResult.value = response.data
    hasError.value = false
  } catch (error: any) {
    hasError.value = true
    if (error.response?.status === 404) {
      await showToast('Game session not found', 'danger')
    } else {
      await showToast('Failed to fetch game result', 'danger')
    }
    console.error('Failed to fetch game result:', error)
  }
})
</script>

<style scoped>
.game-results-content {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.result-section {
  margin-bottom: 30px;
  padding: 20px;
  background: var(--ion-background-color);
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  color: var(--ion-color-primary);
  margin: 0 0 20px;
  text-align: center;
}

.game-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.outcome-display {
  text-align: center;
}

.game-result {
  font-size: 1.5em;
  font-weight: bold;
  padding: 8px 16px;
  border-radius: 4px;
}

.game-result.WIN {
  color: #ffffff;
  background: var(--ion-color-success);
  font-weight: bold;
}

.game-result.LOSS {
  color: #ffffff;
  background: var(--ion-color-danger);
  font-weight: bold;
}

.payout-display {
  font-size: 1.2em;
  text-align: center;
}

.payout-amount {
  color: var(--ion-color-success);
  margin-left: 8px;
  font-weight: bold;
}

.no-result {
  text-align: center;
  color: var(--ion-color-medium);
  padding: 20px;
}

.error-message {
  text-align: center;
  padding: 2rem;
  color: var(--ion-color-danger);
}

.error-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.error-message p {
  font-size: 1.2rem;
  margin: 1rem 0;
}
</style>
