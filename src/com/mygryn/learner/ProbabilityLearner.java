package com.mygryn.learner;

import com.google.common.collect.Lists;
import com.mygryn.GameStatistics;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mygryn.Constants.*;

public class ProbabilityLearner implements Learner {

    private EnumeratedIntegerDistribution distribution;
    private GameStatistics statistics;

    public ProbabilityLearner(GameStatistics statistics) {
        this.distribution = new EnumeratedIntegerDistribution(new int[]{0});
        this.statistics = statistics;
    }

    /**
     * Checks all possible next steps hypothesis based on historical train data
     *
     * 1. Find most probable user next step;
     * 2. Offer counter-measure for that step
     *
     * @return computer step
     */
    @Override
    public int makeComputerGuess() {
        //train
        distribution = new EnumeratedIntegerDistribution(statistics.distributions());

        String lastTwoKeys = statistics
                .getLastTurns(2)
                .stream()
                .collect(Collectors.joining(""));

        int predicted = Lists
                .newArrayList("1", "2", "3")
                .stream()
                .map(v -> {
                    Integer idx = TURN_IDX_MAP.get(lastTwoKeys + v);
                    return new IndexProbability(idx, distribution.probability(idx));
                })
                .max(Comparator.comparing(IndexProbability::getProbability))
                .get() // should be safe
                .getIdx();

        return counterMeasure(recoverStepFromDistributions(predicted));
    }

    /**
     *  Find counter-measure for predicted step of user
     *
     * @param predictedUserStep user step
     * @return
     */
    private Integer counterMeasure (int predictedUserStep) {
        switch (predictedUserStep) {
            case ROCK_ID:
                return PAPER_ID;

            case PAPER_ID:
                return SCISSORS_ID;

            case SCISSORS_ID:
                return ROCK_ID;
        }
        return 0;
    }

    /**
     * Recover predicted user step ID from predicted distribution keys
     *
     * @param predicted distribution key from TURN_IDX_MAP ( e.g. 21 )
     * @return
     */
    private int recoverStepFromDistributions (Integer predicted) {
        String steps = TURN_IDX_MAP.entrySet().stream()
                .filter(e -> Objects.equals(e.getValue(), predicted))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("123");

        return Integer
                .valueOf(steps.substring(2));
    }

    private class IndexProbability {
        Integer idx;
        Double probability;

        public IndexProbability(Integer idx, Double probability) {
            this.idx = idx;
            this.probability = probability;
        }

        public Integer getIdx() {
            return idx;
        }

        public Double getProbability() {
            return probability;
        }
    }
}
