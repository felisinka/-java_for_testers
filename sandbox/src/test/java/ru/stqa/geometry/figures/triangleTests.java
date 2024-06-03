package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class triangleTests {

        @Test
        void canCalculateArea(){
            Assertions.assertEquals(24.0, Triangle.area(10,6,8));
            Assertions.assertEquals(6.0, Triangle.area(3,4,5));
        }
        @Test
        void canCalculatePerimeter(){
            Assertions.assertEquals(24.0, Triangle.perimeter(10,6,8));
            Assertions.assertEquals(12.0, Triangle.perimeter(3,4,5));
        }

    }

