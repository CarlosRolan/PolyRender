package shapes;

import java.awt.Color;

public class MyCube extends MyPolyhedro {

	private MyPolygon back; // blue
	private MyPolygon bottom;// white
	private MyPolygon left;// yellow
	private MyPolygon right;// green
	private MyPolygon front;// orange
	private MyPolygon top;// red

	public MyCube(double l) {
		init(l);
	}

	private void init(double l) {
		// CUBE-Vertices
		final MyPoint P0 = new MyPoint(l, -l, -l);
		final MyPoint P1 = new MyPoint(l, l, -l);
		final MyPoint P2 = new MyPoint(l, l, l);
		final MyPoint P3 = new MyPoint(l, -l, l);
		final MyPoint P4 = new MyPoint(-l, -l, -l);
		final MyPoint P5 = new MyPoint(-l, l, -l);
		final MyPoint P6 = new MyPoint(-l, l, l);
		final MyPoint P7 = new MyPoint(-l, -l, l);

		// Back face
		this.back = new MyPolygon(Color.BLUE, P4, P5, P6, P7);
		// Bottom face
		this.bottom = new MyPolygon(Color.WHITE, P0, P1, P5, P4);
		// Left face
		this.left = new MyPolygon(Color.YELLOW, P0, P4, P7, P3);
		// Right face
		this.right = new MyPolygon(Color.GREEN, P1, P5, P6, P2);
		// Top face
		this.top = new MyPolygon(Color.ORANGE, P3, P2, P6, P7);
		// Front Face
		this.front = new MyPolygon(Color.RED, P0, P1, P2, P3);

		MyPolygon[] polygons = {this.back, this.bottom, this.left, this.right,
				this.top, this.front};

		this.polygons = polygons;
	}

	public void translate(double l) {

	}

}