package geometries;
import primitives.Vector;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
/**
 * Tube
 * @author sendi pardes Israel Shlomo
 *
 */
public class Tube implements Geometry{
    Ray axisRay;
    Double radius;
    public Tube(Ray axisRay, double radius) {
        super();
        if(Util.isZero(radius) || radius < 0)
            throw new IllegalArgumentException("Zero or negative radius");
        this.axisRay = axisRay;
        this.radius = radius;
    }
    public Ray getAxisRay() {
        return axisRay;
    }
    public double getRadius() {
        return radius;
    }
    @Override
    public Vector getNormal(Point p){
        return null;
    }
    @Override
    public String toString() {
        return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
    }
}
