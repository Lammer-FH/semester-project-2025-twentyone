<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { IonPage, IonContent, IonItem, IonLabel, IonInput, IonButton, IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonSegment, IonSegmentButton, IonSpinner } from '@ionic/vue';
import { playerService } from '@/services/playerService';
import { setCurrentPlayer } from '@/stores/authStore';

const router = useRouter();
const selectedSegment = ref('login');
const userName = ref('');
const password = ref('');
const name = ref('');
const error = ref('');
const isLoading = ref(false);

const handleLogin = async () => {
  try {
    error.value = '';
    isLoading.value = true;

    const player = await playerService.login({
      userName: userName.value,
      password: password.value
    });

    setCurrentPlayer(player);
    router.push('/');
  } catch (err: any) {
    if (err.message?.includes('connect')) {
      error.value = 'Cannot connect to the server.';
    } else if (err.response?.status === 401) {
      error.value = 'Invalid username or password.';
    } else {
      error.value = 'Login failed.';
    }
  } finally {
    isLoading.value = false;
  }
};


const handleRegister = async () => {
    try {
        error.value = '';
        isLoading.value = true;
        console.log('Attempting registration with:', { userName: userName.value, name: name.value });

        const response = await playerService.createPlayer({
            userName: userName.value,
            name: name.value,
            passwordHash: password.value
        });

        console.log('Registration successful:', response);
        setCurrentPlayer(response);
        router.push('/');
    } catch (err: any) {
        console.error('Registration error:', err);
        if (err.message === 'Cannot connect to the server. Please make sure the server is running and try again.') {
            error.value = err.message;
        } else if (err.response?.status === 404) {
            error.value = 'Server is not responding. Please try again later.';
        } else if (err.response?.data?.message) {
            error.value = err.response.data.message;
        } else {
            error.value = 'Registration failed. Please try again.';
        }
    } finally {
        isLoading.value = false;
    }
};
</script>

<template>
    <ion-page>
        <ion-content class="ion-padding">
            <ion-card>
                <ion-card-header>
                    <ion-card-title class="ion-text-center">
                        <img src="@/assets/TwentyOneLogo.png" alt="Twenty-One Logo" class="logo">
                    </ion-card-title>
                </ion-card-header>
                <ion-card-content>
                    <ion-segment v-model="selectedSegment">
                        <ion-segment-button value="login">Login</ion-segment-button>
                        <ion-segment-button value="register">Register</ion-segment-button>
                    </ion-segment>

                    <form @submit.prevent="selectedSegment === 'login' ? handleLogin() : handleRegister()">
                        <ion-item>
                            <ion-label position="floating">Username</ion-label>
                            <ion-input v-model="userName" required></ion-input>
                        </ion-item>

                        <ion-item v-if="selectedSegment === 'register'">
                            <ion-label position="floating">Name</ion-label>
                            <ion-input v-model="name" required></ion-input>
                        </ion-item>

                        <ion-item>
                            <ion-label position="floating">Password</ion-label>
                            <ion-input type="password" v-model="password" required></ion-input>
                        </ion-item>

                        <div class="ion-padding">
                            <ion-button expand="block" type="submit" :disabled="isLoading">
                                <ion-spinner v-if="isLoading" name="crescent"></ion-spinner>
                                <span v-else>
                                    {{ selectedSegment === 'login' ? 'Login' : 'Register' }}
                                </span>
                            </ion-button>
                        </div>

                        <div v-if="error" class="ion-text-center ion-padding error-message">
                            {{ error }}
                        </div>
                    </form>
                </ion-card-content>
            </ion-card>
        </ion-content>
    </ion-page>
</template>

<style scoped>
.error-message {
    color: var(--ion-color-danger);
}

ion-spinner {
    width: 20px;
    height: 20px;
}

.logo {
    max-width: 200px;
    height: auto;
    margin: 0 auto;
    display: block;
}
</style>
