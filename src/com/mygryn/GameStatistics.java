package com.mygryn;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.stream.Collectors;

import static com.mygryn.Constants.*;

public class GameStatistics {

    private Integer computerWins = 0;
    private Integer userWins = 0;
    private Double userWinRate = 0.0;
    private Integer gameNumber = 0;
    private List<String> allTurns = Lists.newArrayList();
    private List<Integer> turnsDistributions =  Lists.newArrayList();

    public void print() {
        String logLine = "-------------------------------" +
                "\n Game Number .......... " + gameNumber + "" +
                "\n Your Wins ............ " + userWins +
                "\n Computer Wins ........ " + computerWins +
                "\n Your Win Rate (%) .... " + userWinRate +
                "\n-------------------------------";

        System.out.println(logLine);
    }

    /**
     * Record and calculate all the statistics about Game
     *
     * @param turn       user choice
     * @param gameResult game result won/lost
     */
    public void recordTurn(Integer turn, int gameResult) {
        gameNumber++;
        recordUserWinRate(gameResult);
        recordTurnStats(turn.toString());
    }

    private void recordTurnStats(String turn) {
        allTurns.add(turn);
        if (gameNumber >= COMBINATIONS_LIMIT) {
            List<String> lastThreeTurns = getLastTurns(COMBINATIONS_LIMIT);

            String lastTurnsKey = lastThreeTurns
                    .stream()
                    .collect(Collectors.joining(""));

            turnsDistributions
                    .add(TURN_IDX_MAP.get(lastTurnsKey));
        }
    }

    private void recordUserWinRate(int result) {
        if (result == RESULT_WIN) {
            userWins++;
            userWinRate = Math.ceil(((double) userWins / (double) gameNumber) * 100);
        } else if(result == RESULT_FAIL) {
            computerWins++;
        }
    }

    public int[] distributions() {
        System.out.println("turnsDistributions -> " + turnsDistributions);
       return Ints
               .toArray(turnsDistributions);
    }

    public Integer getGameNumber() {
        return gameNumber;
    }

    public List<String> getLastTurns(int limit) {
        int length = allTurns.size();
        if (length > limit) {
            return allTurns.
                    subList(length - limit - 1, length - 1);
        }
        return allTurns;
    }
}
