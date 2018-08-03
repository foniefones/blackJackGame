package com.christophermauricci.blackjackgame;

public class PlayingCard {

    private Value value;
    private Suit suit;

    public PlayingCard(Suit suit, Value value) {
        this.value = value;
        this.suit = suit;
    }

    public String toString() {
        return this.suit.toString() + " - " + this.value.toString();
    }

    public Value getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }
}
