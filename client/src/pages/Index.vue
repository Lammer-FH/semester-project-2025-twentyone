<template>
  <IonPage>
    <IonHeader>
      <IonToolbar>
        <IonTitle>TwentyOne</IonTitle>
      </IonToolbar>
    </IonHeader>

    <IonContent class="ion-padding">
      <IonGrid class="ion-justify-content-center ion-align-items-center" style="height: 100%">
        <IonRow>
          <IonCol size="12" size-md="6">
            <div class="ion-text-center">
              <IonButton expand="block" class="ion-margin-bottom" @click="startGame">
                <template v-slot:start>
                  <IonIcon name="play-circle-outline" />
                </template>
                Start Game
              </IonButton>

              <IonButton expand="block" class="ion-margin-bottom" @click="viewResults">
                <template v-slot:start>
                  <IonIcon name="trophy-outline" />
                </template>
                Game Results
              </IonButton>

              <IonButton expand="block" class="ion-margin-bottom" @click="goProfile">
                <template v-slot:start>
                  <IonIcon name="person-circle-outline" />
                </template>
                Profile
              </IonButton>

              <br />

              <IonButton expand="block" class="ion-margin-bottom" @click="viewRules">
                <template v-slot:start>
                  <IonIcon name="help-circle-outline" />
                </template>
                Rules
              </IonButton>

              <IonButton expand="block" @click="logout">
                <template v-slot:start>
                  <IonIcon name="log-out-outline" />
                </template>
                Logout
              </IonButton>
            </div>
          </IonCol>
        </IonRow>
      </IonGrid>
    </IonContent>
  </IonPage>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import {
  IonButton,
  IonCol,
  IonContent,
  IonGrid,
  IonHeader,
  IonIcon,
  IonPage,
  IonRow,
  IonTitle,
  IonToolbar,
} from '@ionic/vue'

const router = useRouter()

function startGame() {
  router.push({ name: 'game', params: { id: 1 } })
}
function viewResults() {
  router.push({ name: 'game-result', params: { sessionId: 1 } })
}
function goProfile() {
  /* feat. Player Profile #4 */
}
function viewRules() {
  router.push({ name: 'rules' })
}
function logout() {
  /* ML TODO */
}
</script>
