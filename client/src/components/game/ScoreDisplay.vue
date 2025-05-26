<template>
  <div class="score">Punkte: {{ score }}</div>
</template>

<script setup lang="ts">
defineProps<{ score: number }>()
</script>

<style scoped>
.score {
  font-size: 1.5rem;
  text-align: center;
  margin: 1rem 0;
}
</style>
