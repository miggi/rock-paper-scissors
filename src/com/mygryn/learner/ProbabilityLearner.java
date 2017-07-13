package com.mygryn.learner;

import com.mygryn.GameStatistics;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

public class ProbabilityLearner implements Learner {

    private EnumeratedIntegerDistribution distribution;
    private GameStatistics statistics;

    public ProbabilityLearner(GameStatistics statistics) {
        this.distribution = new EnumeratedIntegerDistribution(new int[]{0});
        this.statistics = statistics;
    }

    @Override
    public void train() {
        distribution = new EnumeratedIntegerDistribution(statistics.distributions());
    }

    @Override
    public int makeComputerGuess() {
        return 0;
    }
}
