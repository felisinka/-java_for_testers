package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectanglePerimeter(double a,double b){
        String text = String.format("Периметр прямоугольника со сторонами %f и %f равен %f", a,b,perimeter(a, b));
        System.out.println(text);

    }

    public static double perimeter(double a, double b) {
        return 2*(a + b);
    }

    public static void printRectangleArea(double a,double b){
        String text = String.format("Площадь прямоугольника со сторонами %f и %f равна %f",a,b,area(a, b));
        System.out.println(text);

    }

    public static double area(double a, double b) {
        return a*b;
    }
}
