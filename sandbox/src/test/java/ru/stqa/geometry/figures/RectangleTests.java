package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateArea(){
        Assertions.assertEquals(60.0, Rectangle.area(10,6));
        Assertions.assertEquals(12.0, Rectangle.area(3,4));
    }
    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(32.0, Rectangle.perimeter(10,6));
        Assertions.assertEquals(14.0, Rectangle.perimeter(3,4));
    }
}
