package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public double Point(double x1, double y1) {
    this.x = x1;
    this.y = y1;
    return Point(x1, y1);
  }

  public double distance(Point p2) {
    return Math.sqrt((x - this.x) * (x - this.x) + (y - this.y) * (y - this.y));
  }
}
