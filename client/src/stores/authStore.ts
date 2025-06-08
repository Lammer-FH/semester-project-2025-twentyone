import { reactive } from 'vue';
import type { Player } from '@/services/playerService';

interface AuthState {
    currentPlayer: Player | null;
    isAuthenticated: boolean;
}

export const authStore = reactive<AuthState>({
    currentPlayer: null,
    isAuthenticated: false,
});

export const setCurrentPlayer = (player: Player | null) => {
    authStore.currentPlayer = player;
    authStore.isAuthenticated = !!player;
};

export const logout = () => {
    authStore.currentPlayer = null;
    authStore.isAuthenticated = false;
}; 