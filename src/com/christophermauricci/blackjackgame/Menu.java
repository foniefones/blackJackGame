package com.christophermauricci.blackjackgame;

import java.util.Scanner;

public class Menu {

    public Menu() {
        System.out.println("Welcome to Academy Casino's Black Jack!" +
                            "\nPlease make a selection: \n");
        Scanner menuInput = new Scanner(System.in);

        System.out.println("==============================="+
                            "\n        Black Jack          " +
                            "\n==============================" +
                            "Press (1) to Play Black Jack" +
                           "\nPress(2) to see rules" +
                            "\nPress(3) to see high scores" +
                            "\nPress(4) to exit game" +
                            "\n============================");
        int response = menuInput.nextInt();

        switch (response) {
            case 1: PlayingCardDeck deck = new PlayingCardDeck(); break;
            case 2: break;
            case 3: break;
            case 4: System.exit(0); break;
            default: System.out.println("Invalid input! Must enter 1, 2, 3 or 4"); break;
        }
    }

}
