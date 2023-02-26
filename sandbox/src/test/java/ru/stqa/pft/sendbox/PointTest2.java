package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest2 {

  @Test
  public void testArea(){
    Point P = new Point();
    P.x = 2.1;
    P.y = 3.5;
    Assert.assertNotEquals(P.distance(4.1, 4.9), 2.441311123146740); //Проверяем, что значение совпадает с точностью до последнего знака после запятой
  }
}
