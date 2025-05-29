<script setup lang="ts">
import { ref } from 'vue';
import { 
  IonPage, 
  IonHeader, 
  IonToolbar, 
  IonTitle, 
  IonContent, 
  IonBackButton, 
  IonButtons,
  IonSegment,
  IonSegmentButton,
  IonLabel
} from '@ionic/vue';

const selectedLanguage = ref<string>('en');
</script>

<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button default-href="/"></ion-back-button>
        </ion-buttons>
        <ion-title>{{ selectedLanguage === 'de' ? 'Spielregeln' : 'Game Rules' }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding">
      <div class="rules-content">
        <ion-segment v-model="selectedLanguage" class="language-toggle">
          <ion-segment-button value="de">
            <ion-label>Deutsch</ion-label>
          </ion-segment-button>
          <ion-segment-button value="en">
            <ion-label>English</ion-label>
          </ion-segment-button>
        </ion-segment>

        <!-- German Rules -->
        <div v-if="selectedLanguage === 'de'">
          <h1>Spielregeln für Blackjack</h1>
          
          <section class="rule-section">
            <h2>🎯 Ziel</h2>
            <p>Mit bis zu 5 Karten oder möglichst nah an <strong>21 Punkte</strong> kommen – ohne sie zu überschreiten.</p>
            <p>Die beste Hand ist ein <strong>"Blackjack"</strong> (Ass + 10/Picture Card).</p>
          </section>

          <section class="rule-section">
            <h2>🎴 Kartenwerte</h2>
            <ul>
              <li><strong>2–10:</strong> Zählen entsprechend dem Zahlenwert</li>
              <li><strong>Bube, Dame, König:</strong> Jeweils <strong>10 Punkte</strong></li>
              <li><strong>Ass:</strong> Zählt <strong>1 oder 11</strong>, je nachdem was besser passt</li>
            </ul>
          </section>

          <section class="rule-section">
            <h2>🎮 Spielablauf</h2>
            <ol>
              <li><strong>Start:</strong> Spieler erhalten zwei Karten</li>
              <li>
                <strong>Aktionen:</strong>
                <ul>
                  <li>Hit: Weitere Karte ziehen</li>
                  <li>Stand: Keine Karte mehr ziehen</li>
                  <li>Double: Einsatz verdoppeln, eine Karte ziehen, dann "Stand"</li>
                  <li>Split: Zwei gleiche Karten aufteilen, separat spielen</li>
                </ul>
              </li>
              <li>
                <strong>Dealer spielt:</strong><br>
                Muss bei <strong>16 oder weniger ziehen</strong>, bei <strong>17 oder mehr stehen bleiben</strong>
              </li>
            </ol>
          </section>

          <section class="rule-section">
            <h2>🏆 Spielende</h2>
            <ul>
              <li><strong>Mehr als 21 Punkte</strong> = verloren (<strong>"Bust"</strong>)</li>
              <li><strong>Höchste Punktzahl ≤ 21 gewinnt</strong></li>
              <li><strong>Gleichstand mit Dealer</strong> = <em>Push</em> (Einsatz zurück)</li>
              <li><strong>"Blackjack"</strong> (21 mit zwei Karten) schlägt jede andere 21</li>
            </ul>
          </section>
        </div>

        <!-- English Rules -->
        <div v-else>
          <h1>Blackjack Rules</h1>
          
          <section class="rule-section">
            <h2>🎯 Objective</h2>
            <p>Get as close to <strong>21 points</strong> as possible with up to 5 cards – without going over.</p>
            <p>The best hand is a <strong>"Blackjack"</strong> (Ace + 10/Picture Card).</p>
          </section>

          <section class="rule-section">
            <h2>🎴 Card Values</h2>
            <ul>
              <li><strong>2–10:</strong> Count at their face value</li>
              <li><strong>Jack, Queen, King:</strong> Each worth <strong>10 points</strong></li>
              <li><strong>Ace:</strong> Counts as <strong>1 or 11</strong>, whichever is more advantageous</li>
            </ul>
          </section>

          <section class="rule-section">
            <h2>🎮 Gameplay</h2>
            <ol>
              <li><strong>Start:</strong> Players receive two cards</li>
              <li>
                <strong>Actions:</strong>
                <ul>
                  <li>Hit: Draw another card</li>
                  <li>Stand: Keep current cards</li>
                  <li>Double: Double your bet, draw one card, then stand</li>
                  <li>Split: Separate two matching cards, play as separate hands</li>
                </ul>
              </li>
              <li>
                <strong>Dealer's Play:</strong><br>
                Must hit on <strong>16 or less</strong>, must stand on <strong>17 or more</strong>
              </li>
            </ol>
          </section>

          <section class="rule-section">
            <h2>🏆 Game End</h2>
            <ul>
              <li><strong>Over 21 points</strong> = loss (<strong>"Bust"</strong>)</li>
              <li><strong>Highest score ≤ 21 wins</strong></li>
              <li><strong>Tie with Dealer</strong> = <em>Push</em> (bet returned)</li>
              <li><strong>"Blackjack"</strong> (21 with two cards) beats any other 21</li>
            </ul>
          </section>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<style scoped>
.rules-content {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.language-toggle {
  margin-bottom: 20px;
}

.rules-text {
  line-height: 1.6;
  font-size: 16px;
}

h1 {
  color: var(--ion-color-primary);
  margin-bottom: 30px;
  text-align: center;
}

h2 {
  color: var(--ion-color-primary);
  margin: 25px 0 15px;
  font-size: 1.5em;
}

.rule-section {
  margin-bottom: 30px;
  padding: 20px;
  background: var(--ion-background-color);
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

ul, ol {
  padding-left: 20px;
}

li {
  margin-bottom: 10px;
}

strong {
  color: var(--ion-color-primary);
}

ul ul {
  margin-top: 10px;
}
</style>