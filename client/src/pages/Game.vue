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
        <IonButton expand="block" @click="startNewGame">New Game</IonButton>
      </div>
      <ActionButtons
        v-else
        @hit="hit"
        @stand="stand"
        @double="double"
        @split="split"
        @retry="onRetry"
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
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonButtons,
  IonBackButton,
  IonIcon,
  IonButton
} from '@ionic/vue'
import { gameControllerOutline } from 'ionicons/icons'

import DealerHand from '@/components/game/DealerHand.vue'
import PlayerHand from '@/components/game/PlayerHand.vue'
import ScoreDisplay from '@/components/game/ScoreDisplay.vue'
import ActionButtons from '@/components/game/ActionButtons.vue'
import { useBlackjack } from '@/composables/useBlackjack'

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
  canSplit
} = useBlackjack()

onMounted(() => {
  startNewGame()
})

</script>

<style scoped>
.game-over {
  margin-top: 2rem;
  text-align: center;
}

.status-message {
  font-size: 1.5rem;
  margin-bottom: 1rem;
  color: var(--ion-color-primary);
}
</style>
