package com.christophermauricci.blackjackgame;

import java.util.Scanner;

//Tutorials class name is Blackjack
public class PlayingCardGame {

    //TODO: Add option to exit game and save highscore (max earnings) to separate text file
    //TODO: Menu to choose: play a game, show rules, show highscores, exit game

    public static void main(String[] args) {
        System.out.println("Welcome to Academy Casino's Black Jack!");

        PlayingCardDeck deck = new PlayingCardDeck();
        deck.createFullDeck();
        deck.shuffle();

        //Create player and dealer hands
        PlayingCardDeck playerDeck = new PlayingCardDeck();                 //TODO: Refactor to playerCards or playerHand instead?
        PlayingCardDeck dealerDeck = new PlayingCardDeck();                 //TODO: Refactor to dealerCards or dealerHand instead?

        double playerMoney = 100.00;
        Scanner userInput = new Scanner(System.in);

        //Game loop
        while(playerMoney > 0) {
            //Player's bet
            System.out.println("You have £" + playerMoney + " left. How much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney) {
                System.out.println("You cannot bet more money than you have. Please leave!");
                break;
            }
            boolean endRound = false;

            //Dealing
            playerDeck.draw(deck);
            playerDeck.draw(deck);

            dealerDeck.draw(deck);
            dealerDeck.draw(deck);

            while (true) {
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                //Dealers hand, only display one of dealer's cards
                System.out.println("Dealer's hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");         //TODO: display value of dealer's card

                //Player action
                System.out.println("Do you want to (1)Hit or (2)Stand?");
                int respone = userInput.nextInt();
                if (respone == 1) {
                    playerDeck.draw(deck);
                    System.out.println("You drew: " + playerDeck.getCard(playerDeck.deckSize() -1).toString());     //most recent added card if player choose to hit

                    //If player go bust when drawing new card
                    if(playerDeck.cardsValue() > 21) {
                        System.out.println("Bust! Your card's value is: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (respone == 2) {
                    break;
                }
            }
            //Reveal dealer's cards
            System.out.println("Dealer's cards: " + dealerDeck.toString());

            //See who has most points
            if (dealerDeck.cardsValue() > playerDeck.cardsValue() && !endRound) {
                System.out.println("Dealer wins!");                                             //TODO: Add player and dealer card values
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer have to draw at 16 and stands at 17
            while (dealerDeck.cardsValue() < 17 && !endRound) {
                dealerDeck.draw(deck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize() -1).toString());      //most recent added card if have to draw
            }

            System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());
            //If player win
            if (dealerDeck.cardsValue() > 21 && !endRound) {
                System.out.println("Dealer bust! You win this round!");
                playerMoney += playerBet;
                endRound = true;
            }

            //If push (a draw between player and dealer
            if(playerDeck.cardsValue() == dealerDeck.cardsValue() && !endRound) {
                System.out.println("Push! No winner.");
                endRound = true;
            }

            if(playerDeck.cardsValue() > dealerDeck.cardsValue() && !endRound) {
                System.out.println("You win this round!");                                              //TODO: Add player and dealer card values
                playerMoney += playerBet;
                endRound = true;
            } else if (!endRound) {
                System.out.println("You lose the round");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Adds player's and dealer's cards back into the deck arraylist
            playerDeck.moveAllToDeck(deck);
            dealerDeck.moveAllToDeck(deck);
            System.out.println("End of round.");
        }

        System.out.println("Game over! You are out of money! xD");

    }

}
