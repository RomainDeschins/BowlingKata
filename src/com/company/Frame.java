package com.company;

public class Frame {

    public boolean isStrike;
    public boolean isSpare;
    public int[] points;
    public int totalPoints;

    public Frame(boolean isStrike, boolean isSpare, int... points) {
        this.isStrike = isStrike;
        this.isSpare = isSpare;
        this.points = points;
        totalPointsCalculation();
    }

    public void totalPointsCalculation(){
        for (int points: points){
            this.totalPoints = totalPoints + points;
        }
    }



}
