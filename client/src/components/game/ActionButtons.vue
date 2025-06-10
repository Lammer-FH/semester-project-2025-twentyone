<template>
  <IonGrid>
    <IonRow>
      <IonCol>
        <IonButton expand="block" @click="$emit('hit')" :disabled="!canHit">Hit</IonButton>
      </IonCol>
      <IonCol>
        <IonButton expand="block" @click="$emit('stand')" :disabled="!canStand">Stand</IonButton>
      </IonCol>
    </IonRow>
    <IonRow>
      <IonCol>
        <IonButton
          expand="block"
          @click="$emit('double')"
          :disabled="!canDouble"
          color="secondary"
        >Double</IonButton>
      </IonCol>
      <IonCol>
        <IonButton
          expand="block"
          @click="$emit('split')"
          :disabled="!canSplit"
          color="tertiary"
        >Split</IonButton>
      </IonCol>
    </IonRow>
  </IonGrid>
</template>

<script setup lang="ts">
import { IonButton, IonCol, IonGrid, IonIcon, IonRow } from '@ionic/vue'

defineProps<{
  canHit: boolean;
  canStand: boolean;
  canDouble: boolean;
  canSplit: boolean;
}>();

defineEmits<{
  (e: 'hit'): void;
  (e: 'stand'): void;
  (e: 'double'): void;
  (e: 'split'): void;
}>();
</script>
