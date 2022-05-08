import geometries.Plane;
import geometries.Sphare;
import geometries.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
     * Integration test for camera and shapes.
     */
    public class IntegrationTests {

        private static Vector vTo;
        private static Vector vUp;
        private static Camera camera;

        @BeforeAll
        public static void setupAll() {
            Point pZero = new Point(0, 0, 0);
            vTo = new Vector(0, 0, -1);
            vUp = new Vector(0, 1, 0);
            camera = new Camera(pZero, vTo, vUp);
        }

    @Test
    public void cameraPlaneIntersections() {


        /* TC01: plane with 9 points that intersect */

        /* Variables */
        Plane planeTestCase1 = new Plane( new Point(0, 0, -3), new Vector(0, 0, 1));
        camera.setVPDistance(1).setVPSize(3, 3);
        List<Point> result1 = camera.findRay(3, 3, planeTestCase1);

        /* Asserts */
        assertEquals(9, result1.size(), "ERROR: Except for 9 intersect points");

        /* TC02: plane with 9 points that intersect */

        /* Variables */
        Plane planeTestCase2 = new Plane(new Point(0, 0, -2),new Vector(0, 0.5, -1));
        camera.setVPDistance(1).setVPSize(3, 3);
        List<Point> result2 = camera.findRay(3, 3, planeTestCase2);

        /* Asserts */
        assertEquals(9, result2.size(), "ERROR: Except for 9 intersect points");


        /* TC03: plane with 6 points that intersect */

        /* Variables */
        Plane planeTestCase3 = new Plane( new Point(0, 0, -2), new Vector(0, 4, -1));
        camera.setVPDistance(1).setVPSize(3, 3);
        List<Point> result3 = camera.findRay(3, 3, planeTestCase3);

        /* Asserts */
        assertEquals(6, result3.size(), "ERROR: Except for 9 intersect points");
    }

    @Test
    public void cameraTriangleIntersections() {

        /* TC01: triangle with one point that intersect */

        /* Variables */
        Triangle triangleTestCase1 = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2),
                new Point(-1, -1, -2));
        camera.setVPDistance(1).setVPSize(3, 3);
        List<Point> result1 = camera.findRay(3, 3, triangleTestCase1);

        /* Asserts */
        assertEquals(1, result1.size(), "ERROR: Except for one intersect point");

        /* TC02: triangle with 2 points that intersect */

        /* Variables */
        Triangle triangleTestCase2 = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2),
                new Point(-1, -1, -2));
        camera.setVPDistance(1).setVPSize(3, 3);
        List<Point> result2 = camera.findRay(3, 3, triangleTestCase2);

        /* Asserts */
        assertEquals(2, result2.size(), "ERROR: Except for 2 intersect points");
    }
        @Test
        public void cameraSphereIntersections() {
            /* TC01: sphere with 2 points that intersect */

            /* Variables */
            Sphare sphereTC01 = new Sphare(new Point(0, 0, -3), 1);
            camera.setVPDistance(1).setVPSize(3, 3);
            List<Point> result01 = camera.findRay(3, 3, sphereTC01);
            List<Point> exceptedResult01 = Arrays.asList(new Point(0, 0, -2), new Point(0, 0, -4));

            /* Asserts */
            Assertions.assertNotNull(result01,
                    "ERROR: There are no intersection, and except for 2 points");
            assertTrue(
                    exceptedResult01.size() == result01.size() && exceptedResult01.containsAll(result01),
                    "ERROR: There aren't the same points that intersect");

            /* TC02: sphere with 18 points that intersect */

            /* Variables */
            Sphare sphereTC02 = new Sphare(new Point(0, 0, -2.5), 2.5);
            Camera cameraTC02 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
            cameraTC02.setVPDistance(1).setVPSize(3, 3);
            List<Point> result02 = cameraTC02.findRay(3, 3, sphereTC02);

            /* Asserts */
            assertEquals(18, result02.size(), "ERROR: Except for 18 intersect points");

            /*
             * TC03: sphere with 10 points that intersect,
             * part of the sphere is behind the view plane
             */

            /* Variables */
            Sphare sphereTC03 = new Sphare(new Point(0, 0, -2), 2);
            Camera cameraTC03 = new Camera(new Point(0, 0, 0.5), vTo, vUp);
            cameraTC03.setVPDistance(1).setVPSize(3, 3);
            List<Point> result03 = cameraTC03.findRay(3, 3, sphereTC03);

            /* Asserts */
            assertEquals(10, result03.size(), "ERROR: Except for 10 intersect points");


            /* TC04: sphere with 9 points that intersect, part of the sphere is behind the camera */

            /* Variables */
            Sphare sphereTC04 = new Sphare(new Point(0, 0, 0), 4);
            Camera cameraTC04 = new Camera(new Point(0, 0, 1), vTo, vUp);
            cameraTC04.setVPDistance(1).setVPSize(3, 3);
            List<Point> result04 = cameraTC04.findRay(3, 3, sphereTC04);

            /* Asserts */
            assertEquals(9, result04.size(), "ERROR: Except for 9 intersect points");

            /* TC05: sphere with no intersect, the sphere is behind the camera */

            /* Variables */
            Sphare sphereTC05 = new Sphare(new Point(0, 0, 1), 0.5);
            camera.setVPDistance(1).setVPSize(3, 3);
            List<Point> result05 = camera.findRay(3, 3, sphereTC05);

            /* Asserts */
            assertNull(result05, "ERROR: Except for null, there are no intersect points");
        }


    }

