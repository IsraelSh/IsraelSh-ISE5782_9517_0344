package geometries;
import primitives.Point;

public class Triangle extends  Polygon implements Geometry{

    public Triangle(Point... vertices){
        super(vertices);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
