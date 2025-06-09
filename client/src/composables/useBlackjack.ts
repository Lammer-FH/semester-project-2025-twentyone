import { ref, computed } from 'vue';
import type { GameState } from '@/types/blackjack';
import { BlackjackService } from '@/services/blackjack.service';

const blackjackService = new BlackjackService();

export function useBlackjack() {
    const gameState = ref<GameState | null>(null);

    const playerCards = computed(() => gameState.value?.playerHand.map(card => card.code) ?? []);
    const dealerCards = computed(() => gameState.value?.dealerHand.map(card => card.code) ?? []);
    const playerScore = computed(() => gameState.value?.playerScore ?? 0);
    const dealerScore = computed(() => gameState.value?.dealerScore ?? 0);
    const gameStatus = computed(() => gameState.value?.gameStatus ?? 'playing');
    const isGameOver = computed(() => {
        const status = gameState.value?.gameStatus;
        return status !== 'playing';
    });

    const statusMessage = computed(() => {
        switch (gameState.value?.gameStatus) {
            case 'playerBust':
                return 'Bust! You went over 21!';
            case 'dealerBust':
                return 'Dealer busts! You win!';
            case 'playerWin':
                return 'Congratulations! You win!';
            case 'dealerWin':
                return 'Dealer wins!';
            case 'push':
                return 'Push! It\'s a tie!';
            default:
                return '';
        }
    });

    function startNewGame() {
        gameState.value = blackjackService.initializeGame();
    }

    function hit() {
        if (!gameState.value) return;
        gameState.value = blackjackService.playerHit(gameState.value);
    }

    function stand() {
        if (!gameState.value) return;
        gameState.value = blackjackService.playerStand(gameState.value);
    }

    return {
        playerCards,
        dealerCards,
        playerScore,
        dealerScore,
        gameStatus,
        isGameOver,
        statusMessage,
        startNewGame,
        hit,
        stand
    };
} 