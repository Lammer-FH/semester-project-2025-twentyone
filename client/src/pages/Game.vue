<template>
  <IonPage>
    <IonHeader>
      <IonToolbar>
        <IonTitle>Play Game</IonTitle>
      </IonToolbar>
    </IonHeader>

    <IonContent class="ion-padding">
      <DealerHand :cards="dealerCards" />
      <PlayerHand :cards="playerCards" />
      <ScoreDisplay :score="playerScore" />
      <ActionButtons @hit="onHit" @stand="onStand" />
    </IonContent>
  </IonPage>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/vue'

import DealerHand from '@/components/game/DealerHand.vue'
import PlayerHand from '@/components/game/PlayerHand.vue'
import ScoreDisplay from '@/components/game/ScoreDisplay.vue'
import ActionButtons from '@/components/game/ActionButtons.vue'

// mocks
const session = ref({
  id: 1,
  playerCards: ['AH', '7D'],
  dealerCards: ['10C'],
  playerScore: 18,
})

const dealerCards = computed(() => session.value.dealerCards)
const playerCards = computed(() => session.value.playerCards)
const playerScore = computed(() => session.value.playerScore)

// aktuell mal noch mit mocks
function onHit() {
  session.value.playerCards.push('5H')
  session.value.playerScore += 5
}

// hier auch n mock
function onStand() {
  session.value.dealerCards.push('6S')
}
</script>

<style scoped></style>
