package primitives;

public class Ray {
    private final Point p0;
    private final  Vector dir;

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    public Ray(Point p, Vector v)
    {
        p0 = new Point(p.point.d1, p.point.d2, p.point.d3);
        Vector v3 = v.normalize();
        dir = new Vector(v3.point.d1,v3.point.d2,v3.point.d3);
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
