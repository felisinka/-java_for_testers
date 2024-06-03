package ru.stqa.geometry.figures;

public record Square (double side){

    public static void printSquarePerimeter(Square s){
        String text = String.format("Периметр квадрата со стороной %f равен %f", s.side, s.perimeter());
        System.out.println(text);

    }

    public static void printSquareArea(Square s){
       String text = String.format("Площадь квадрата со стороной %f равна %f", s.side,  s.area());
        System.out.println(text);

    }

    public double area() {
        return this.side*this.side;
    }

    public double perimeter() {
        return this.side*4;
    }
}
