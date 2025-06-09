<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonItem, IonLabel, IonInput, IonButton, IonButtons, IonBackButton, IonAlert } from '@ionic/vue';
import { playerService } from '@/services/playerService';
import { authStore, logout } from '@/stores/authStore';
import type { Player } from '@/services/playerService';

const router = useRouter();
const player = ref<Player | null>(null);
const showDeleteConfirm = ref(false);
const isEditing = ref(false);
const error = ref('');
const newPassword = ref('');

onMounted(() => {
    if (!authStore.isAuthenticated) {
        router.push('/login');
        return;
    }
    player.value = { ...authStore.currentPlayer! };
});

const handleUpdate = async () => {
    try {
        if (!player.value || !player.value.id) return;
        
        const updatedPlayer = await playerService.updatePlayer(player.value.id, {
            ...player.value,
            passwordHash: newPassword.value
        });
        
        // Update the store with the new player data
        authStore.currentPlayer = updatedPlayer;
        isEditing.value = false;
        error.value = '';
        newPassword.value = ''; // Clear the password field after successful update
    } catch (err) {
        error.value = 'Failed to update profile. Please try again.';
    }
};

const handleDelete = async () => {
    try {
        if (!player.value || !player.value.id) return;
        
        await playerService.deletePlayer(player.value.id);
        showDeleteConfirm.value = false;
        logout();
        router.push('/login');
    } catch (err) {
        error.value = 'Failed to delete account. Please try again.';
        showDeleteConfirm.value = false;
    }
};

const handleLogout = () => {
    logout();
    router.push('/login');
};

const startEditing = () => {
    isEditing.value = true;
    newPassword.value = ''; // Clear password field when starting to edit
};

const cancelEditing = () => {
    isEditing.value = false;
    newPassword.value = ''; // Clear password field when canceling
    // Reset player data to original state
    if (authStore.currentPlayer) {
        player.value = { ...authStore.currentPlayer };
    }
};
</script>

<template>
    <ion-page>
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button default-href="/"></ion-back-button>
                </ion-buttons>
                <ion-title>Profile</ion-title>
            </ion-toolbar>
        </ion-header>

        <ion-content class="ion-padding">
            <template v-if="player">
                <ion-item>
                    <ion-label position="floating">Username</ion-label>
                    <ion-input v-model="player.userName" :readonly="!isEditing"></ion-input>
                </ion-item>

                <ion-item>
                    <ion-label position="floating">Name</ion-label>
                    <ion-input v-model="player.name" :readonly="!isEditing"></ion-input>
                </ion-item>

                <ion-item v-if="isEditing">
                    <ion-label position="floating">New Password</ion-label>
                    <ion-input type="password" v-model="newPassword"></ion-input>
                </ion-item>

                <div class="ion-padding">
                    <template v-if="!isEditing">
                        <ion-button expand="block" @click="startEditing">
                            Edit Profile
                        </ion-button>
                    </template>
                    <template v-else>
                        <ion-button expand="block" @click="handleUpdate">
                            Save Changes
                        </ion-button>
                        <ion-button expand="block" fill="outline" @click="cancelEditing">
                            Cancel
                        </ion-button>
                    </template>

                    <ion-button expand="block" color="danger" @click="showDeleteConfirm = true">
                        Delete Account
                    </ion-button>

                    <ion-button expand="block" color="medium" @click="handleLogout">
                        Logout
                    </ion-button>
                </div>

                <div v-if="error" class="ion-text-center ion-padding error-message">
                    {{ error }}
                </div>
            </template>
        </ion-content>

        <ion-alert
            :is-open="showDeleteConfirm"
            header="Confirm Delete"
            message="Are you sure you want to delete your account? This action cannot be undone."
            :buttons="[
                {
                    text: 'Cancel',
                    role: 'cancel',
                    handler: () => { showDeleteConfirm = false }
                },
                {
                    text: 'Delete',
                    role: 'confirm',
                    handler: handleDelete
                }
            ]"
        ></ion-alert>
    </ion-page>
</template>

<style scoped>
.error-message {
    color: var(--ion-color-danger);
}

ion-button {
    margin-bottom: 1rem;
}
</style> 