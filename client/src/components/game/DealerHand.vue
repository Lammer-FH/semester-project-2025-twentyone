<template>
  <IonCard>
    <IonCardHeader>
      <IonCardTitle>Dealer</IonCardTitle>
    </IonCardHeader>
    <IonCardContent>
      <IonRow>
        <IonCol v-for="(card, idx) in cards" :key="idx">
          {{ card }}
        </IonCol>
      </IonRow>
    </IonCardContent>
  </IonCard>
</template>

<script setup lang="ts">
import { IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonCol, IonRow } from '@ionic/vue'

defineProps<{ cards: string[] }>()
</script>
