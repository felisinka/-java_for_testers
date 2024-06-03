package ru.stqa.geometry.figures;

public record Rectangle (double sideA, double sideB) {

    public static void printRectanglePerimeter(Rectangle r){
        String text = String.format("Периметр прямоугольника со сторонами %f и %f равен %f",
                r.sideA,r.sideB,r.perimeter());
        System.out.println(text);

    }


    public static void printRectangleArea(Rectangle r){
        String text = String.format("Площадь прямоугольника со сторонами %f и %f равна %f",
                r.sideA,r.sideB,r.area());
        System.out.println(text);

    }

    public double area() {
        return this.sideA*this.sideB;
    }

    public double perimeter() {
        return 2* (this.sideA+this.sideB);
    }
}
