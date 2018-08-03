package com.christophermauricci.blackjackgame;

import java.util.ArrayList;
import java.util.Random;

public class PlayingCardDeck {

    private ArrayList<PlayingCard> playingCards;

    public PlayingCardDeck() {
        this.playingCards = new ArrayList<>();
    }

    public void createFullDeck() {
        //Generate playing cards here
        for(Suit cardSuit : Suit.values()) {
            for(Value cardValue : Value.values()) {
                //Add a new card to the deck
                this.playingCards.add(new PlayingCard(cardSuit, cardValue));
            }
        }
    }

    public void shuffle() {
        //New temporary arraylist with randomizer
        ArrayList<PlayingCard> tempDeck = new ArrayList<>();
        Random random = new Random();

        int randomCardIndex = 0;
        int originalSize = this.playingCards.size();

        for (int i = 0; i<originalSize; i++) {
            //Generate random index             formula: nextInt((max - min) +1) +min;
            randomCardIndex = random.nextInt((this.playingCards.size()-1 - 0) +1) + 0;
            tempDeck.add(this.playingCards.get(randomCardIndex));
            //Remove from original deck
            this.playingCards.remove(randomCardIndex);
        }
        this.playingCards = tempDeck;
    }

    public String toString() {
        String cardListOutput = "";


        for (PlayingCard aPlayingCard : this.playingCards) {
            cardListOutput += "\n" + aPlayingCard.toString();

        }
        return cardListOutput;
    }

    //Methods for card deck
    public void removeCard(int i) {
        this.playingCards.remove(i);
    }

    public  PlayingCard getCard(int i) {
        return this.playingCards.get(i);
    }

    public void addCard(PlayingCard addCard) {
        this.playingCards.add(addCard);
    }

    public void draw (PlayingCardDeck comingFromDeck) {
        this.playingCards.add(comingFromDeck.getCard(0));                           //draws the top card of the deck (index 0)
        comingFromDeck.removeCard(0);
    }

    public int deckSize() {
        return this.playingCards.size();
    }

    //Method to move both player's and dealer's cards to deck
    public void moveAllToDeck(PlayingCardDeck moveTo) {
        int thisDeckSize = this.playingCards.size();

        //put cards in moveTo deck
        for (int i=0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }
        for (int i=0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }

    //Return value of each card in deck
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (PlayingCard aCard : this.playingCards) {
            switch (aCard.getValue()) {
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: totalValue += 1; break;
            }
        }
        //Loop to control ace values
        for (int i =0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else  {
                totalValue += 11;
            }
        }
        return totalValue;
    }

    public char suitValues() {
        char uniSuit = ' ';
        //TODO: Add code to replace suit names with unicode characters, use the getSuit method from PlayingCard

        // SPADES: '\u2660'
        //DIAMOND: '\u2666'
        //CLUBS: '\u2663'
        //HEARTS: '\u2764'
        return uniSuit;
    }

}
