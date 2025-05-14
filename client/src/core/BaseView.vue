<script setup lang="ts">
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar } from '@ionic/vue'
</script>

<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-title>
          <slot name="default-view-title"></slot>
        </ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <slot name="default-view-body"></slot>
    </ion-content>
  </ion-page>
</template>
