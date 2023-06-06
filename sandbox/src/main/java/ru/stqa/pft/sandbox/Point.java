package ru.stqa.pft.sandbox;

public class Point {
  //добавляем атрибуты объекта, координаты точек на плоскости
  public double x;
  public double y;
  //создание конструктора, который заполняет атрибуты какими-то значениями
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  //добавляем функцию, которая вычисляет расстояние между двумя точками
  public double distance(Point p) {
    double l = (p.x - x);
    double m = (p.y - y);
    double res = Math.sqrt((l * l) + (m * m));
    return res;
  }
}
