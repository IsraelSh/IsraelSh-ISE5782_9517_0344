package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    private List<Intersectable> geometriesBodies;

    /* Empty ctor
     */
    public Geometries() {
        geometriesBodies = new LinkedList<>();
    }

    /* Ctor with list of geometries
     * @param geometries
     */
    public Geometries(Intersectable... geometries)
    {
        geometriesBodies = List.of(geometries);
    }

    /**
     * Adds list of geometries to the current list
     * .
     * @param geometries
     */
    public void add(Intersectable... geometries)
    {
        geometriesBodies.addAll(List.of(geometries));
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        if (geometriesBodies.isEmpty()) // In case the collection is empty
            return null;

        List<GeoPoint> points = null, result;
        for (Intersectable body: geometriesBodies) {
            result = body.findGeoIntersectionsHelper(ray);
            if(result != null){
                if(points == null)
                    points = new LinkedList<GeoPoint>(result);
                else
                    points.addAll(result);
            }
        }
        return points;
    }
}