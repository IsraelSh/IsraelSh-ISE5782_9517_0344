import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import Scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene Scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		Scene.geometries.add( //
				new Sphare(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
				new Sphare(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
		Scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKL(0.0004).setKQ(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(Scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		Scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		Scene.geometries.add( //
				new Sphare(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20).setKT(0.5)),
				new Sphare(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKR(1)),
				new Cylinder(400,new Ray( new Vector(0, 0, -1),new Point(-950, -900, -1000)),  20)
						.setEmission(new Color(20, 30, 100))
						.setMaterial(new Material().setDiffuse(0.25).setSpecular(0.25).setNShininess(20).setKT(0.5)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKR(0.5)));



		Scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(Scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		Scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

		Scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
				new Sphare(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setKT(0.6)));

		Scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(Scene)) //
				.renderImage() //
				.writeToImage();
	}


	@Test
	public void newOne() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		Scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		Scene.geometries.add( //
				new Sphare( new Point(-950, -900, -1000),400) //
						.setEmission(new Color(32, 17, 240)) //
						.setMaterial(new Material().setDiffuse(0.25).setSpecular(0.25).setNShininess(20).setKT(0.5)),
				new Sphare( new Point(-950, -900, -1000),200) //
						.setEmission(new Color(228, 18, 193)) //
						.setMaterial(new Material().setDiffuse(0.25).setSpecular(0.25).setNShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKR(1)),
				new Cylinder(400,new Ray( new Vector(0, 0, -1),new Point(-950, -900, -1000)),  20)
						.setEmission(new Color(20, 30, 100))
						.setMaterial(new Material().setDiffuse(0.25).setSpecular(0.25).setNShininess(20).setKT(0.5)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKR(0.5)));



		Scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("newOne", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(Scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 *
	 * produces a picture we created, looks like bubble-gum
	 */
	@Test
	public void ourPicture() {
		Camera camera = new Camera(new Point(0, 0,0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
				.setVPSize(300, 300).setVPDistance(400);

		Scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		Scene.geometries.add( //
				new Sphare(new Point(0,50,-800),120) //the turquise sphere
						.setEmission(new Color(0,100,100)) //
						.setMaterial(new Material().setKD(0.6).setKS(0.9).setNShininess(1000).setKT(0).setKR(1)),
				new Sphare(new Point(-210,110,-900),300) //the large purple sphere
						.setEmission(new Color(30,0,50)) //
						.setMaterial(new Material().setKD(0).setKS(0.2).setNShininess(1000).setKT(0.6).setKR(0.2)),
				new Sphare(new Point(300,50,-200),80) //the blue sphere (not seen boldly)
						.setEmission(new Color(0,0,100)) //
						.setMaterial(new Material().setKD(0.6).setKS(0.9).setNShininess(1000).setKT(0).setKR(0.2)),
				new Sphare(new Point(10, 30, -100),10) //the red sphere
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20).setKT(0).setKR(0.8)),
				new Sphare(new Point(-80, 80, -400),20) //the magenta sphere
						.setEmission(new Color(java.awt.Color.MAGENTA)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20).setKT(0.7).setKR(0)),
				new Sphare(new Point(-100, 60, -300),30) //the yellow sphere
						.setEmission(new Color(java.awt.Color.YELLOW)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20).setKT(0.7).setKR(0)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),//the triangle
						new Point(-1500, -1500, -2000)).setEmission(new Color(java.awt.Color.BLACK))
						.setMaterial(new Material().setKD(0.25).setKS(0.9).setNShininess(20).setKT(0).setKR(0.8)));


		Scene.lights.add(new SpotLight(new Color(200,200,200), new Point(1000,-550,1000), new Vector(0,-1,0)) //
				.setKL(4E-5).setKQ(2E-7));
		Scene.lights.add(new PointLight(new Color(300, 500, 500), new Point(-50, -50, 50))//
				.setKL(0.00001).setKQ(0.000001));

		ImageWriter imageWriter = new ImageWriter("ourPicture", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(Scene)) //
				.renderImage() //
				.writeToImage();
	}
}
