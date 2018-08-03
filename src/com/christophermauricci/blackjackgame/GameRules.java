package com.christophermauricci.blackjackgame;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameRules {

    public GameRules() {
        try(BufferedReader br = new BufferedReader(new FileReader("gamerules.txt"))) {          //TODO: replace £ with unicode in .txt file
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());              //to get each line in .txt file to show on a new line in console
                line = br.readLine();
            }
            String rules = sb.toString();
            System.out.println(rules);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
