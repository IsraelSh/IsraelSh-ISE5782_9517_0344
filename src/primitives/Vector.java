package primitives;

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
    public Vector(double x, double y , double z) {
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
    //infinite loop!
          return new Vector(super.xyz.add(v.xyz));
    }

    public Vector scale(double scl) {

        return  new Vector(this.xyz.scale(scl));
    }
    /**
     * Vector product
     * @param v
     * @return Vector product between this to other
     */
    public Vector crossProduct(Vector v)

    {
        double x = this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2;
        double y = -(this.xyz.d1*v.xyz.d3-this.xyz.d3*v.xyz.d1);
        double z = this.xyz.d1*v.xyz.d2-this.xyz.d2*v.xyz.d1;
        Vector newv = new Vector(x,y,z);
        return newv;
    }
    /**
     * Calculate the length of the vector squared
     * @return length of the vector squared
     */
    public double lengthSquared(){
        return super.distanceSquared(new Point(0,0,0));
    }
    /**
     * Calculate the length of the vector
     * @return length of the vector
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    /**
     * normalize this vector
     * @return a new vector which is the original vector, normalized
     */

    public Vector normalize()
    {
        /// fix
        double dis = length();
        Vector newvector = new Vector(this.xyz.d1/dis,this.xyz.d2/dis,this.xyz.d3/dis);
        return newvector;
    }
    /**
     * Scalar product
     * @param v
     * @return Scalar product between this to other
     */
    public double dotProduct(Vector v){
        Double3 temp =  this.xyz.product(v.xyz);
        return temp.d1 + temp.d2 + temp.d3;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return this.xyz.equals(other.xyz) && this.xyz.equals(other.xyz);
    }
    public Vector subtract (Vector v) {return new Vector(super.xyz.subtract(v.xyz));
    }

}
