package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Triangle.printTrianglePerimeter(5,5,5);
        Triangle.printTriangleArea(5,5,5);
        Rectangle.printRectangleArea(5,3);
        Rectangle.printRectanglePerimeter(5,3);
       Square.printSquarePerimeter(5);
       Square.printSquareArea(5);
    }
}
