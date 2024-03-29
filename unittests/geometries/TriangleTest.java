package geometries;

import org.junit.jupiter.api.Test;

import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for geometries. Triangle class

 */


class TriangleTest {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
//        Triangle tri = new Triangle(new Point(2,0,0),new Point(0,2,0),new Point(0,0,0));
//        assertEquals(new Vector(0,0,1),tri.getNormal(null));

    }
    @Test
    void findIntersections() {
        Triangle triangle = new Triangle(new Point(0, 1, 0), new Point(0, 5, 0), new Point(0, 3, 5));

        // ============ Equivalence Partitions Tests ==============
        // TC01: The intersection point is in the triangle
        assertEquals(List.of(new Intersectable.GeoPoint(triangle, new Point(0, 3, 1))),
                triangle.findGeoIntersectionsHelper(new Ray( new Vector(-1, 0, 1),new Point(1, 3, 0))),
                "The point supposed to be in the triangle");

        // TC02: The intersection point is outside the triangle, against edge
        assertNull(triangle.findGeoIntersectionsHelper(new Ray( new Vector(-1, 0, 1),new Point(1, 0, 0))),
                "The point supposed to be outside the triangle, against edge");

        // TC03: The intersection point is outside the triangle, against vertex
        assertNull(triangle.findGeoIntersectionsHelper(new Ray( new Vector(-1, 0.1, -0.1),new Point(1, 0, 0))),
                "The point supposed to be outside the triangle, against vertex");

        // =============== Boundary Values Tests ==================
        // TC10: The point is on edge
        assertNull(triangle.findGeoIntersectionsHelper(new Ray( new Vector(-1, 0, 0),new Point(1, 3, 0))),
                "The point supposed to be on edge");

        // TC11: The point is in vertex
        assertNull(triangle.findGeoIntersectionsHelper(new Ray( new Vector(-1, 0, 0),new Point(1, 1, 0))),
                "The point supposed to be in vertex");

        // TC12: The point is on edge's continuation
        assertNull(triangle.findGeoIntersectionsHelper(new Ray( new Vector(-1, 0.1, 0),new Point(1, 0, 0))),
                "The point supposed to be on edge's continuation");
    }
}