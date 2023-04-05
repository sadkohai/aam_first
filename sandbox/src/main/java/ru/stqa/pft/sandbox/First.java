package ru.stqa.pft.sandbox;

import javax.xml.transform.Result;
import java.awt.geom.Point2D;

public class First{
	public static void main (String[] args) {
		hello("world");
		hello("user");
		hello ("alexey");

		Square s = new Square(5);
		System.out.println("P квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("P прямоугольника со стоонами " + r.a + " и " + r.b + " = " + r.area());

		Point p1 = new Point(2.1, 3.5);
		Point p2 = new Point(4.1,4.9);

		System.out.println("Расстояние между точками " + "Point1" + "("  + p1.x + ", " + p1.y + ")" + " и Point2" +  "(4.1, 4.9)" + " = " + p2.distance(p1));
	}
	
	public static void hello (String somebody) {
		System.out.println("Hello, " + somebody +"!");
	}
}