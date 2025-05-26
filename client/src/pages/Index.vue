<script setup lang="ts">
import { reactive } from 'vue';

import { storeToRefs } from 'pinia';

import { IonButton, IonCard, IonCardHeader, IonCardSubtitle, IonCardContent } from '@ionic/vue';

import useStore from '../stores/main';

const mainStore = useStore();

const { welcomeText } = storeToRefs(mainStore);
// If you do not want to use 'welcomeText.value', wrap it in a reactive object.
// It can be easier for people migrating from vue 2.
const data = reactive({
	introductionText: welcomeText.value
});

// eslint-disable-next-line no-console
if (data.introductionText === welcomeText.value) console.log(data.introductionText);
</script>

<template>
	<base-view id="home-page">
		<template #default-view-title>
			<div class="text-center">Home Page</div>
		</template>
		<template #default-view-body>
			<div class="row welcome-page">
				<ion-card class="col-xl-4 col-lg-5 col-md-5 col-sm-6 col-12">
					<ion-card-header>
						<ion-card-subtitle>First Page</ion-card-subtitle>
						<ion-card-title>Welcome Home</ion-card-title>
					</ion-card-header>

					<ion-card-content class="text-center">
						<p>{{ welcomeText }}</p>
						<div class="button-container">
							<ion-button class="pt-1" router-link="/about">Click Me</ion-button>
							<ion-button class="pt-1" router-link="/rules" color="secondary">
								Spielregeln
							</ion-button>
						</div>
					</ion-card-content>
				</ion-card>
			</div>
		</template>
	</base-view>
</template>

<style scoped>
.button-container {
	display: flex;
	gap: 10px;
	justify-content: center;
}
</style>
