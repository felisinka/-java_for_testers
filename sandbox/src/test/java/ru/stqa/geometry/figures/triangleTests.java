package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var t = new Triangle(10.0, 6.0, 8.0);
        Assertions.assertEquals(24.0, t.area());
        t = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(6.0, t.area());
    }

    @Test
    void canCalculatePerimeter() {
        var t = new Triangle(10.0, 6.0, 8.0);
        Assertions.assertEquals(24.0, t.perimeter());
        t = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(12.0, t.perimeter());
    }

    @Test
    void cannotCreateTriangleWithNegativeSideA() {
        try {
            new Triangle(-5, 1, 3);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle(5, -1, 3);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle(5, 1, -3);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void cannotCreateTriangleWithBrokenSideInequality() {
        try {
            new Triangle(5, 1, 3);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(10.0, 6.0, 8.0);
        var t2 = new Triangle(10.0, 6.0, 8.0);
        Assertions.assertEquals(t1,t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(10.0, 6.0, 8.0);
        var t2 = new Triangle(6.0, 10.0, 8.0);
        Assertions.assertEquals(t1,t2);
    }

    @Test
    void testEquality3() {
        var t1 = new Triangle(10.0, 6.0, 8.0);
        var t2 = new Triangle(8.0, 10.0, 6.0);
        Assertions.assertEquals(t1,t2);
    }
}

