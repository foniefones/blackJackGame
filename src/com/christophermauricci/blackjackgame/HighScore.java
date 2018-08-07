package com.christophermauricci.blackjackgame;

import java.io.*;

public class HighScore {

    public HighScore() {
        File file = new File("highscores.txt");
        //If .txt file doesn't exist, create a new one to store high scores in
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(BufferedReader br = new BufferedReader(new FileReader("highscores.txt"))) {          //TODO: replace £ with unicode in .txt file
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
        //TODO: Add code to ask if user want to go back to menu or quit
    }
}
