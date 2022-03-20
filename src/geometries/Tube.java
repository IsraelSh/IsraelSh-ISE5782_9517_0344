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
        //get ray point and vector
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        //get point on the same level as the given point
        double t = v.dotProduct(p.subtract(p0));//t=v(p-p0), t is the projection of the vector (p-p0) on the ray v

        //if the point is not on the same level then get the point
        //and return the normal
        if(!Util.isZero(t))
        {
            Point o = p0.add(v.scale(t));//o=p0+t*v
            return p.subtract(o).normalize();
        }

        //if the point is on the same level then return normal
        return p.subtract(p0).normalize();
    }
    @Override
    public String toString() {
        return "Tube [axisRay=" + axisRay + ", radius=" + radius + "]";
    }
}
