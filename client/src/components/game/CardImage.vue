<template>
  <IonImg :src="src" :alt="code" class="ion-width-60 ion-height-90" />
</template>

<script setup lang="ts">
import { IonImg } from '@ionic/vue'

const props = defineProps<{ code: string }>()
const src = new URL(`../../assets/cards/${props.code}.jpg`, import.meta.url).href
</script>
<style>
ion-img {
  width: 60px;
  height: 90px;
  object-fit: cover;
}
</style>
