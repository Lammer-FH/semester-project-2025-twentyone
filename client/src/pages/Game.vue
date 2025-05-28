<template>
  <IonPage>
    <IonHeader>
      <IonToolbar>
        <IonButtons slot="start">
          <IonBackButton default-href="/"></IonBackButton>
        </IonButtons>
        <IonTitle>
          <IonIcon :icon="gameControllerOutline" class="ion-margin-end"></IonIcon>
          Play Game
        </IonTitle>
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
import { computed, onMounted, ref } from 'vue'
import { 
  IonContent, 
  IonHeader, 
  IonPage, 
  IonTitle, 
  IonToolbar,
  IonButtons,
  IonBackButton,
  IonIcon,
} from '@ionic/vue'
import { gameControllerOutline } from 'ionicons/icons'

import DealerHand from '@/components/game/DealerHand.vue'
import PlayerHand from '@/components/game/PlayerHand.vue'
import ScoreDisplay from '@/components/game/ScoreDisplay.vue'
import ActionButtons from '@/components/game/ActionButtons.vue'
import { useRoute } from 'vue-router'
import type { GameSessionDto } from '@/api'
import { fetchGameSession } from '@/services/game-session.service.ts'

interface ExtendedGameSessionDto extends GameSessionDto {
  dealerCards: string[]
  playerCards: string[]
  playerScore: number
}

const route = useRoute()
const session = ref<ExtendedGameSessionDto | null>(null)
const dealerCards = computed(() => session.value?.dealerCards ?? [])
const playerCards = computed(() => session.value?.playerCards ?? [])
const playerScore = computed(() => session.value?.playerScore ?? 0)

// Daten aus BE fetchen
onMounted(async () => {
  const id = Number(route.params.id)
  const gameSession = await fetchGameSession(id)
  session.value = {
    ...gameSession,
    dealerCards: [],
    playerCards: [],
    playerScore: 0
  }
})

// aktuell mal noch mit mocks
function onHit() {
  if (session.value) {
    session.value.playerCards.push('5H')
    session.value.playerScore += 5
  }
}

// hier auch n mock
function onStand() {
  if (session.value) {
    session.value.dealerCards.push('6S')
  }
}
</script>

<style scoped></style>
