package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
//we do it because we want to do Compsite template
public class Geometries implements Intersectable {


    List<Intersectable> _intersectables;

    public Geometries() {
        _intersectables = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... intersectables) {
        _intersectables = new LinkedList<Intersectable>();
        Collections.addAll(_intersectables, intersectables);
    }

    public void add(Intersectable... intersectables) {
        Collections.addAll(_intersectables, intersectables);
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        ArrayList<Point> points = null;
        for (var geometry : _intersectables) {
            var geometryList = geometry.findIntersections(ray);
            if (geometryList != null) {
                if (points == null) {
                    points = new ArrayList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}