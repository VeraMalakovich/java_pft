package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Vera");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    //создание объектов, ссылка на конструктор с установкой значения атрибута
    Point p1 = new Point(4, 5);
    Point p2 = new Point(3, 8);
    System.out.println("Расстояние между двумя точками " + distance(p1, p2));
    System.out.println("Расстояние между двумя точками " + p1.distance(p2));
  }

  public static void hello(String somebody) {
  System.out.println("Hello, " + somebody + "!");
}
  //функция вычисления расстояния между точками
  public static double distance(Point p1, Point p2) {
    double l = (p2.x - p1.x);
    double m = (p2.y - p1.y);
    double res = Math.sqrt((l * l) + (m * m));
    return res;
  }
}