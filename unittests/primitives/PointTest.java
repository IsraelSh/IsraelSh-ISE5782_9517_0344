package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {


    @Test
    void add() {
        Point p1 = new Point(1,2,3);
        Vector v = new Vector(5,6,8);
        Point addingPiont = new Point(6 , 8 , 11);
        assertEquals(p1.add(v) , addingPiont , "the add() function returns wrong value.");
        //Boundary
        assertThrows(IllegalArgumentException.class,() -> p1.subtract(p1) ,
                "do not throw error when subtracts the same point");
    }

    @Test
    void subtract() {
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(5,6,8);
        Vector subtractionVector = new Vector(-4 , -4 , -5);
        assertEquals(p1.subtract(p2) , subtractionVector , "the subtraction() function returns wrong value.");
        assertThrows(IllegalArgumentException.class,() -> p1.subtract(p1) ,
                "do not throw error when subtracts the same point");
    }
}