package com.mygryn;

import java.util.Scanner;

import static com.mygryn.Constants.*;

public class GameApp {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            game.getStatistics().print();

            printQueryMessage();

            int input = scanner.nextInt();
            if (input == 0) break;

            printResult(game.play(input));
        }
        scanner.close();
    }

    private static void printQueryMessage() {
        System.out.println("Please make your CHOICE: " +
                "\n1 = ROCK, 2 = PAPER, or 3 = SCISSORS, 0 = quit");
    }

    private static void printResult(Result res) {
        switch (res.getResult()) {
            case RESULT_WIN:
                System.out.println("\n --------------- Victory! '" + res.getHuman() + "' beats '" + res.getComputer() + "'.");
                break;
            case RESULT_FAIL:
                System.out.println("\n --------------- Failure! '" + res.getHuman() + "' can't beat '" + res.getComputer() + "'.");
                break;
            case RESULT_TIE:
                System.out.println("\n --------------- It's TIE! Impossible.");
                break;
        }
    }
}
