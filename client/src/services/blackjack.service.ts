import type { Card, GameState } from '@/types/blackjack';

const CARD_SUITS = ['H', 'D', 'C', 'S'];
const CARD_VALUES = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];

export class BlackjackService {
    private createDeck(): Card[] {
        const deck: Card[] = [];
        for (const suit of CARD_SUITS) {
            for (const value of CARD_VALUES) {
                const code = value + suit;
                const numericValue = this.getCardValue(value);
                deck.push({ code, value: numericValue });
            }
        }
        return this.shuffleDeck(deck);
    }

    private shuffleDeck(deck: Card[]): Card[] {
        const shuffled = [...deck];
        for (let i = shuffled.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
        }
        return shuffled;
    }

    private getCardValue(value: string): number {
        if (value === 'A') return 11;
        if (['K', 'Q', 'J'].includes(value)) return 10;
        return parseInt(value);
    }

    private calculateScore(hand: Card[]): number {
        let score = 0;
        let aces = 0;

        // First sum all non-aces
        for (const card of hand) {
            if (card.code[0] === 'A') {
                aces++;
            } else {
                score += card.value;
            }
        }

        // Then add aces
        for (let i = 0; i < aces; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }

        return score;
    }

    private dealCard(state: GameState): Card {
        if (state.deck.length === 0) {
            state.deck = this.createDeck();
        }
        return state.deck.pop()!;
    }

    initializeGame(): GameState {
        const deck = this.createDeck();
        const state: GameState = {
            playerHand: [],
            dealerHand: [],
            deck,
            gameStatus: 'playing',
            playerScore: 0,
            dealerScore: 0,
            isPlayerTurn: true,
            isDealerTurn: false,
            bet: 10, // Default bet
            canDouble: true, // Initially can double
            canSplit: false // Will be set after dealing cards
        };

        // Deal initial cards
        state.playerHand.push(this.dealCard(state));
        state.dealerHand.push(this.dealCard(state));
        state.playerHand.push(this.dealCard(state));
        state.dealerHand.push(this.dealCard(state));

        // Calculate initial scores
        state.playerScore = this.calculateScore(state.playerHand);
        state.dealerScore = this.calculateScore(state.dealerHand);

        // Check for initial blackjack
        if (state.playerScore === 21) {
            state.gameStatus = 'playerWin';
            state.isPlayerTurn = false;
        }

        // Check if split is possible
        state.canSplit = this.canSplit(state.playerHand);

        return state;
    }

    private canSplit(hand: Card[]): boolean {
        return hand.length === 2 && 
               this.getCardValue(hand[0].code[0]) === this.getCardValue(hand[1].code[0]);
    }

    playerHit(state: GameState): GameState {
        if (!state.isPlayerTurn || state.gameStatus !== 'playing') return state;

        state.playerHand.push(this.dealCard(state));
        state.playerScore = this.calculateScore(state.playerHand);

        if (state.playerScore > 21) {
            state.gameStatus = 'playerBust';
            state.isPlayerTurn = false;
        } else if (state.playerHand.length >= 5) {
            // 5-card rule: automatically stand
            return this.playerStand(state);
        }

        return { ...state };
    }

    playerStand(state: GameState): GameState {
        if (!state.isPlayerTurn || state.gameStatus !== 'playing') return state;

        state.isPlayerTurn = false;
        state.isDealerTurn = true;

        // Dealer's turn
        while (state.isDealerTurn) {
            state = this.dealerPlay(state);
        }

        return { ...state };
    }

    private dealerPlay(state: GameState): GameState {
        if (!state.isDealerTurn) return state;

        if (state.dealerScore < 17) {
            // Dealer must hit on 16 or less
            state.dealerHand.push(this.dealCard(state));
            state.dealerScore = this.calculateScore(state.dealerHand);

            if (state.dealerScore > 21) {
                state.gameStatus = 'dealerBust';
                state.isDealerTurn = false;
            }
        } else {
            // Dealer must stand on 17 or more
            state.isDealerTurn = false;
            this.determineWinner(state);
        }

        return { ...state };
    }

    private determineWinner(state: GameState): void {
        if (state.gameStatus !== 'playing') return;

        if (state.playerScore > state.dealerScore) {
            state.gameStatus = 'playerWin';
        } else if (state.dealerScore > state.playerScore) {
            state.gameStatus = 'dealerWin';
        } else {
            state.gameStatus = 'push';
        }
    }

    playerDouble(state: GameState): GameState {
        if (!state.isPlayerTurn || !state.canDouble || state.gameStatus !== 'playing') {
            return state;
        }

        // Double the bet
        state.bet *= 2;
        
        // Draw one card
        state.playerHand.push(this.dealCard(state));
        state.playerScore = this.calculateScore(state.playerHand);

        // Check for bust
        if (state.playerScore > 21) {
            state.gameStatus = 'playerBust';
            state.isPlayerTurn = false;
            return state;
        }

        // Automatically stand after doubling
        return this.playerStand(state);
    }

    playerSplit(state: GameState): GameState {
        if (!state.isPlayerTurn || !state.canSplit || state.gameStatus !== 'playing') {
            return state;
        }

        // Create a new game state for the split hand
        const splitState: GameState = {
            ...state,
            playerHand: [state.playerHand[1]], // Take second card
            dealerHand: [], // New dealer hand for split game
            playerScore: 0,
            dealerScore: 0,
            isPlayerTurn: true,
            isDealerTurn: false,
            gameStatus: 'playing'
        };

        // Update current hand
        state.playerHand = [state.playerHand[0]]; // Keep first card
        
        // Deal new cards to both hands
        state.playerHand.push(this.dealCard(state));
        splitState.playerHand.push(this.dealCard(state));

        // Update scores
        state.playerScore = this.calculateScore(state.playerHand);
        splitState.playerScore = this.calculateScore(splitState.playerHand);

        // Update split possibilities
        state.canSplit = this.canSplit(state.playerHand);
        splitState.canSplit = this.canSplit(splitState.playerHand);

        // Can't double after split
        state.canDouble = false;
        splitState.canDouble = false;

        // Store split state for later
        state.splitHand = splitState;

        return state;
    }
} 