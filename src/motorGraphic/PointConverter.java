package motorGraphic;

import java.awt.Point;

import shapes.Point3D;

public final class PointConverter implements Parameters {

	// The Z value is no the Y valua of the 3D pint and the Y value is now the X
	// value from ABSTRACT 3D to visual 2D
	public static Point convertPoint(Point3D p3d) {
		double x3d = p3d.x;
		double y3d = p3d.y;
		double z3d = p3d.z;

		double[] newVal = proyect(x3d, y3d, z3d);

		// This is the RESULT of the 3D Point
		int x2d = (int) (newVal[0]);
		// Remember that is inverted
		int y2d = (int) (newVal[1]);
		// IMPORTANT para sacar por canvas adecuadamente tengo que invertir el eje Y
		return new Point(x2d, -y2d);
	}

	// EL
	// MOTOR===========================================================================
	private static double[] proyect(double width, double height, double depth) {
		// Distance between Point A and Point B ==
		double dist = Math.sqrt(width * width + height * height);
		// Angle b
		double theta = Math.atan2(height, width);
		// New calculated
		double depth2 = distantFromCanvas - depth;

		// double localScale = Math.abs(FOV / (depth2 + FOV));

		// MAGICCCC perspective
		// dist *= localScale;

		double[] newVal = new double[2];

		newVal[0] = dist * Math.cos(theta);
		newVal[1] = dist * Math.sin(theta);

		return newVal;
	}

	// [START]
	// =====================TRANSFORMATIONS===================================================
	// Rotation
	public static void rotateAxisX(Point3D p, double degrees, double speed) {
		double radius = Math.sqrt(p.y * p.y + p.z * p.z);
		double theta = Math.atan2(p.z, p.y);
		theta += speed * Math.PI / 360 * degrees;
		p.y = radius * Math.cos(theta);
		p.z = radius * Math.sin(theta);
	}

	public static void rotateAxisY(Point3D p, double degrees, double speed) {
		double radius = Math.sqrt(p.x * p.x + p.z * p.z);
		double theta = Math.atan2(p.x, p.z);
		theta += speed * Math.PI / 360 * degrees;
		p.x = radius * Math.sin(theta);
		p.z = radius * Math.cos(theta);
	}

	public static void rotateAxisZ(Point3D p, double degrees, double speed) {
		double radius = Math.sqrt(p.y * p.y + p.x * p.x);
		double theta = Math.atan2(p.y, p.x);
		theta += speed * Math.PI / 360 * degrees;
		p.y = radius * Math.sin(theta);
		p.x = radius * Math.cos(theta);
	}

	// Scale
	public static void scaleAxisX(Point3D p, double scaleFactor) {
		p.x *= scaleFactor;
	}

	public static void scaleAxisY(Point3D p, double scaleFactor) {
		p.y *= scaleFactor;
	}

	public static void scaleAxisZ(Point3D p, double scaleFactor) {
		p.z *= scaleFactor;
	}

	// Translation
	public static void translateAxisX(Point3D p, int dist) {
		p.x += dist;
	}

	public static void translateAxisY(Point3D p, int dist) {
		p.y += dist;
	}

	public static void translateAxisZ(Point3D p, int dist) {
		p.z += dist;
	}

	// [END]
	// ==========================================================================================
}
