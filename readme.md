# 🃏 TwentyOne – Blackjack als Mobile-App

Dies ist das GitHub-Repository für unser AWT-Projekt **TwentyOne** – eine Singleplayer-Blackjack-Anwendung mit Vue 3 (Ionic) im Frontend und Spring Boot im Backend.

## 📚 Projektidee

Ein simples Blackjack-Spiel ("Twenty One") gegen einen virtuellen Dealer.  
Ziel ist es, das Spiel vollständig als mobile-first Web-App umzusetzen – mit REST-API, sauberem Code und gutem Design.

Details zur Projektidee, Ressourcen und User Stories findest du hier:  
👉 [Projektbeschreibung & Spezifikation (Google Docs)](https://docs.google.com/document/d/1y2g-sIxQuOhFQXfiXVY2fNWwhQiEoZoPI1cMuAEVLB4/edit?usp=sharing)

## ⚙️ Tech-Stack

- **Frontend**: Ionic + Vue 3 + Pinia + Axios
- **Backend**: Java 21 + Spring Boot + JPA
- **Datenbank**: H2 (Dev), PostgreSQL (optional)
- **Architektur**: Clean Architecture (Domain/UseCase/API Layering)
- **Design Tools**: Figma (optional), Paper Prototypes
- **Management**: GitHub Projects (Kanban), Issues, Pull Requests

## 🚀 Projekt ausführen

### 🔧 Voraussetzungen

- Java 21
- Node.js + npm
- Ionic CLI (`npm install -g @ionic/cli`)
- Maven 3.9+

### ▶️ Projekt bauen

auf root:

```bash
mvn clean install
```

### ▶️ Backend starten

```bash
cd server
./mvn clean install spring-boot:run
```

### ▶️ Backend debuggen

Um das Backend im Debug-Modus zu starten, führe folgenden Befehl im `server`-Verzeichnis aus:

```bash
cd server
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
```

Die Anwendung hält beim start nicht an, bis ihr euch auf den Debugger Port `5005` verbindet.  
Zum debuggen müsst ihr euch eine jvm mit dem Port `5005` konfigurieren. 

API erreichbar unter:  
`http://localhost:8080/twentyone/api/` und dann der jeweilige Controller.


### 📱 Frontend starten

```bash
cd client
npm install
npm run dev
```

App erreichbar unter: `http://localhost:5173`

---


## 👥 Team

- Burko Martin
- Hochmayr Nina
- Lindner Marvin
- Reynaud Cade

---

> Advanced Web Technologies · MSE SS-25 · FHTW Wien  
> Gruppe: `at.fhtw.swe.swt.twentyone`
