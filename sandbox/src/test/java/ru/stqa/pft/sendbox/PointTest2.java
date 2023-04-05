package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest2 {

  @Test
  public void testArea(){
    Point p1 = new Point(2.1, 3.5);
    Point p2 = new Point(4.1,4.9);
      Assert.assertNotEquals(p1.distance(p2), 2.441311123146740); //Проверяем, что значение совпадает с точностью до последнего знака после запятой
  }
}
