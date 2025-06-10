<template>
  <div class="scores">
    <div class="score">Player Score: {{ playerScore }}</div>
    <div class="score">Dealer Score: {{ dealerScore }}</div>
  </div>
</template>

<script setup lang="ts">
defineProps<{ 
  playerScore: number;
  dealerScore: number;
}>();
</script>

<style scoped>
.scores {
  display: flex;
  justify-content: space-around;
  margin: 1rem 0;
}

.score {
  font-size: 1.5rem;
  text-align: center;
}
</style>
