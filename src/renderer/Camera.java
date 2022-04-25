package renderer;

import geometries.Intersectable;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Camera {
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private Point p0;
    private int distance;
    private double width;
    private double height;

    /**
     * Constructor to create new Camera.
     * @param p0 the center point
     * @param vTo1 MUST be orthogonal to vectorUp
     * @param vUp1 MUST be orthogonal to vectorTo
     * @throws IllegalArgumentException if vectorTo isn't orthogonal to vectorUp
     */
    public Camera(Point p0, Vector vTo1, Vector vUp1) throws IllegalArgumentException {
        this.p0 = p0;
        this.vTo = vTo1.normalize();
        this.vUp = vUp1.normalize();
        if (!isZero(vTo1.dotProduct(vUp1))) {
            throw new IllegalArgumentException("ERROR: vectorTo and vectorUp must be orthogonal");
        }
        this.vRight = vTo1.crossProduct(vUp1).normalize();
    }

    public Point getP0() {return p0;}

    public Vector getvTo() {return vTo;}

    public Vector getvUp() {return vUp;}

    public Vector getvRight() {return vRight;}

    public int getDistance() {return distance;}

    /**
     * Set the distance from the camera to the view plane.
     * @param distance the distance from the camera to the view plane, MUST be not negative or
     *     zero
     * @return the camera itself
     */
    public Camera setVPDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public double getWidth() {return width;}

    public double getHeight() {return height;}

    /**
     * Set the width and the height of the view plane.
     * @param width the width of the view plane, MUST be not negative or zero
     * @param height the height of the view plane, MUST be not negative or zero
     * @return the camera itself
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Helper for {@link #rotation(double, int)}.
     * @param vector the vector to rotate
     * @param r an angle in radians
     * @return the result vector after the rotation
     */
    private Vector twistZ(Vector vector, double r) {
        double cos = Math.cos(r);
        double sin = Math.sin(r);
        double axisX = vector.getX() * cos - vector.getY() * sin;
        double axisY = vector.getX() * sin + vector.getY() * cos;
        return new Vector(axisX, axisY, vector.getZ());
    }

    /**
     * Rotating the camera by degree in radians.
     * @param r the degrees in radians
     * @param a to which axis to rotate
     * @return the camera itself
     */
    public Camera rotation(double r, int a) {
        if(a == 0) {
            vTo = twistX(vTo, r);
            vUp = twistX(vUp, r);
        }
        if(a == 1) {
            vTo = twistY(vTo, r);
            vUp = twistY(vUp, r);
        }
        if(a == 2) {
            vTo = twistZ(vTo, r);
            vUp = twistZ(vUp, r);
        }
        vRight = vTo.crossProduct(vUp);
        return this;
    }


    /**
     * Helper for {@link #rotation(double, int)}.
     * @param vector the vector to rotate
     * @param r an angle in radians
     * @return the result vector after the rotation
     */
    private Vector twistY(Vector vector, double r) {
        double cos = Math.cos(r);
        double sin = Math.sin(r);
        double axisX = vector.getX() * cos + vector.getZ() * sin;
        double axisZ = vector.getZ() * cos - vector.getX() * sin;
        return new Vector(axisX, vector.getY(), axisZ);
    }

    /**
     * Helper for {@link #rotation(double, int)}.
     * @param vector the vector to rotate
     * @param r an angle in radians
     * @return the result vector after the rotation
     */
    private Vector twistX(Vector vector, double r) {
        double cos = Math.cos(r);
        double sin = Math.sin(r);
        double axisY = vector.getY() * cos - vector.getZ() * sin;
        double axisZ = vector.getY() * sin + vector.getZ() * cos;
        return new Vector(vector.getX(), axisY, axisZ);
    }


    /**
     * Find all the intersection points with the plan view and geometry.
     * @param nX row count pixels
     * @param nY column count pixels
     * @param intersect the intersect-able object with the rays from the view plane
     * @return list of all the points that intersect, if there is no intersect point return null
     */
    public List<Point> findRay(int nX, int nY, Intersectable intersect) {
        Ray ray;
        List<Point> result = new LinkedList<>();
        List<Point> intersectionPoints;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ray = constructRay(nX, nY, i, j);
                intersectionPoints = intersect.findIntersections(ray);
                if (intersectionPoints != null) {
                    result.addAll(intersectionPoints);
                }
            }
        }
        return result.isEmpty() ? null : result;
    }

    /**
     * Shift the camera to the direction of the vector with the distance.
     * @param vector direction with distance
     * @return the camera itself
     */
    public Camera shift(Vector vector) {
        p0 = p0.add(vector);
        return this;
    }

    /**
     * Find a ray from p0 to the center of the pixel from the given resolution.
     * @param nX the number of the rows
     * @param nY the number of the columns
     * @param j spacipic  column
     * @param i row
     * @return ray from p0 the center to the center of the pixel in row i column j
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Vector dir;
        Point pCenter, pCenterPixel;
        double ratioY, ratioX, yI, xJ;

        pCenter = p0.add(vTo.scale(distance)); //give us the center of the pixel
        ratioY = alignZero(height / nY);
        ratioX = alignZero(width / nX);

        pCenterPixel = pCenter;
        yI = alignZero(-1 * (i - (nY - 1) / 2d) * ratioY);
        xJ = alignZero((j - (nX - 1) / 2d) * ratioX);
        if (!isZero(xJ)) {
            pCenterPixel = pCenterPixel.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pCenterPixel = pCenterPixel.add(vUp.scale(yI));
        }
        dir = pCenterPixel.subtract(p0);

        return new Ray(dir,p0);
    }


}
