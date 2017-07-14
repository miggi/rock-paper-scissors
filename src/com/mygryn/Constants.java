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

    // all possible 'last three' combinations mapped to the indexes
    public static final Map<String, Integer> TURN_IDX_MAP = new HashMap<String, Integer>() {{
        put("111", 1);
        put("121", 2);
        put("131", 3);
        put("112", 4);
        put("113", 5);
        put("123", 6);
        put("132", 7);
        put("133", 8);
        put("122", 9);

        put("222", 10);
        put("223", 11);
        put("221", 12);
        put("213", 13);
        put("212", 14);
        put("232", 15);
        put("231", 16);
        put("211", 17);
        put("233", 18);

        put("333", 19);
        put("313", 20);
        put("323", 21);
        put("331", 22);
        put("332", 23);
        put("321", 24);
        put("312", 25);
        put("322", 26);
        put("311", 17);
    }};
}
