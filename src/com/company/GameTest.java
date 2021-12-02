package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void getScore() {
        Game bowlingGame = new Game("X X X X X X X X X X X X");
        assertEquals(300, bowlingGame.getScore());
    }




}