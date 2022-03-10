package primitives;
import static primitives.Util.isZero;

/**
 * This class extends Point class and will handle all functions related to the Vector
 * @author sendi pardes Israel Shlomo
 *
 */

public class Vector extends Point {
    /**
     * constructor that receiving the values of the three coordinates from Double
     * @param x
     * @param y
     * @param y
     */
    public Vector(double x, double y ,double z) {
        super(x,y,z);
        if(Double3.ZERO.equals(new Double3(x,y,z))) throw new IllegalArgumentException("Can't create the zero vector");
    }
    /**
     * constructors that receiving Object from Double3 type
     * @param d3
     */
    public Vector(Double3 d3) {
        super(d3);
        if(Double3.ZERO.equals(d3)) throw new IllegalArgumentException("Can't create the zero vector");

    }
    @Override
    public String toString() {return "Vector = " + super.toString();}

    /**
     * performs addition between two vectors.
     * @param v
     * @return a new vector as the sum of two vectors
     */
    public Vector add(Vector v)
    {
        return this.add(v);
    }

    public Vector scale(double scl) {
        Vector v = new Vector(this.spoint.d1*scl,this.spoint.d2*scl,this.spoint.d3*scl);
        return v;
    }
    /**
     * Vector product
     * @param v
     * @return Vector product between this to other
     */
    public Vector crossProduct(Vector v)
    {
        double x = this.spoint.d2*v.spoint.d3 - this.spoint.d3*v.spoint.d2;
        double y = this.spoint.d1*v.spoint.d3-this.spoint.d3*v.spoint.d1;
        double z = this.spoint.d1*v.spoint.d2-this.spoint.d2*v.spoint.d1;
        Vector newvector = new Vector(x,-y,z);
        return newvector;
    }

    /**
     * Calculate the length of the vector squared
     * @return length of the vector squared
     */
    public double lengthSquared()
    {
        double squared = this.spoint.d1*this.spoint.d1+this.spoint.d2*this.spoint.d2+this.spoint.d3*this.spoint.d3;
        return squared;
    }
    /**
     * Calculate the length of the vector
     * @return length of the vector
     */
    public double length()
    {
        double squared = lengthSquared();
        return Math.sqrt(squared);
    }
    /**
     * normalize this vector
     * @return a new vector which is the original vector, normalized
     */

    public Vector normalize()
    {
        double dis = length();
        Vector newvector = new Vector(this.spoint.d1/dis,this.spoint.d2/dis,this.spoint.d3/dis);
        return newvector;
    }
    /**
     * Scalar product
     * @param v
     * @return Scalar product between this to other
     */
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
