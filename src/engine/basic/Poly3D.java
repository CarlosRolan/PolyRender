package engine.basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import engine.PointConverter;

/**
 * @author Carlos Rolán Díaz
 *
 */
public class Poly3D {

	private static final long serialVersionUID = 1L;

	private double mArea;
	private Point3D[] mPoints;
	private Color mColor;
	private Polygon mProyecction;
	private boolean mSelected;
	private Polygon mSelection;

	// REMEBER THAT THE POINT (0,0) IS IN THE UP-LEFT CORNER

	public void setColor(Color color) {
		mColor = color;
	}

	public synchronized Polygon getProyection() {
		if (mProyecction == null) {
			mProyecction = calculateProyection();
		}
		return mProyecction;
	}

	// CONSTRUCTOR
	// This syntaxis means = poins is an array of MyPoint of length x
	public Poly3D(Color color, Point3D... points) {
		mColor = color;
		mPoints = points;
	}

	public Poly3D(Point3D... points) {
		mColor = Color.WHITE;
		mPoints = points;
	}

	private Polygon calculateProyection() {
		Polygon poly = new Polygon();
		for (int i = 0; i < mPoints.length; i++) {
			Point p1 = PointConverter.convertPoint(mPoints[i]);
			poly.addPoint(p1.x, p1.y);
		}
		return poly;
	}

	// PUBLIC METHODs
	// TODO
	public Point3D calculate_G_Center() {
		int x = 0;
		int y = 0;
		return null;
	}

	public void renderLines(Graphics g) {
		g.setColor(mColor);
		mProyecction = calculateProyection();
		g.drawPolygon(mProyecction);
	}

	public void renderFaces(Graphics g) {
		g.setColor(mColor);
		mProyecction = calculateProyection();
		g.fillPolygon(mProyecction);

	}

	// [START]
	// =====================TRANSFORMATIONS===================================================
	// Rotation
	public void rotate(double xDegrees, double yDegrees, double zDegrees) {
		for (Point3D p : mPoints) {
			PointConverter.rotateAxisX(p, xDegrees);
			PointConverter.rotateAxisY(p, yDegrees);
			PointConverter.rotateAxisZ(p, zDegrees);
		}
		mProyecction = calculateProyection();
	}

	public void rotateX(double xDegrees) {
		for (Point3D p : mPoints) {
			PointConverter.rotateAxisX(p, xDegrees);
		}
		mProyecction = calculateProyection();
	}

	public void rotateY(double yDegrees) {
		for (Point3D p : mPoints) {
			PointConverter.rotateAxisY(p, yDegrees);
		}
		mProyecction = calculateProyection();
	}

	public void rotateZ(double zDegrees) {
		for (Point3D p : mPoints) {
			PointConverter.rotateAxisZ(p, zDegrees);
		}
		mProyecction = calculateProyection();
	}

	// Scale
	public void scale(double scalefactorX, double scalefactorY,
			double scalefactorZ) {
		for (Point3D p : mPoints) {
			PointConverter.scaleAxisX(p, scalefactorX);
			PointConverter.scaleAxisY(p, scalefactorY);
			PointConverter.scaleAxisZ(p, scalefactorZ);
		}
	}

	public void scaleX(double scalefactorX) {
		for (Point3D p : mPoints) {
			PointConverter.scaleAxisX(p, scalefactorX);
		}
	}

	public void scaleY(double scalefactorY) {
		for (Point3D p : mPoints) {
			PointConverter.scaleAxisY(p, scalefactorY);
		}
	}

	public void scaleZ(double scalefactorZ) {
		for (Point3D p : mPoints) {
			PointConverter.scaleAxisZ(p, scalefactorZ);
		}
	}

	// Translation
	public void translate(int xDist, int yDist, int zDist) {
		for (Point3D p : mPoints) {
			PointConverter.translateAxisX(p, xDist);
			PointConverter.translateAxisY(p, yDist);
			PointConverter.translateAxisZ(p, zDist);
		}
	}

	public void inverse() {
		scale(-1, -1, 1);
	}
	// [END]
	// ==========================================================================================

	// TODO esta es la solucion chapucera que tenemos para saber que caras estan
	// enfrtne y cuales detras
	// Z = DEPTH
	public double getAverageDepth() {
		double sum = 0;
		for (Point3D p : mPoints) {
			sum += p.z;
		}
		return sum / mPoints.length;
	}

	public String getProyecPointsToString() {
		String toret = "";
		mProyecction = calculateProyection();

		for (int i = 0; i < mProyecction.npoints; i++) {
			toret += "P" + i + "[" + mProyecction.xpoints[i] + "," + mProyecction.ypoints[i] + "]\t";
		}

		return toret;
	}

	public String getPointsToString() {
		String toret = "";

		for (int i = 0; i < mPoints.length; i++) {
			toret += i + mPoints[i].toString();
		}

		return toret;
	}

}
