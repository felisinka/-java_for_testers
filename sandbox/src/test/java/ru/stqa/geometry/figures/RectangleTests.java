package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void canCalculateArea(){
        var r = new Rectangle(10,6);
        Assertions.assertEquals(60.0, r.area());
        r = new Rectangle(3,4);
        Assertions.assertEquals(12.0, r.area());
    }
    @Test
    void canCalculatePerimeter(){
        var r = new Rectangle(10,6);
        Assertions.assertEquals(32.0, r.perimeter());
        r = new Rectangle(3,4);
        Assertions.assertEquals(14.0, r.perimeter());
    }
}
