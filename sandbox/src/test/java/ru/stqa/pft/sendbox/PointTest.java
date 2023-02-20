package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Point;

public class PointTest {

  @Test
  public void testArea(){
    Point P = new Point();
    P.x1 = 0;
    P.y1 = 1;
    P.x2 = 2;
    P.y2 = 4;
    Assert.assertEquals(P.distance(), 3.605551275463989);
  }
}
