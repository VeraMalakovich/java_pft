package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistanceTrue() {
    Point p1 = new Point(4, 6);
    Point p2 = new Point(3, 8);
    Assert.assertEquals(p1.distance(p2), 2.23606797749979);
  }

  @Test
  public void testDistanceFalse() {
    Point p1 = new Point(4, 7);
    Point p2 = new Point(3, 8);
    double temp = p1.distance(p2);
    Assert.assertNotEquals(p1.distance(p2), 2.23606797749979, "Result is invalid!");
  }
}
