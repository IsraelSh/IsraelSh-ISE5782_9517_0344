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
     *
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
}