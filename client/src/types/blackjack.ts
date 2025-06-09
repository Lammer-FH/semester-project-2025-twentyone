export type Card = {
    code: string;  // e.g., 'AH' for Ace of Hearts
    value: number; // Numeric value of the card (1/11 for Ace)
};

export type GameState = {
    playerHand: Card[];
    dealerHand: Card[];
    deck: Card[];
    gameStatus: 'playing' | 'playerBust' | 'dealerBust' | 'playerWin' | 'dealerWin' | 'push';
    playerScore: number;
    dealerScore: number;
    isPlayerTurn: boolean;
    isDealerTurn: boolean;
};

export type GameAction = 'hit' | 'stand'; 