package primitives;
import static primitives.Util.isZero;

public class Vector extends Point {
    public Vector(double x, double y ,double z) {
        super(x,y,z);
        if(Double3.ZERO.equals(new Double3(x,y,z))) throw new IllegalArgumentException("Can't create the zero vector");
    }
    public Vector(Double3 d3) {
        super(d3);
        if(Double3.ZERO.equals(d3)) throw new IllegalArgumentException("Can't create the zero vector");

    }
    @Override
    public String toString() {return "Vector = " + super.toString();}

    public Vector add(Vector v)
    {
        return this.add(v);
    }

    public Vector scale(double scl) {
        Vector v = new Vector(this.spoint.d1*scl,this.spoint.d2*scl,this.spoint.d3*scl);
        return v;
    }
    public Vector crossProduct(Vector v)
    {
        double x = this.spoint.d2*v.spoint.d3 - this.spoint.d3*v.spoint.d2;
        double y = this.spoint.d1*v.spoint.d3-this.spoint.d3*v.spoint.d1;
        double z = this.spoint.d1*v.spoint.d2-this.spoint.d2*v.spoint.d1;
        Vector newvector = new Vector(x,-y,z);
        return newvector;
    }
    public double lengthSquared()
    {
        double squared = this.spoint.d1*this.spoint.d1+this.spoint.d2*this.spoint.d2+this.spoint.d3*this.spoint.d3;
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
        Vector newvector = new Vector(this.spoint.d1/dis,this.spoint.d2/dis,this.spoint.d3/dis);
        return newvector;
    }
    public double dotProduct(Vector v)
    {
        return this.spoint.d1 * v.spoint.d1 + this.spoint.d2 * v.spoint.d2 + this.spoint.d3 * v.spoint.d3;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Vector subtract (Vector v){
        return this.subtract(v);
    }
}
