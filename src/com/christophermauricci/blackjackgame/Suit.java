package com.christophermauricci.blackjackgame;

public enum Suit {
    CLUBS ('\u2663'), 
    DIAMOND ('\u2666'),
    SPADES('\u2660'),
    HEARTS('\u2764');

    private final char character;

    Suit(char cha) {
        this.character = cha;
    }
}
