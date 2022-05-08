package geometries;
import primitives.Ray;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * Cylinder
 * @author sendi pardes Israel Shlomo
 *
 */

public class Cylinder extends Tube {
    private double hight;

    public Cylinder (double radius,Ray ray,double hight) {
        super(ray,radius);
        this.hight = hight;}

    @Override
    public Vector getNormal(Point p){
        return null;
    }
    @Override
    public String toString() {
        return "Cylinder [hight=" + hight + "]";
    }

}
