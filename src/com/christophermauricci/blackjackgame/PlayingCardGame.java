package com.christophermauricci.blackjackgame;

import java.io.*;
import java.util.Scanner;

//Game logic
//TODO: Add option to send player back to menu after game over or saved highscore
public class PlayingCardGame {

    public PlayingCardGame() {
        System.out.println("Welcome to Academy Casino's Black Jack!");

        PlayingCardDeck deck = new PlayingCardDeck();
        deck.createFullDeck();
        deck.shuffle();

        //Create player and dealer hands
        PlayingCardDeck playerHand = new PlayingCardDeck();
        PlayingCardDeck dealerHand = new PlayingCardDeck();

        double playerMoney = 100.00;
        Scanner userInput = new Scanner(System.in);

        //Game loop
        while(playerMoney > 0) {
            //Player's bet
            System.out.println("You have £" + playerMoney + " left. How much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney) {
                System.out.println("You cannot bet more money than you have. Please leave the casino!");
                break;
            }
            boolean endRound = false;

            //Dealing
            playerHand.draw(deck);
            playerHand.draw(deck);

            dealerHand.draw(deck);
            dealerHand.draw(deck);

            while (true) {
                System.out.println("\nYour hand: " + playerHand.toString());

                System.out.println("Your hand is valued at: " + playerHand.cardsValue());

                //Dealers hand, only display one of dealer's cards
                System.out.println("\nDealer's hand: \n" + dealerHand.getCard(0).toString() + " and [Hidden]");

                //Player action
                System.out.println("Do you want to (1)Hit or (2)Stand?");
                int response = userInput.nextInt();
                if (response == 1) {
                    playerHand.draw(deck);
                    System.out.println("You drew: " + playerHand.getCard(playerHand.deckSize() -1).toString());     //most recent added card if player choose to hit

                    //If player go bust when drawing new card
                    if(playerHand.cardsValue() > 21) {
                        System.out.println("Bust! Your card's value is: " + playerHand.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }
            //Reveal dealer's cards
            System.out.println("Dealer's cards: " + dealerHand.toString());

            //See who has most points
            if (dealerHand.cardsValue() > playerHand.cardsValue() && !endRound) {
                System.out.println("\n \nDealer wins!");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer have to draw at 16 and stands at 17
            while (dealerHand.cardsValue() < 17 && !endRound) {
                dealerHand.draw(deck);
                System.out.println("Dealer draws: " + dealerHand.getCard(dealerHand.deckSize() -1).toString());      //most recent added card if have to draw
            }

            System.out.println("Dealer's hand is valued at: " + dealerHand.cardsValue());
            //If player win
            if (dealerHand.cardsValue() > 21 && !endRound) {
                System.out.println("Dealer bust! You win this round!");
                playerMoney += playerBet;
                endRound = true;
            }

            //If push (a draw between player and dealer
            if(playerHand.cardsValue() == dealerHand.cardsValue() && !endRound) {
                System.out.println("\nPush! No winner.");
                endRound = true;
            }

            if(playerHand.cardsValue() > dealerHand.cardsValue() && !endRound) {
                System.out.println("\nYou win this round!");
                playerMoney += playerBet;
                endRound = true;
            } else if (!endRound) {
                System.out.println("\nYou lose the round");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Adds player's and dealer's cards back into the deck arraylist
            playerHand.moveAllToDeck(deck);
            dealerHand.moveAllToDeck(deck);

            //Give user the option to save high score and quit or play again
            Scanner playAgainInput = new Scanner(System.in);
            System.out.println("\nEnd of round. Would you like to play another round or save earnings? \n" +
                                                "\n(1) Play again " +
                                                "(2) Save earnings");
            int playAgain = playAgainInput.nextInt();
            if (playAgain == 1) {
                endRound = true;
            } else if (playAgain == 2) {
                //If .txt file doesn't exist, create a new one to store high scores in
                File file = new File("highscores.txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("To save your score, please type in your name: ");
                Scanner nameHighscore = new Scanner(System.in);
                String name = nameHighscore.nextLine();

                // Print name and earnings to file
                try (FileWriter fw = new FileWriter("highscores.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter toHighscore = new PrintWriter(bw)) {
                    toHighscore.println(name + " " + " £" + playerMoney + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                //TODO: Add code to send player back to menu instead of quiting game
            }
        }
        System.out.println("\nGame over! You are out of money! xD");
        //TODO: Add code to send player back to menu
    }

}
