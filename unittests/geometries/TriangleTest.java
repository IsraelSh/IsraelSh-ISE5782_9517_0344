package geometries;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * Unit tests for geometries. Triangle class

 */


class TriangleTest {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tri = new Triangle(new Point(2,0,0),new Point(0,2,0),new Point(0,0,0));
        assertEquals(new Vector(0,0,1),tri.getNormal(null));

    }
}