package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motorGraphic.PointConverter;

import java.awt.Point;

/**
 * @author Carlos Rolán Díaz
 *
 */
public class MyPolygon extends Polygon {

	private static final long serialVersionUID = 1L;
	private MyPoint[] points;
	private Color color;
	// REMEBER THAT THE POINT (0,0) IS IN THE UP-LEFT CORNER
	private int screenX = 0;
	private int screenY = 0;

	// GETTERS
	public int getcX() {
		return screenX;
	}

	public int getcY() {
		return screenY;
	}

	// SETTERS
	public void setcX(int screenX) {
		this.screenX = screenX;
	}

	public void setcY(int screenY) {
		this.screenY = screenY;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// CONSTRUCTOR
	public MyPolygon(Color color, int screenX, int screenY, MyPoint... points) {
		this.color = color;
		this.screenX = screenX;
		this.screenY = screenY;
		this.points = new MyPoint[points.length];
		for (int i = 0; i < points.length; i++) {
			MyPoint p = points[i];
			this.points[i] = new MyPoint(p.x, p.y, p.z);
		}
	}

	// This syntaxis means = poins is an array of MyPoint of length x
	public MyPolygon(Color color, MyPoint... points) {
		this.color = color;
		this.points = new MyPoint[points.length];
		for (int i = 0; i < points.length; i++) {
			MyPoint p = points[i];
			this.points[i] = new MyPoint(p.x, p.y, p.z);
		}
	}

	// This syntaxis means = poins is an array of MyPoint of length x
	public MyPolygon(MyPoint... points) {
		this.color = Color.WHITE;
		this.points = new MyPoint[points.length];
		for (int i = 0; i < points.length; i++) {
			MyPoint p = points[i];
			this.points[i] = new MyPoint(p.x, p.y, p.z);
		}
	}

	// PUBLIC METHODs
	// TODO
	public MyPoint calculate_G_Center() {
		int x = 0;
		int y = 0;
		return null;
	}

	// HOW TO SEE
	public void renderFaces(Graphics g) {
		Polygon poly = new Polygon();
		for (int i = 0; i < this.points.length; i++) {
			Point p1 = PointConverter.convertPoint(this.points[i]);
			poly.addPoint(p1.x + getcX() / 2, p1.y + getcX() / 2);
		}
		g.setColor(this.color);
		g.fillPolygon(poly);

	}

	public void renderLines(Graphics g) {
		Polygon poly = new Polygon();
		for (int i = 0; i < this.points.length; i++) {
			Point p1 = PointConverter.convertPoint(this.points[i]);
			poly.addPoint(p1.x + getcX(), p1.y + getcY());
		}
		g.setColor(this.color);
		g.drawPolygon(poly);

	}

	// [START]
	// =====================TRANSFORMATIONS===================================================
	// Rotation
	public void rotate(double xDegrees, double yDegrees, double zDegrees) {
		for (MyPoint p : points) {
			PointConverter.rotateAxisX(p, xDegrees, 1);
			PointConverter.rotateAxisY(p, yDegrees, 1);
			PointConverter.rotateAxisZ(p, zDegrees, 1);
		}
	}

	public void rotateX(double xDegrees) {
		for (MyPoint p : this.points) {
			PointConverter.rotateAxisX(p, xDegrees, 1);
		}
	}

	public void rotateY(double yDegrees) {
		for (MyPoint p : points) {
			PointConverter.rotateAxisY(p, yDegrees, 1);
		}
	}

	public void rotateZ(double zDegrees) {
		for (MyPoint p : points) {
			PointConverter.rotateAxisZ(p, zDegrees, 1);
		}
	}

	// Scale
	public void scale(double scalefactorX, double scalefactorY,
			double scalefactorZ) {
		for (MyPoint p : this.points) {
			PointConverter.scaleAxisX(p, scalefactorX);
			PointConverter.scaleAxisY(p, scalefactorY);
			PointConverter.scaleAxisZ(p, scalefactorZ);
		}
	}

	public void scaleX(double scalefactorX) {
		for (MyPoint p : this.points) {
			PointConverter.scaleAxisX(p, scalefactorX);
		}
	}

	public void scaleY(double scalefactorY) {
		for (MyPoint p : this.points) {
			PointConverter.scaleAxisY(p, scalefactorY);
		}
	}

	public void scaleZ(double scalefactorZ) {
		for (MyPoint p : this.points) {
			PointConverter.scaleAxisZ(p, scalefactorZ);
		}
	}

	// Translation
	public void translate(int xDist, int yDist, int zDist) {
		for (MyPoint p : this.points) {
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
	protected double getAverageDepth() {
		double sum = 0;
		for (MyPoint p : this.points) {
			sum += p.z;
		}
		return sum / this.points.length;
	}

}
