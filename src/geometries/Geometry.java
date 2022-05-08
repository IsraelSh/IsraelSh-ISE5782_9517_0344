package geometries;

import primitives.*;

/**
 * Interface for general geometric obj.
 */
public abstract class Geometry extends Intersectable {

     protected Color emission = Color.BLACK;
     private Material material = new Material();
     /**
      * getEmission
      * @return emission
      */
     public Color getEmission(){
          return emission;
     }

     /**
      * setEmission
      * @return emission
      */
     public Geometry setEmission(Color emission){
          this.emission = emission;
          return this;
     }
     /**
      * getMaterial
      * @return emission
      */
     public Material getMaterial(){
          return material;
     }

     /**
      * setEMaterial
      * @return emission
      */
     public Geometry setMaterial(Material material){
          this.material = material;
          return this;
     }


     /**
      * abstract func who return the normal vector of obj.
      * @param p point p
      * @return the normal vector of obj.
      */
     public abstract Vector getNormal(Point p);
}