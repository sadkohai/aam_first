package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {

  @Test
  public void testArea(){
    Point P = new Point();
    P.x = 2.1;
    P.y = 3.5;
    Point p2 = new Point();
    p2.x = 4.1;
    p2.y = 4.9;
    Assert.assertEquals(P.distance(p2), 2.4413111231467406); //Проверка, что расстояние вычислино верно
  }
}