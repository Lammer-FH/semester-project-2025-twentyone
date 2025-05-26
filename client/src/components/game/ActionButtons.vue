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
  </IonGrid>
</template>

<script setup lang="ts">
import { IonButton, IonCol, IonGrid, IonRow } from '@ionic/vue'

defineEmits<{
  (e: 'hit'): void
  (e: 'stand'): void
}>()
</script>
