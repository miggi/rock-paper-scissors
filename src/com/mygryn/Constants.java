package com.mygryn;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String ROCK = "rock";
    public static final String PAPER = "paper";
    public static final String SCISSORS = "scissors";
    public static final Integer COMBINATIONS_LIMIT = 3;
    public static final Integer LEARN_LIMIT = 10;

    public static final int RESULT_WIN = 1;
    public static final int RESULT_FAIL = -1;
    public static final int RESULT_TIE = 0;


    public static final int ROCK_ID = 1;
    public static final int PAPER_ID = 2;
    public static final int SCISSORS_ID = 3;

    // all possible combinations mapped to the indexes
    public static final Map<String, Integer> TURN_IDX_MAP = new HashMap<String, Integer>() {{
        put("111", 1);
        put("112", 2);
        put("113", 3);
        put("123", 4);
        put("132", 5);

        put("222", 6);
        put("223", 7);
        put("221", 8);
        put("213", 9);
        put("231", 10);

        put("333", 11);
        put("331", 12);
        put("332", 13);
        put("321", 14);
        put("312", 15);
    }};
}
