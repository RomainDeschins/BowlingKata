package com.company;

import java.util.ArrayList;

public class Game {

    public String rollSequence;
    public ArrayList<Frame> line;
    public ArrayList<Integer> bonusPoints;

    public Game(String rollSequence) {
        this.rollSequence = rollSequence.replaceAll("\\s","");;
        this.line = new ArrayList<>(10);
        this.bonusPoints = new ArrayList<>();
        lineGenerator();
    }

    public void lineGenerator(){
        int frameCounter = 0;

        for (int rollIndex = 0; rollIndex < rollSequence.length(); rollIndex++) {

            if(frameCounter < 10){

                if (Character.compare(rollSequence.charAt(rollIndex), 'X') == 0) {
                    line.add(new Frame(true, false, 10));
                    frameCounter++;

                } else {
                    int firstRoll;
                    int secondRoll;

                    if(Character.compare(rollSequence.charAt(rollIndex), '-') == 0){
                        firstRoll = 0;
                    } else {
                        firstRoll = Integer.parseInt((Character.toString(rollSequence.charAt(rollIndex))));
                    }

                    rollIndex++;
                    if(Character.compare(rollSequence.charAt(rollIndex), '-') == 0) {
                        secondRoll = 0;
                        line.add(new Frame(false, false, firstRoll, secondRoll));
                        frameCounter++;
                    } else if (Character.compare(rollSequence.charAt(rollIndex), '/') == 0) {
                        secondRoll = 10 - firstRoll;
                        line.add(new Frame(false, true, firstRoll, secondRoll));
                        frameCounter++;
                    } else {
                        secondRoll = Integer.parseInt((Character.toString(rollSequence.charAt(rollIndex))));
                        line.add(new Frame(false, false, firstRoll, secondRoll));
                        frameCounter++;
                    }
                }

            } else {
                if (Character.compare(rollSequence.charAt(rollIndex), 'X') == 0) {
                    bonusPoints.add(10);
                } else if (Character.compare(rollSequence.charAt(rollIndex), '-') == 0){
                    bonusPoints.add(0);
                } else if (Character.compare(rollSequence.charAt(rollIndex), '/') == 0){
                    bonusPoints.add(10 - bonusPoints.get(0));
                }
                else {
                    bonusPoints.add(Integer.parseInt((Character.toString(rollSequence.charAt(rollIndex)))));
                }
            }
        }

    }

    public int getScore() {

        int score = 0;

        for (Frame frame : line) {

            if (frame.isStrike) {
                int firstAdditionalRoll = 0;
                int secondAdditionalRoll = 0;
                if (line.indexOf(frame) < 8) {
                    Frame nextFrame = line.get(line.indexOf(frame)+1);
                    if (nextFrame.isStrike){
                        firstAdditionalRoll = nextFrame.points[0];
                        secondAdditionalRoll = line.get(line.indexOf(frame)+2).points[0];
                    } else {
                        firstAdditionalRoll = nextFrame.points[0];
                        secondAdditionalRoll = nextFrame.points[1];
                    }
                } else if (line.indexOf(frame) == 8){
                    Frame nextFrame = line.get(line.indexOf(frame)+1);
                    if (nextFrame.isStrike){
                        firstAdditionalRoll = nextFrame.points[0];
                        secondAdditionalRoll = bonusPoints.get(0);
                    } else {
                        firstAdditionalRoll = nextFrame.points[0];
                        secondAdditionalRoll = nextFrame.points[1];
                    }
                } else {
                    firstAdditionalRoll = bonusPoints.get(0);
                    secondAdditionalRoll = bonusPoints.get(1);
                }
                score = score + frame.totalPoints + firstAdditionalRoll + secondAdditionalRoll;

            } else if (frame.isSpare) {
                int additionalRoll = 0;
                if (line.indexOf(frame) < 9) {
                    Frame nextFrame = line.get(line.indexOf(frame) + 1);
                    additionalRoll = nextFrame.points[0];
                } else {
                    additionalRoll = bonusPoints.get(0);
                }
                score = score + frame.totalPoints + additionalRoll;

            } else {
                score = score + frame.totalPoints;
            }
        }
        return score;
    }

}
