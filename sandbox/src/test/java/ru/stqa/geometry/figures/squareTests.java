package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea(){
        var s = new Square(5.0);
        Assertions.assertEquals(25.0, s.area());
    }

    @Test
    void canCalculatePerimeter(){
        var s = new Square(5.0);
        Assertions.assertEquals(20.0, s.perimeter());
    }
}
