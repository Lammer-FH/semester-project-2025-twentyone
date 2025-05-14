<script setup lang="ts">
defineProps<{
  msg: string
}>()
</script>

<template>
  <ion-card>
    <ion-card-header>
      <ion-card-title>{{ msg }}</ion-card-title>
      <ion-card-subtitle>Ein Test mit Ionic Komponenten</ion-card-subtitle>
    </ion-card-header>

    <ion-card-content>
      <ion-button expand="block" color="primary"> Klick mich </ion-button>

      <ion-toggle justify="space-between" class="ion-margin-top"> Schalter </ion-toggle>

      <ion-input label="Name" label-placement="floating" placeholder="Dein Name"></ion-input>

      <ion-item>
        <ion-label>Beispiel Select</ion-label>
        <ion-select interface="popover" placeholder="Waehlen">
          <ion-select-option value="1">Option A</ion-select-option>
          <ion-select-option value="2">Option B</ion-select-option>
        </ion-select>
      </ion-item>
    </ion-card-content>
  </ion-card>
</template>

<style scoped>
ion-card-title {
  font-size: 1.8rem;
}

ion-card-subtitle {
  font-size: 1rem;
  color: var(--ion-color-medium);
}
</style>
