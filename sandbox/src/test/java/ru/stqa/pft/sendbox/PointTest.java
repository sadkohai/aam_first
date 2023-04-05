package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {

  @Test
  public void testArea(){
    Point p1 = new Point(2.1, 3.5);
    Point p2 = new Point(4.1,4.9);
     Assert.assertEquals(p1.distance(p2),2.4413111231467406); //Проверка, что расстояние вычислено верно
  }
}