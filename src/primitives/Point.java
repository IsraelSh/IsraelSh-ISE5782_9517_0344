package primitives;

/**
 * Class Point
 * 3-Dimensional coordinate system.
 * @author Sendi pardes Israel Shlomo
 */


public class Point {
    /**
     * constructor that receiving Object from Double3 type
     * @param spoint
     */
     final Double3 xyz;

    public Double3 getXyz()
    {
        return xyz;
    }
    /**
     * constructor that receiving the values of the three coordinates from Double
     * @param x
     * @param y
     * @param z
     */
    public Point(double x,double y,double z) {
        xyz =new Double3(x,y,z);}

    public Point(Double3 d){
        xyz = d;}

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return (this.xyz.equals(other.xyz));
    }
    @Override
    public String toString() {
        return String.format("Point: "+ xyz.toString());
    }
    /**
     * Sum two floating point triads into a new triad where each couple of numbers
     * is summarized
     *
     * @param  p2  right handle side operand for addition
     * @return result of add
     */
    public Point add(Point p2)
    {
       return new Point(this.xyz.add(p2.xyz));
    }
    /**
     * Subtract two floating point triads into a new triad where each couple of
     * numbers is subtracted
     *
     * @param p2 right handle side operand for addition
     * @return result of add
     */
    public Vector subtract(Point p2){
        return new Vector(this.xyz.subtract(p2.xyz));
    }


    public double getX() {
        return xyz.d1;
    }
    public double getY() {
        return xyz.d2;
    }
    public double getZ() {return xyz.d3;
    }
}
