package engine.basic;

import java.awt.Point;

public class Point3D {

	public double x, y, z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point get2D() {
		return new Point((int) x, (int) y);
	}

	public String toString() {
		return "P[" + String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(z) + "]";
	}

}
