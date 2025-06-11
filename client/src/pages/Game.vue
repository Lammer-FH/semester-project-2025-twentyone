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
          <IonIcon :icon="gameControllerOutline" class="ion-margin-end"></IonIcon>
          Play Game
        </IonTitle>
      </IonToolbar>
    </IonHeader>

    <IonContent class="ion-padding">
      <DealerHand :cards="dealerCards" />
      <PlayerHand :cards="playerCards" />
      <ScoreDisplay :player-score="playerScore" :dealer-score="dealerScore" />

      <div v-if="isGameOver" class="game-over">
        <h2 class="status-message">{{ statusMessage }}</h2>
        <div class="button-alignment">
          <IonButton expand="block" @click="startNewGame">Play Again</IonButton>
          <IonButton expand="block" color="medium" @click="router.push('/game-results')"
            >Game Results</IonButton
          >
        </div>
      </div>

      <ActionButtons
        v-else
        @hit="hit"
        @stand="stand"
        @double="double"
        @split="split"
        :can-hit="true"
        :can-stand="true"
        :can-double="canDouble"
        :can-split="canSplit"
      />
    </IonContent>
  </IonPage>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
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
import { gameControllerOutline } from 'ionicons/icons'

import DealerHand from '@/components/game/DealerHand.vue'
import PlayerHand from '@/components/game/PlayerHand.vue'
import ScoreDisplay from '@/components/game/ScoreDisplay.vue'
import ActionButtons from '@/components/game/ActionButtons.vue'
import { useBlackjack } from '@/composables/useBlackjack'
import router from '@/router'

const {
  playerCards,
  dealerCards,
  playerScore,
  dealerScore,
  isGameOver,
  statusMessage,
  startNewGame,
  hit,
  stand,
  double,
  split,
  canDouble,
  canSplit,
} = useBlackjack()

onMounted(() => {
  startNewGame(123)
})
</script>

<style scoped>
.game-over {
  margin-top: 2rem;
  text-align: center;
}
.button-alignment {
  display: flex;
  justify-content: center;
}

.status-message {
  font-size: 1.5rem;
  margin-bottom: 1rem;
  color: var(--ion-color-primary);
}
</style>
