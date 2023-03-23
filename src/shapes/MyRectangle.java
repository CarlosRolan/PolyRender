package shapes;

import java.awt.Color;

public final class MyRectangle extends MyPolyhedro {

	private MyPolygon back; // blue
	private MyPolygon bottom;// white
	private MyPolygon left;// yellow
	private MyPolygon right;// green
	private MyPolygon front;// orange
	private MyPolygon top;// red

	public MyRectangle(double width, double height, double depth) {
		init(width, height, depth);
	}

	private void init(double width, double height, double depth) {
		// CUBE-Vertices
		final MyPoint P0 = new MyPoint(width, -height, -depth);
		final MyPoint P1 = new MyPoint(width, height, -depth);
		final MyPoint P2 = new MyPoint(width, height, depth);
		final MyPoint P3 = new MyPoint(width, -height, depth);
		final MyPoint P4 = new MyPoint(-width, -height, -depth);
		final MyPoint P5 = new MyPoint(-width, height, -depth);
		final MyPoint P6 = new MyPoint(-width, height, depth);
		final MyPoint P7 = new MyPoint(-width, -height, depth);

		// Back face
		this.back = new MyPolygon(Color.RED, P4, P5, P6, P7);
		// Bottom face
		this.bottom = new MyPolygon(Color.BLUE, P0, P1, P5, P4);
		// Left face
		this.left = new MyPolygon(Color.GREEN, P0, P4, P7, P3);
		// Right face
		this.right = new MyPolygon(Color.GREEN, P1, P5, P6, P2);
		// Top face
		this.top = new MyPolygon(Color.BLUE, P3, P2, P6, P7);
		// Front Face
		this.front = new MyPolygon(Color.RED, P0, P1, P2, P3);

		MyPolygon[] polygons = {back, bottom, left, right, top, front};

		this.polygons = polygons;
	}

}
