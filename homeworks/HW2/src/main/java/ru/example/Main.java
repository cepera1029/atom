package ru.example;

import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static int bulls;
    public static int cows;

    public static boolean gameCore(String userWord, String answer) {
        bulls = 0;
        cows = 0;
        boolean result = false;
        for (int i = 0; i < answer.length(); i++) {
            if (userWord.toCharArray()[i] == answer.toCharArray()[i])
                bulls++;
        }
        if (bulls == answer.length())
            result = true;
        else {
            char letter;
            boolean[] gameFlag;
            boolean[] playerFlag;

            gameFlag = new boolean[answer.length()];
            playerFlag = new boolean[userWord.length()];
            for (int i = 0; i < answer.length(); i++) {
                if (answer.toCharArray()[i] == userWord.toCharArray()[i]) {
                    gameFlag[i] = false;
                    playerFlag[i] = false;
                } else {
                    gameFlag[i] = true;
                    playerFlag[i] = true;
                }

            }
            for (int i = 0; i < userWord.length(); i++) {
                letter = userWord.toCharArray()[i];
                if (userWord.indexOf(letter) != -1 && playerFlag[i]) {
                    for (int j = 0; j < answer.length(); j++) {
                        if (letter == answer.toCharArray()[j] && gameFlag[j]) {
                            playerFlag[i] = false;
                            gameFlag[j] = false;
                            cows++;
                            break;
                        }
                    }
                }
            }
            System.out.printf("bulls:%d cows:%d\n", bulls, cows);
        }
        log.info("User word: " + userWord + "   Bulls: " + bulls + "   Cows: " + cows);
        return result;
    }

    public static void main(String[] args) {
        int count = 0;
        log.info("Game started!");//logger works
        File path = new File("dictionary.txt");
        if (!path.exists())
            System.out.println("File not found");
        ArrayList<String> arr = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String scurrentline;
            while ((scurrentline = br.readLine()) != null) {
                arr.add(scurrentline);
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////GAME///////
        //System.out.printf("count=%d",count);
        Random rnd = new Random(System.currentTimeMillis());
        int number = rnd.nextInt(count);
        String answer = arr.get(number);
        System.out.println(answer);
        log.info("Right answer: " + answer);
        boolean result = false;
        int attempts = 10;
        System.out.println("Make sure u switched English");
        System.out.printf("There are %d letters in my word\n", answer.length());
        while ((!result) && (attempts != 0)) {
            attempts--;
            System.out.println("Your guess?");
            Scanner input = new Scanner(System.in);
            String userWord = input.nextLine();
            if (userWord.length() != answer.length()) {
                System.out.println("Incorrect word length!");
                System.out.printf("There are %d letters in my word\n", answer.length());
                log.info("Incorrect word length: " + userWord.length() + "/" + answer.length());
                attempts++;
            } else
                result = gameCore(userWord, answer);
            System.out.printf("Attempts remaining:%d\n", attempts);
        }
        if (!result) {
            System.out.printf("You lost( My word is %s\n", answer);
            log.info("Defeat!");
        } else {
            System.out.println("You won!");
            log.info("Win!");
        }
    }
}