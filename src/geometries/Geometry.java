package geometries;
import primitives.Point;
import primitives.Vector;

/**
 *return the normal of all the geometric shapes realized from it
 * @author Sendi Pardes Israel Shlomo
 *
 */
public interface    Geometry extends Intersectable  {
     Vector getNormal(Point p);
}
