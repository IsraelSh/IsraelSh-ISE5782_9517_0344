package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Vector(1,1,100),new Point(0,0,1));
        List<Point> pointList = new LinkedList<Point>();
        pointList.add(new Point(10,10,100));
        pointList.add(new Point(-10,-10,-100));
        pointList.add(new Point(100,100,100));
        assertEquals(ray.findClosestPoint(pointList), pointList.get(0));
    }

    @Test
    void testFindClosestPoint2() {
        Ray ray = new Ray(new Vector(1,1,100),new Point(0,0,1));
        List<Point> pointList = null;
        assertNull(pointList,"the list of points is empty!");
    }

    @Test
    void testFindClosestPoint3() {
        Ray ray = new Ray(new Vector(1,1,100),new Point(0,0,1));
        List<Point> pointList = new LinkedList<Point>();
        pointList.add(new Point(10,10,100));
        pointList.add(new Point(-10,-10,-100));
        pointList.add(new Point(100,100,100));
        assertEquals(ray.findClosestPoint(pointList), pointList.get(0),
                "The first point is not a closest point to ray");

    }

    @Test
    void testFindClosestPoint4() {
        Ray ray = new Ray(new Vector(1,1,100),new Point(0,0,1));
        List<Point> pointList = new LinkedList<Point>();
        pointList.add(new Point(-10,-10,-100));
        pointList.add(new Point(100,100,100));
        pointList.add(new Point(10,10,100));
        assertEquals(ray.findClosestPoint(pointList), pointList.get(2),
                "The first point is not a closest point to ray");

    }
}
