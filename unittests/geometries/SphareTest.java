package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

/**
 * Unit tests for geometries.Sphare class
 *
 */
public class SphareTest {

    /**
     * Test method for {@link geometries.Sphare#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p= new Point(1, 1, 6);
        Point p1=new Point(1,1,1);
        Sphare s = new Sphare(p1,5);
        Vector v= p1.subtract(p).normalize();

        assertEquals(v, s.getNormal(p), "Bad normal to sphere");//regular case
    }
}

















/*class SphareTest {

    @Test
    void getNormal() {
    }

    @Test
    void getCenter() {
    }

    @Test
    void getRadius() {
    }

    @Test
    void testToString() {
    }
}*/