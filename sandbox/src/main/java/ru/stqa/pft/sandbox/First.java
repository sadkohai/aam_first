package ru.stqa.pft.sandbox;

import javax.xml.transform.Result;

public class First{
	
	public static void main (String[] args) {
		hello("world");
		hello("user");
		hello ("alexey");

		Square s = new Square(5);
		System.out.println("P квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4,6);
		System.out.println("P прямоугольника со стоонами " + r.a + " и " + r.b + " = " + r.area());

		Point P = new Point ();
		P.x1 = 0;
		P.y1 = 1;
		P.x2 = 2;
		P.y2 = 4;
		System.out.println("Расстояние между точками = " + P.distance());
	}
	
	public static void hello (String somebody) {
		System.out.println("Hello, " + somebody +"!");
	}
}