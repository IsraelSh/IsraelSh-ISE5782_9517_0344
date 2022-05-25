package lighting;

import primitives.*;

/* interface LightSource
 */
public interface LightSource {

    /* getIntensity
     * @param p
     * @return
             */
    public Color getIntensity(Point p);

    /**
     * getL
     * @param p
     * @return
     */
    public Vector getL(Point p);

    public double getDistance(Point point);


}