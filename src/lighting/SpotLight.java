package lighting;

import primitives.Color;
import primitives.*;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight{
    private Vector direction;

    /**
     *
     * @param intensity
     * @param position
     * @param dir
     * @param c
     * @param l
     * @param q
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        Vector l = super.getL(p);

        if (alignZero(direction.dotProduct(l)) <= 0) //In case the dir * l return zero or negative number
            return Color.BLACK;

        return super.getIntensity(p).scale(direction.dotProduct(l));
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
    /**
     * Sets the size of the array of points
     * If the given number is a positive number, this will activate SoftShadowing
     *
     * @param size Size of the array
     * @return the Point light with array of point
     */
    public SpotLight setSize(double size) {
        super.setSize(size);
        this.initializePoints();
        return this;
    }

    /**
     * Initialize the array of points that will cast shadow rays
     */
    private void initializePoints() {
        //Send the location of the light+ the direction vector of the light
        super.initializePoints(this.position.add(direction));
    }
}