package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Triangle
 * @author sendi pardes Israel Shlomo
 *
 */
public class Triangle extends Polygon {
    /**
     * constructor that receive three points and activate the super constructor to create the triangle
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
