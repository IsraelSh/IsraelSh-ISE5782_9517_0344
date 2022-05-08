package geometries;

import primitives.*;

import java.util.List;

/**
 * interface Intersectable
 *
 * @author
 *
 */
public abstract class Intersectable {

    /**
     * Finds intersections of a ray with geometric object and returns them as list of points
     * @param ray
     *
     * @return List<Point> - list of intersections in geometric object
     */
    public List<Point> findIntsersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * static class GeoPoint
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * ctor of static class GeoPoint
         */
        public GeoPoint(Geometry geometry, Point point){
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof GeoPoint))
                return false;
            GeoPoint other = (GeoPoint) obj;
            return other.point.equals(this.point) &&
                    other.geometry == this.geometry; // Checking geometry by reference
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }

    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}