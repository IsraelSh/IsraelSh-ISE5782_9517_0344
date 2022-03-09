package primitives;
import static primitives.Util.isZero;

public class Vector extends Point {
    public Vector(double x, double y ,double z) {
        super(x,y,z);
        if(this.equals(this.point.ZERO)) throw new IllegalArgumentException("Can't create the zero vector");
    }
    public Vector(Double3 d3) {
        super(d3);
    }
    @Override
    public String toString()
    {
        return super.toString();
    }
    public Vector add(Vector v)
    {
        return this.add(v);
    }
    public Vector scale(double scl) {
        Vector v = new Vector(this.point.d1*scl,this.point.d2*scl,this.point.d3*scl);
        return v;
    }
    public Vector crossProduct(Vector v)
    {
        double x = this.point.d2*v.point.d3 - this.point.d3*v.point.d2;
        double y = this.point.d1*v.point.d3-this.point.d3*v.point.d1;
        double z = this.point.d1*v.point.d2-this.point.d2*v.point.d1;
        Vector newvector = new Vector(x,-y,z);
        return newvector;
    }
    public double lengthSquared()
    {
        double squared = this.point.d1*this.point.d1+this.point.d2*this.point.d2+this.point.d3*this.point.d3;
        return squared;
    }
    public double length()
    {
        double squared = lengthSquared();
        return Math.sqrt(squared);
    }
    public Vector normalize()
    {
        double dis = length();
        Vector newvector = new Vector(this.point.d1/dis,this.point.d2/dis,this.point.d3/dis);
        return newvector;
    }
    public double dotProduct(Vector v)
    {
        return this.point.d1 * v.point.d1 + this.point.d2 * v.point.d2 + this.point.d3 * v.point.d3;
    }
}
