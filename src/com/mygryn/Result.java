package com.mygryn;

public class Result {
    private String human = "";
    private String computer = "";
    private Integer result = 0;

    public Result(String human, String computer, Integer result) {
        this.human = human;
        this.computer = computer;
        this.result = result;
    }

    public String getHuman() {
        return human;
    }

    public String getComputer() {
        return computer;
    }

    public Integer getResult() {
        return result;
    }
}
