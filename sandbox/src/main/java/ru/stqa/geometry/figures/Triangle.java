package ru.stqa.geometry.figures;

public class Triangle {

    public static void printTrianglePerimeter(double a,double b,double c){
        String text = String.format("Периметр треугольника со сторонами %f, %f, %f равен %f",
                a, b, c, perimeter(a, b, c));
        System.out.println(text);

    }

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static void printTriangleArea(double a,double b,double c){
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f равна %f",a,b,c, area(a, b, c));
        System.out.println(text);

    }

    public static double area(double a, double b, double c) {
        var p = perimeter(a,b,c)/2;
        return java.lang.Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
