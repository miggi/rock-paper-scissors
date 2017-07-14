package com.mygryn;

import com.mygryn.learner.Learner;
import com.mygryn.learner.ProbabilityLearner;

import java.util.Random;

import static com.mygryn.Constants.*;

public class Game {

    private final GameStatistics statistics;
    private final Learner learner;

    public Game() {
        this.statistics = new GameStatistics();
        this.learner = new ProbabilityLearner(statistics);
    }

    /**
     * Gets result of the estimation User vs Machine
     *
     * @param userTurn user input
     * @return index of the result win/fail/tie
     */
    public Result play(int userTurn) {
        int computerTurn = getComputerTurn(learner, statistics);
        int result = RESULT_TIE;

        switch (userTurn) {
            case ROCK_ID:
                result = computerTurn == SCISSORS_ID ? RESULT_WIN : RESULT_FAIL;
                break;
            case PAPER_ID:
                result = computerTurn == ROCK_ID ? RESULT_WIN : RESULT_FAIL;
                break;
            case SCISSORS_ID:
                result = computerTurn == PAPER_ID ? RESULT_WIN : RESULT_FAIL;
        }
        statistics.recordTurn(userTurn, result);
        return
                new Result(
                        codeToName(userTurn),
                        codeToName(computerTurn),
                        result
                );
    }

    /**
     * Gets computer turn. Determine random number generation strategy:
     *
     *  1. If games number < LEARN_LIMIT use random generation;
     *  2. If games number > LEARN_LIMIT apply machine learning-based technique;
     *
     * @param learner    implementation of the machine learner
     * @param statistics stats holder
     * @return index of the secret
     */
    private int getComputerTurn(Learner learner, GameStatistics statistics) {
        if (statistics.getGameNumber() > LEARN_LIMIT)
            return learner
                    .makeComputerGuess();
        else
            return new Random()
                    .nextInt(COMBINATIONS_LIMIT) + 1;
    }

    /**
     * Method will map choice code to word representation.
     * 1=rock, 2=paper, or 3=scissors.
     *
     * @param number input numeric value between [0..3]
     * @return string word name
     * @throws IllegalArgumentException if user enters not supported choice
     */
    private String codeToName(int number) {
        String choice;
        switch (number) {
            case 1:
                choice = ROCK;
                break;
            case 2:
                choice = PAPER;
                break;
            case 3:
                choice = SCISSORS;
                break;
            default:
                throw new IllegalArgumentException("Your choice '" + number
                        + "' is not supported! Only numbers 1 = Rock, 2 = Paper, or 3 = Scissors. ");
        }
        return choice;
    }

    public GameStatistics getStatistics() {
        return statistics;
    }
}
