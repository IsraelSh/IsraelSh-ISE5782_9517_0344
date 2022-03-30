package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries. Tube class
 *
 */
class TubeTest {
    /**
     *
     */
    @Test
    public void testGetNormal() {
        // Equivalence
        Ray r= new Ray(new Vector(0,1,0), new Point(0,0,0));
        Tube t= new Tube(r,1);
        Point p = new Point(1,0,1);
        Vector n= t.getNormal(p);
        //assertTrue("bad normal to tube",isZero(r.getDir().dotProduct(n)));
        // =============== Boundary Values Tests ==================

        try {
            new Tube(r,0).getNormal(p);
            fail("GetNormal() should throw an exception, but it failed");
        } catch (Exception e) {}
    }
}