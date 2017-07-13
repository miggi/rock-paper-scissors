package com.mygryn;

import java.util.Scanner;

import static com.mygryn.Constants.*;

public class GameApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        while (true) {
            game.getStatistics().print();
            printQueryMessage();

            int input = scanner.nextInt();
            if (input == 0)
                break;

            printResult(
                    game.play(input)
            );
        }
    }


    private static void printQueryMessage() {
        System.out.println("Please make your CHOICE!" +
                "\n 1 = ROCK, 2 = PAPER, or 3 = SCISSORS, 0 = quit");
    }

    private static void printResult(Result res) {
        switch (res.getResult()) {
            case RESULT_WIN:
                System.out.println("--------------- Victory! '" + res.getHuman() + "' beats '" + res.getComputer() + "'.");
                break;
            case RESULT_FAIL:
                System.out.println("--------------- Failure! '" + res.getHuman() + "' can't beat '" + res.getComputer() + "'.");
                break;
            case RESULT_TIE:
                System.out.println("--------------- It's TIE! Impossible.");
                break;
        }
    }
}
