package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
       var squares = Stream.of(new Square(7),new Square(5),new Square(3));
      /*  for (Square square : squares) {
            Square.printSquareArea(square);
        }*/

        Consumer<Square> print = (square) ->
                                {Square.printSquareArea(square);};
        squares.forEach(print);
     /*   Triangle.printTrianglePerimeter(new Triangle(5,5,5));
        Triangle.printTriangleArea(new Triangle(5,5,5));
        Rectangle.printRectangleArea(new Rectangle(5,3));
        Rectangle.printRectanglePerimeter(new Rectangle(5,3));
       Square.printSquarePerimeter(new Square(5));*/

    }
}
