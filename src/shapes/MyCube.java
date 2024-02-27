package shapes;

import java.awt.Color;

public class MyCube extends MyPolyhedro {

	private Poly3D back; // blue
	private Poly3D bottom;// white
	private Poly3D left;// yellow
	private Poly3D right;// green
	private Poly3D front;// orange
	private Poly3D top;// red

	public MyCube(double l) {
		init(l);
	}

	private void init(double l) {
		// CUBE-Vertices
		final Point3D P0 = new Point3D(l, -l, -l);
		final Point3D P1 = new Point3D(l, l, -l);
		final Point3D P2 = new Point3D(l, l, l);
		final Point3D P3 = new Point3D(l, -l, l);
		final Point3D P4 = new Point3D(-l, -l, -l);
		final Point3D P5 = new Point3D(-l, l, -l);
		final Point3D P6 = new Point3D(-l, l, l);
		final Point3D P7 = new Point3D(-l, -l, l);

		// Back face
		this.back = new Poly3D(Color.BLUE, P4, P5, P6, P7);
		// Bottom face
		this.bottom = new Poly3D(Color.WHITE, P0, P1, P5, P4);
		// Left face
		this.left = new Poly3D(Color.YELLOW, P0, P4, P7, P3);
		// Right face
		this.right = new Poly3D(Color.GREEN, P1, P5, P6, P2);
		// Top face
		this.top = new Poly3D(Color.ORANGE, P3, P2, P6, P7);
		// Front Face
		this.front = new Poly3D(Color.RED, P0, P1, P2, P3);

		Poly3D[] polygons = {this.back, this.bottom, this.left, this.right,
				this.top, this.front};

		this.mPolygons = polygons;
	}

	public void translate(double l) {

	}

}