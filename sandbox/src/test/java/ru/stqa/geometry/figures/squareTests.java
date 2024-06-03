package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class squareTests {

    @Test
    void canCalculateArea(){
        Assertions.assertEquals(25.0, Square.area(5));
    }
}
