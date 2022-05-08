package lighting;

import primitives.Color;
import primitives.*;

/* class PointLight
 */
public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC = 1, kL = 0, kQ = 0;

    /*
            * @param intensity
     * @param position
     * @param c
     * @param l
     * @param q
     */
    public PointLight(Color intensity, Point position, double c, double l, double q) {
        super(intensity);
        this.position = position;
        kC = c;
        kL = l;
        kQ = q;
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

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        Color iL = getIntensity().scale((1 / (kC + kL * d + kQ * d * d)));
        return iL;
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}