package primitives;
import static primitives.Util.isZero;
import primitives.Vector;
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
     final  Vector dir;



    public Point getP0() {
        return this.p0;
    }

    public Vector getDir() {
        return this.dir;
    }

    public Ray(Point p, Vector v)
    {
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


}
