package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Unit tests for geometries. Plane class
 *
 */
class PlaneTest {

    @Test
    void testGetNormal() {

        // ============ Equivalence Partitions Tests ==============
        //To check the plane we need to check if the normal is good. if not - print it's an incorrect normal.
        Point p1 = new Point(1, 2, 0);
        Point p2 = new Point(-4, 7, 0);
        Point p3 = new Point(1, 0, 5);
        Plane p = new Plane(p1, p2, p3);
        Vector v1 = p1.subtract(p2);
        Vector v2 = p2.subtract(p3);
        Vector v3 = p3.subtract(p1);
        Vector n = p.getNormal(p1);
        assertTrue(isZero(v1.dotProduct(n)), "ERROR: incorrect normal to plane");//if the dot product== 0, it's really the normal to the plane
        assertTrue(isZero(v2.dotProduct(n)), "ERROR: incorrect normal to plane");//if the dot product== 0, it's really the normal to the plane
        assertTrue(isZero(v3.dotProduct(n)), "ERROR: incorrect normal to plane");//if the dot product== 0, it's really the normal to the plane
        //Boundary

        try {
            new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(4, 8, 12)).getNormal(p1);//a case that all the points are on the same vector- cannot create the plane
            fail("GetNormal() should throw an exception, all the points are on the same vector");
        } catch (Exception e) {
        }
        //case that two points are identical
        try{
            Point p4 = new Point(1,2,3);
            Point p5 = new Point(1,2,3);
            Point p6 = new Point(1, 0, 5);
            Plane pl = new Plane(p4, p5, p6);
            fail("getNormal() should throw an exception - two points are identical");
        }catch (Exception e) {
        }
    }



    @Test
    void findIntersections() {
        Plane plane = new Plane(new Point(1,0,1), new Point(0,1,1), new Point(1,1,1));

        // ================ EP: The Ray must be neither orthogonal nor parallel to the plane ==================
        //TC01: Ray intersects the plane
        assertEquals(List.of(new Point(1,0.5,1)),
                plane.findIntersections(new Ray(new Vector(1,0,1),new Point(0,0.5,0))),
                "Ray intersects the plane");

        //TC02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Vector(1,2,5),new Point(1,0.5,2))),
                "Ray does not intersect the plane");


        // ====================== Boundary Values Tests =======================//
        // **** Group: Ray is parallel to the plane
        //TC10: The ray included in the plane
        assertNull(plane.findIntersections(new Ray(new Vector(1,0,0),new Point(1,2,1))),
                "Ray is parallel to the plane, the ray included in the plane");

        //TC11: The ray not included in the plane
        assertNull(plane.findIntersections(new Ray(new Vector(1,0,0),new Point(1,2,2))),
                "Ray is parallel to the plane, the ray not included in the plane");

        // **** Group: Ray is orthogonal to the plane
        //TC12: according to 𝑃0, before the plane
        assertEquals(List.of(new Point(1,1,1)),
                plane.findIntersections(new Ray( new Vector(0,0,1),new Point(1,1,0))),
                "Ray is orthogonal to the plane, according to p0, before the plane");

        //TC13: according to 𝑃0, in the plane
        assertNull(plane.findIntersections(new Ray(new Vector(0,0,1),new Point(1,2,1))),
                "Ray is orthogonal to the plane, according to p0, in the plane");

        //TC14: according to 𝑃0, after the plane
        assertNull(plane.findIntersections(new Ray(new Vector(0,0,1),new Point(1,2,2))),
                "Ray is orthogonal to the plane, according to p0, after the plane");

        // **** Group: Ray is neither orthogonal nor parallel to
        //TC15: Ray begins at the plane
        assertNull(plane.findIntersections(new Ray( new Vector(2,3,5),new Point(2,4,1))),
                "Ray is neither orthogonal nor parallel to ray and begin at the plane");

        //TC16: Ray begins in the same point which appears as reference point in the plane
        assertNull(plane.findIntersections(new Ray( new Vector(2,3,5),new Point(1,0,1))),
                "Ray is neither orthogonal nor parallel to ray and begins in the same point " +
                        "which appears as reference point in the plane");
    }

}
