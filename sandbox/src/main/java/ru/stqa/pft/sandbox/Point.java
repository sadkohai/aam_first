package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public double Point(double x1, double y1) {
    this.x = x1;
    this.y = y1;
    return Point(x,y);
  }

  public double distance(Point p2) {
    return Math.sqrt((p2.x - this.x) * (p2.x - this.x) + (p2.y - this.y) * (p2.y - this.y));
  }
}
