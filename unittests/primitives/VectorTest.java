package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class VectorTest{

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1 = new Vector(1,1,1);
        Vector v2 = new Vector(-1,-1,1);
        Vector v3 = new Vector(-1,-1,-1);
        assertEquals(new Vector(0,0,2), v1.add(v2), "Add() wrong result length");//check if the add works well (not the zero vector)

        // =============== Boundary Values Tests ==================
        //
        try {
            v1.add(v3);//zero vector
            fail("Add() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @Test
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(1,3,3);

        assertEquals(new Vector(0,-1,0), v1.subtract(v2), "Substract() wrong result length");//regular case

        // =============== Boundary Values Tests ==================
        //
        try {
            v1.subtract(v1);//zero
            fail("Substract() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    public void testScale() {
        Vector v1 = new Vector(1, 1, 1);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(-2, -2, -2), v1.scale(-2));//regular case
        // =============== Boundary Values Tests ==================
        //
        try {
            v1.scale(0);//multiple by 0- cannot get the zero vector
            fail("Scale() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);//vr=(0,3,-2)

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length());

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);//if the vector are on the same direction, the cross product must be 0
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
    }


    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    public void testDotProduct() {
        Vector v1 = new Vector(1, 1, 1);
        Vector v2 = new Vector(3, 3, 1);
        Vector v3 = new Vector(0, -3, 9);
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(v1.dotProduct(v2) -7), "ERROR: dotProduct() wrong value");//check regular case
        assertTrue(isZero(v3.dotProduct(v2)), "ERROR: dotProduct() wrong value for orthogonal vectors");//check the orthogonal case- if the result is 0
        // =============== Boundary Values Tests ==================
        try {
            assertTrue(isZero(new Vector(0,0,0).dotProduct(v1)), "dotProduct() wrong result length");//in that case, the ctor of vector will throw an exception- the zero vector
            fail("dotProduct() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#lenghtSquared()}.
     */
    @Test
    public void testLenghtSquared() {
        Vector v1 = new Vector (1, 3, 9);
        // ============ Equivalence Partitions Tests ==============
        assertTrue(isZero(v1.lengthSquared() - 91), "LengthSquared() wrong result length");//regular case
        // =============== Boundary Values Tests ==================
        //
        try {
            assertTrue(isZero(new Vector(0,0,0).lengthSquared()), "LengthSquared() wrong result length");//in that case, the ctor of vector will throw an exception- the zero vector
            fail("LengthSquared() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    public void testLength() {
        Vector v1= new Vector(5,3,1);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1.length(),Math.sqrt(35));//regular case
        // =============== Boundary Values Tests ==================
        //
        try {
            assertTrue(isZero(new Vector(0,0,0).length()), "Length() wrong result length");//in that case, the ctor of vector will throw an exception- the zero vector
            fail("Length() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void testNormalize() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1.normalize().length(), 1,0.0001);//regular case
        // =============== Boundary Values Tests ==================
        //
        try {
            assertTrue(isZero(new Vector(0,0,0).normalize().length()), "Normalize() wrong result length");//in that case, the ctor of vector will throw an exception- the zero vector
            fail("Normalize() should throw an exception, but it failed");
        } catch (Exception e) {}
    }

    /**
     * Test method for {@link primitives.Vector#normalized()}.
     */
    @Test
    public void testNormalized() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(v1.normalize().length(), 1,0.0001);//regular case
        // =============== Boundary Values Tests ==================
        //
        try {
            assertTrue(isZero(new Vector(0,0,0).normalize().length()));//in that case, the ctor of vector will throw an exception- the zero vector
            fail("Normalize() should throw an exception, but it failed");
        } catch (Exception e) {}
    }


}














/*class VectorTest {

    @Test
    void testToString() {
    }

    @Test
    void add() {
    }

    @Test
    void scale() {
    }

    @Test
    void crossProduct() {
    }

    @Test
    void lengthSquared() {
    }

    @Test
    void length() {
    }

    @Test
    void normalize() {
    }

    @Test
    void dotProduct() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void subtract() {
    }
} */