<template>
  <IonGrid>
    <IonRow>
      <IonCol>
        <IonButton expand="block" @click="$emit('hit')">Hit</IonButton>
      </IonCol>
      <IonCol>
        <IonButton expand="block" @click="$emit('stand')">Stand</IonButton>
      </IonCol>
    </IonRow>
    <IonRow class="ion-padding-top">
      <IonCol>
        <IonButton expand="block" color="medium" @click="$emit('retry')">
          <template v-slot:start>
            <IonIcon name="refresh-outline" />
          </template>
          Play Again
        </IonButton>
      </IonCol>
      <IonCol>
        <IonButton expand="block" color="tertiary" @click="$emit('result')">
          <template v-slot:start>
            <IonIcon name="trophy-outline" />
          </template>
          Game Result
        </IonButton>
      </IonCol>
    </IonRow>
  </IonGrid>
</template>

<script setup lang="ts">
import { IonButton, IonCol, IonGrid, IonIcon, IonRow } from '@ionic/vue'

defineEmits<{
  (e: 'hit'): void
  (e: 'stand'): void
  (e: 'retry'): void
  (e: 'result'): void
}>()
</script>
