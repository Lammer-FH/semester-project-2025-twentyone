<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <template v-slot:start>
          <ion-buttons>
            <ion-back-button default-href="/"></ion-back-button>
          </ion-buttons>
        </template>
        <ion-title>Game Results</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content class="ion-padding">
      <h2 class="win-loss-count">
        <ion-icon :icon="trophy" size="large" class="ion-margin-end"></ion-icon>
        Total Win/Loss {{ winCount }}/{{ totalCount }}
      </h2>

      <!--      Filterbar -->
      <div class="filter-bar">
        <ion-segment v-model="selectedFilter">
          <ion-segment-button value="ALL">All</ion-segment-button>
          <ion-segment-button value="WIN">Win</ion-segment-button>
          <ion-segment-button value="LOSS">Loss</ion-segment-button>
          <ion-segment-button value="PUSH">Push</ion-segment-button>
        </ion-segment>
      </div>

      <!--      Items in der jeweiligen Kategorie -->
      <ion-list>
        <ion-item v-for="(entry, index) in filteredResults" :key="entry.gameSessionId">
          <ion-label>
            <h3>Game {{ totalCount - index }}</h3>
            <p>{{ formatDateTime(entry.startTime!) }} Uhr</p>
          </ion-label>
        </ion-item>
      </ion-list>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { computed, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import {
  IonBackButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonPage,
  IonSegment,
  IonSegmentButton,
  IonTitle,
  IonToolbar,
} from '@ionic/vue'
import { trophy } from 'ionicons/icons'
import { authStore } from '@/stores/authStore'
import type { GameResultOverviewDto, GameResultOverviewDtoOutcomeEnum } from '@/api'
import { fetchResultsForPlayer } from '@/services/game-result.service.ts'

const router = useRouter()
const results = ref<GameResultOverviewDto[]>([])
const selectedFilter = ref<'ALL' | GameResultOverviewDtoOutcomeEnum>('ALL')

const filteredResults = computed(() => {
  if (selectedFilter.value === 'ALL') return results.value
  return results.value.filter((r) => r.outcome === selectedFilter.value)
})

const winCount = computed(() => results.value.filter((r) => r.outcome === 'WIN').length)
const totalCount = computed(() => results.value.length)

onMounted(async () => {
  if (!authStore.currentPlayer?.id) {
    router.push('/login')
    return
  }

  results.value = await fetchResultsForPlayer(authStore.currentPlayer.id)
})

function formatDateTime(iso: string): string {
  return new Date(iso)
    .toLocaleString('de-DE', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    })
    .replace(/\./g, ':')
    .replace(',', ' -')
}
</script>

<style scoped>
.win-loss-count {
  display: flex;
  align-items: center;
  justify-content: center;
}

.filter-bar {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 1rem 0;
}
</style>
