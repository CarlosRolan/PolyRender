package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyPolyhedro {

	protected Poly3D[] mPolygons;
	protected double mVolumne;
	protected Color mColor;

	// CONSTRUCTORs
	public MyPolyhedro(Color color, Poly3D... polygons) {
		mColor = color;
		mPolygons = polygons;
		setPolygonColor();
	}

	public MyPolyhedro(Poly3D... polygons) {
		// We are not using this color
		mColor = Color.WHITE;
		mPolygons = polygons;
	}

	public void render(Graphics g) {
		for (Poly3D poly : mPolygons) {
			poly.render(g);
		}
	}

	public void renderFaces(Graphics g, int centerX, int centerY) {
		for (Poly3D poly : mPolygons) {
			poly.renderFaces(g, centerX, centerY);
		}
	}

	public void renderLines(Graphics g, int centerX, int centerY) {
		for (Poly3D poly : mPolygons) {
			poly.renderLines(g, centerX, centerY);
		}
	}

	// IMPORTANTISIMO HACE QUE CARAS SE VEN DELANTE Y QUE CARAS DETRAS
	// TODO
	private Poly3D[] sortPolygons() {
		List<Poly3D> polyList = new ArrayList<Poly3D>();

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

	public void translate(int xDist, int yDist, int zDist) {
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

	private void setPolygonColor(Color color) {
		for (Poly3D poly : mPolygons) {
			poly.setColor(color);
		}
	}

}
