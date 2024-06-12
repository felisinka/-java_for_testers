package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (double sideA, double sideB, double sideC){

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(sideA, triangle.sideA) == 0 && Double.compare(sideB, triangle.sideB) == 0 && Double.compare(sideC, triangle.sideC) == 0)
                ||
                (Double.compare(sideA, triangle.sideC) == 0 && Double.compare(sideB, triangle.sideB) == 0 && Double.compare(sideC, triangle.sideA) == 0)
                ||
                (Double.compare(sideA, triangle.sideB) == 0 && Double.compare(sideB, triangle.sideA) == 0 && Double.compare(sideC, triangle.sideC) == 0)
                ||
                (Double.compare(sideA, triangle.sideA) == 0 && Double.compare(sideB, triangle.sideC) == 0 && Double.compare(sideC, triangle.sideB) == 0)
                ||
                (Double.compare(sideA, triangle.sideB) == 0 && Double.compare(sideB, triangle.sideC) == 0 && Double.compare(sideC, triangle.sideA) == 0)
                ||
                (Double.compare(sideA, triangle.sideC) == 0 && Double.compare(sideB, triangle.sideA) == 0 && Double.compare(sideC, triangle.sideB) == 0)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideA, sideB, sideC);
    }

    public Triangle {
    if (sideA<0||sideB<0||sideC<0||sideA+sideB<sideC||sideA+sideC<sideB||sideB+sideC<sideA) {
      throw new IllegalArgumentException("Triangle side should be non-negative");
    }


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
