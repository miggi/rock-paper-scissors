package com.mygryn.learner;

public interface Learner {

    /**
     * Train (re-train) model on the fresh data
     */
    void train();

    /**
     * Pick less probable value from trained model for game
     */
    int makeComputerGuess();
}
