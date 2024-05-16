
package engine.basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Object3D {

	protected Poly3D[] mPolygons;
	protected Polygon[] mProyecctions;
	protected double mVolumne;
	protected Color mColor;
	protected boolean mSelected;

	// GETTERs
	public Polygon[] getProyections() {
		mProyecctions = new Polygon[mPolygons.length];
		for (int i = 0; i < mPolygons.length; i++) {
			mProyecctions[i] = mPolygons[i].getProyection();
		}
		return mProyecctions;
	}

	public boolean isSelected() {
		return mSelected;
	}

	// SETTERs
	public void setSelect(boolean isSelected) {
		mSelected = isSelected;
		System.out.println("HAS SELECTED");
	}

	private Polygon[] calculateProyections() {
		mProyecctions = new Polygon[mPolygons.length];
		for (int i = 0; i < mPolygons.length; i++) {
			mProyecctions[i] = mPolygons[i].getProyection();
		}
		return mProyecctions;
	}

	// CONSTRUCTORs
	public Object3D(Color color, Poly3D... polygons) {
		mColor = color;
		mPolygons = polygons;
		// mProyecctions = new Polygon[mPolygons.length];
		setPolygonColor();
	}

	public Object3D(Poly3D... polygons) {
		// We are not using this color
		mColor = Color.WHITE;
		mPolygons = polygons;
		// mProyecctions = new Polygon[mPolygons.length];
	}

	public void renderFaces(Graphics g) {
		mProyecctions = new Polygon[mPolygons.length];
		for (int i = 0; i < mPolygons.length; i++) {
			mProyecctions[i] = mPolygons[i].getProyection();
			mPolygons[i].renderFaces(g);
		}
	}

	public void renderLines(Graphics g) {
		calculateProyections();

		for (int i = 0; i < mPolygons.length; i++) {
			mPolygons[i].renderLines(g);
		}
	}

	// IMPORTANTISIMO HACE QUE CARAS SE VEN DELANTE Y QUE CARAS DETRAS
	// TODO
	private Poly3D[] sortPolygons() {
		ArrayList<Poly3D> polyList = new ArrayList<Poly3D>();

		for (Poly3D myPolygon : mPolygons) {
			polyList.add(myPolygon);
		}

		Collections.sort(polyList, new Comparator<Poly3D>() {
			@Override
			public int compare(Poly3D p1, Poly3D p2) {
				return p2.getAverageDepth() - p1.getAverageDepth() < 0 ? 1 : -1;
			}
		});

		for (int i = 0; i < mPolygons.length; i++) {
			mPolygons[i] = polyList.get(i);
		}

		return mPolygons;
	}

	public boolean checkSelection(int x, int y) {
		for (Poly3D poly3d : mPolygons) {
			Polygon proyection = poly3d.getProyection();
			if (proyection.contains(x, y)) {
				setSelect(true);
				return true;
			}
		}
		return false;
	}

	// [START]
	// =====================TRANSFORMATIONS===================================================
	// Rotation
	public void rotate(double xDegrees, double yDegrees, double zDegrees) {
		for (Poly3D p : mPolygons) {
			p.rotate(xDegrees, yDegrees, zDegrees);
		}
		sortPolygons();
	}

	public void rotateX(double xDegrees) {
		for (Poly3D p : mPolygons) {
			p.rotateX(xDegrees);
		}
		sortPolygons();
	}

	public void rotateY(double yDegrees) {
		for (Poly3D p : mPolygons) {
			p.rotateY(yDegrees);
		}
		sortPolygons();
	}

	public void rotateZ(double zDegrees) {
		for (Poly3D p : mPolygons) {
			p.rotateZ(zDegrees);
		}
		sortPolygons();
	}

	public void scale(double scalefactorX, double scalefactorY,
			double scalefactorZ) {
		for (Poly3D p : mPolygons) {
			p.scale(scalefactorX, scalefactorY, scalefactorZ);
		}
		sortPolygons();
	}

	public void scaleX(double scalefactorX) {
		for (Poly3D p : mPolygons) {
			p.scaleX(scalefactorX);
		}
		sortPolygons();
	}

	public void scaleY(double scalefactorY) {
		for (Poly3D p : mPolygons) {
			p.scaleY(scalefactorY);
		}
		sortPolygons();
	}

	protected void translate(int xDist, int yDist, int zDist) {
		for (Poly3D p : mPolygons) {
			p.translate(xDist, yDist, zDist);
		}
		sortPolygons();
	}
	// [END]
	// ==========================================================================================

	private void setPolygonColor() {
		for (Poly3D poly : mPolygons) {
			poly.setColor(mColor);
		}
	}
}
