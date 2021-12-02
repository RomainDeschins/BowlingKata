package com.company;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GameTestParameterized {

    @ParameterizedTest
    @CsvSource({
            "300, X X X X X X X X X X X X",
            "283, X X X X X X X X X X 3 /",
            "278, X X X X X X X X X X 3 2",
            "276, X X X X X X X X X 6/ X",
            "90, 9- 9- 9- 9- 9- 9- 9- 9- 9- 9-",
            "70, -7 -7 -7 -7 -7 -7 -7 -7 -7 -7",
            "150, 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5",
            "50, 32 32 32 32 32 32 32 32 32 32"
    })
    void getScore(int expectedScore, @AggregateWith(GameAggregator.class) Game game) {
        assertEquals(expectedScore, game.getScore());
    }
}