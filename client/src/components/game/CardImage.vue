<script setup lang="ts">
import { computed } from 'vue'
import { IonImg } from '@ionic/vue'

const props = defineProps<{ code: string }>()

const src = computed(() => new URL(`../../assets/cards/${props.code}.jpg`, import.meta.url).href)
</script>

<template>
  <!-- so lädt IonImg immer die aktuelle URL neu -->
  <IonImg :key="props.code" :src="src" :alt="props.code" class="ion-width-60 ion-height-90" />
</template>
<style>
ion-img {
  width: 60px;
  height: 90px;
  object-fit: cover;
}
</style>
