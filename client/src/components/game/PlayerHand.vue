<template>
  <IonCard>
    <IonCardHeader>
      <IonCardTitle>Spieler</IonCardTitle>
    </IonCardHeader>
    <IonCardContent>
      <IonRow>
        <IonCol v-for="(card, idx) in cards" :key="idx" size="auto" class="ion-margin-end">
          <CardImage :code="card" />
        </IonCol>
      </IonRow>
    </IonCardContent>
  </IonCard>
</template>

<script setup lang="ts">
import { IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonCol, IonRow } from '@ionic/vue'
import CardImage from '@/components/game/CardImage.vue'

const props = defineProps<{ cards: string[] }>()
</script>

<style scoped></style>
