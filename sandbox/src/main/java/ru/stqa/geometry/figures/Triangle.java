package ru.stqa.geometry.figures;

public class Triangle {

    double sideA;
    double sideB;
    double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA=sideA;
        this.sideB=sideB;
        this.sideC=sideC;
    }

    public static void printTrianglePerimeter(Triangle t){
        String text = String.format("Периметр треугольника со сторонами %f, %f, %f равен %f",
                t.sideA,t.sideB,t.sideC, t.perimeter());
        System.out.println(text);

    }

    public static void printTriangleArea(Triangle t){
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f равна %f",
                t.sideA,t.sideB,t.sideC, t.area());
        System.out.println(text);

    }

    public double area() {
        var p = this.perimeter()/2;
        return java.lang.Math.sqrt(p*(p-this.sideA)*(p-this.sideB)*(p-this.sideC));
    }

    public double perimeter() {
        return this.sideA + this.sideB + this.sideC;
    }
}
