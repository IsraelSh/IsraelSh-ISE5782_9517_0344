package lighting;

import primitives.Color;
import primitives.*;
import primitives.Point;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/* class PointLight
 */
public class PointLight extends Light implements LightSource{
    protected Point position;
    protected double radius;
    private double kC = 1, kL = 0, kQ = 0;
    private Vector kk = new Vector(1,0,0);
    /**
     * The array of points that will cast shadow rays
     */
    private Point[] points;
    /**
     * The size of the light
     */
    private double size = 0;
    /**
     * The max number of points that are in the array
     */
    private final int NUMBER_OF_POINTS = 1000;
    /**
     * A random number
     */
    private final Random rand = new Random();
    /*
     * @param intensity
     * @param position
     * @param c
     * @param l
     * @param q
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
       // kk=KK;
        //kC = c;,Vector KK
       // kL = l;
        //kQ = q;
    }
    public PointLight(Color c, Point pos, double radius) {
        super(c);
        position = pos;
        this.radius = radius;
    }
    /* setKC
     * @param kC
     * @return
             */
    public PointLight setKC(double kC){
        this.kC = kC;
        return this;
    }

    /* setKL
     * @param kL
     * @return
             */
    public PointLight setKL(double kL){
        this.kL = kL;
        return this;
    }

    /**
     * setKQ
     * @param kQ
     * @return
     */
    public PointLight setKQ(double kQ){
        this.kQ = kQ;
        return this;
    }
    public PointLight setradius(double radius) {
        this.radius = radius;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        Color iL = getIntensity().scale((1 / (kC + kL * d + kQ * d * d)));
        return iL;
    }
    /**
     * The light vector is the vector from the light to the point.
     *
     * @param p The point on the surface of the object that we're calculating the normal for.
     * @return The vector from the light source to the point on the surface.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
    /**
     * get distance of light from given point
     * @param point point
     * @return double
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
    public List<Vector> getListL(Point p, int numOfRays) {
        Random r = new Random();
        double numOfRaysForItareation=Math.sqrt(numOfRays);
        List<Vector> vectors = new LinkedList();
        for (double i = -radius; i < radius; i += radius / numOfRaysForItareation) {
            for (double j = -radius; j < radius; j += radius / numOfRaysForItareation) {
                if (i != 0 && j != 0) {
                    Point point = position.add(new Vector(i, 0.1d, j));
                    if (point.equals(position)){
                        vectors.add(p.subtract(point).normalize());
                    }
                    else{
                        try{
                            if (point.subtract(position).dotProduct(point.subtract(position)) <= radius * radius){
                                vectors.add(p.subtract(point).normalize());
                            }
                        }
                        catch (Exception e){
                            vectors.add(p.subtract(point).normalize());
                        }

                    }
                }

            }
        }
        vectors.add(getL(p));
        return vectors;
    }
    /**
     * Sets the size of the array of points
     * If the given number is a positive number, this will activate SoftShadowing
     *
     * @param size Size of the array
     * @return the Point light with array of point
     */
    public PointLight setSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * Initialize the array of points that will cast shadow rays
     *
     * @param p The point in the middle of the grid of rays
     */
    public void initializePoints(Point p) {
        if (size == 0)// if the size is zero, Soft shadows is not activated
            return;
        points = new Point[NUMBER_OF_POINTS];// Create a new array of points
        Vector n = p.subtract(position);
        Vector vX = n.getOrthogonal().normalize();
        Vector vY = vX.crossProduct(n).normalize();
        double x, y, radius;
        for (int i = 0; i < NUMBER_OF_POINTS; i += 4) {
            radius = rand.nextDouble(size);
            x = rand.nextDouble(radius);
            y = getCircleScale(x, radius);
            for (int j = 0; j < 4; j++) {
                //in this part we mirror the point we got 4 times, to each quarter of the grid
                points[i + j] = position.add(vX.scale(j % 2 == 0 ? x : -x)).add(vY.scale((j <= 1 ? -y : y)));
            }
        }
    }

    /**
     * Return the length of b in the pythagorean theorem (a^2 + b^2 = c^2)
     *
     * @param a a in pythagorean theorem
     * @param c in pythagorean theorem
     * @return b in pythagorean theorem
     */
    private double getCircleScale(double a, double c) {
        return Math.sqrt(c * c - a * a);
    }

    /**
     * Get the array of points that will cast shadow rays
     * NOTE: initializePoints function must be used first,
     * Otherwise this will return null
     *
     * @return The array of point
     */
    public Point[] getPoints() {
        return this.points;
    }

    /**
     * Get the number of points that will cast shadow rays
     *
     * @return The amount of Points
     */
    public int getNUMBER_OF_POINTS() {
        return this.NUMBER_OF_POINTS;
    }
}