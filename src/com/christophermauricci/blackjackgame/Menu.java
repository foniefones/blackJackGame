package com.christophermauricci.blackjackgame;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Menu mainMenu = new Menu();
    }

    public Menu() {
        System.out.println("\nWelcome to Academy Casino's Black Jack!\n" +
                            "\nPlease make a selection: \n");
        Scanner menuInput = new Scanner(System.in);

        System.out.println("\n==============================="+
                            "\n        Black Jack          " +
                            "\n===============================" +
                            "\nPress (1) to Play Black Jack" +
                           "\nPress(2) to view rules" +
                            "\nPress(3) to see high scores" +
                            "\nPress(4) to exit game" +
                            "\n===============================");
        int response = menuInput.nextInt();

        switch (response) {
            case 1: PlayingCardGame playGame = new PlayingCardGame(); break;
            case 2: GameRules rules = new GameRules();  break;
            case 3: HighScore scores = new HighScore(); break;
            case 4: System.exit(0); break;
            default:
                try{
                    Integer testResponse = Integer.parseInt(menuInput.next());
                }

                catch (NumberFormatException a){
                    System.out.print("Invalid input! Must enter 1, 2, 3 or 4");             //TODO: Fix error exception!!
                }
        }
    }
}
