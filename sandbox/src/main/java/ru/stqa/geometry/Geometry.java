package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Triangle.printTrianglePerimeter(new Triangle(5,5,5));
        Triangle.printTriangleArea(new Triangle(5,5,5));
        Rectangle.printRectangleArea(new Rectangle(5,3));
        Rectangle.printRectanglePerimeter(new Rectangle(5,3));
       Square.printSquarePerimeter(new Square(5));
       Square.printSquareArea(new Square(5));
    }
}
