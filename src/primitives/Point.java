package primitives;
import static primitives.Util.isZero;


public class Point {
    public final Double3 spoint;
    public Double3 getSpoint()
    {
        return spoint;
    }
    public Point(double x,double y,double z) {
        spoint =new Double3(x,y,z);}
    public Point(Double3 d){spoint =d;}

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return (this.spoint == other.spoint);
    }
    @Override
    public String toString() {
        return String.format("Point: "+ spoint.toString());
    }
    public 	Point add(Point p2)
    {
        double X = p2.spoint.d1 + this.spoint.d1;
        double Y = p2.spoint.d2 + this.spoint.d2;
        double Z = p2.spoint.d3 + this.spoint.d3;
        Point newpoint = new Point(X,Y,Z);
        return newpoint;
    }
    public Point subtract (Point p2)
    {
        double X = p2.spoint.d1-this.spoint.d1;
        double Y = p2.spoint.d2-this.spoint.d2;
        double Z = p2.spoint.d3-this.spoint.d3;
        Point newpoint = new Point(X,Y,Z);
        return newpoint;
    }






}
