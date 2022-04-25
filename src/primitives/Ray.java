package primitives;

import static primitives.Util.isZero;
import java.util.List;

/**
 * Class Ray is the basic class representing a Ray of Euclidean primitives in Cartesian
 * Object is founded in geometry
 *  @author sendi pardes Israel Shlomo
 *
 */

public class Ray {
    /**
     * constructor that receives a vector and a point and creates a ray
     * @param dir
     * @param p0
     */
     final Point p0;
     final Vector dir;
    public Point getP0() {
        return this.p0;
    }

    public Vector getDir() {
        return this.dir;
    }

    public Ray(Vector v, Point p) {
        super();
        p0 = p;
        dir = v.normalize();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return this.p0.equals(other.p0) && this.dir.equals(other.dir) ;
    }
    @Override
    public String toString()
    {
        return dir.toString()+" "+p0.toString();
    }

    /**
     *get Point at specific distance in the ray's direction
     *
     * @param t is a distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        if(isZero(t)){
            throw new IllegalArgumentException("t is equal to 0 produce an illegal ZERO vector");
        }
        return p0.add(dir.scale(t));
    }
    /**
     *
     * @param listPoint
     * @return The closest point to the began of the ray
     */
    public Point findClosestPoint(List<Point> listPoint) {

        if (listPoint == null) //In case of an empty list
            return null;
        Point closePoint = listPoint.get(0);	//Save the first point in the list
        for (Point p : listPoint) {
            if (closePoint.distance(p0) > p.distance(p0))	//In case the distance of closes point is bigger than the p point
                closePoint = p;
        }
        return closePoint;
    }


}
