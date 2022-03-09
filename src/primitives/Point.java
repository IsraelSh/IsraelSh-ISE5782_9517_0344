package primitives;
import static primitives.Util.isZero;


public class Point {
    public final Double3 point;
    public Double3 getPoint()
    {
        return point;
    }
    public Point(double x,double y,double z) {point =new Double3(x,y,z);}
    public Point(Double3 d)
    {
        point=new Double3(d.d1,d.d2,d.d3);
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return (this.point== other.point);
    }
    @Override
    public String toString() {
        return String.format("Point: "+ point.toString());
    }
    public 	Point add(Point p2)
    {
        double X = p2.point.d1 + this.point.d1;
        double Y = p2.point.d2 + this.point.d2;
        double Z = p2.point.d3 + this.point.d3;
        Point newpoint = new Point(X,Y,Z);
        return newpoint;
    }
    public Point subtract (Point p2)
    {
        double X = p2.point.d1-this.point.d1;
        double Y = p2.point.d2-this.point.d2;
        double Z = p2.point.d3-this.point.d3;
        Point newpoint = new Point(X,Y,Z);
        return newpoint;
    }






}
