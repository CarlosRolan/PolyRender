package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyPolyhedro {

	protected MyPolygon[] polygons;
	protected double volumne;
	protected Color color;

	// CONSTRUCTORs
	public MyPolyhedro(Color color, MyPolygon... polygons) {
		this.color = color;
		this.polygons = polygons;
		this.setPolygonColor();
	}

	public MyPolyhedro(MyPolygon... polygons) {
		// We are not using this color
		this.color = Color.WHITE;
		this.polygons = polygons;
	}

	public void renderFaces(Graphics g) {
		for (MyPolygon poly : this.polygons) {
			poly.renderFaces(g);
		}
	}

	public void renderLines(Graphics g) {
		for (MyPolygon poly : this.polygons) {
			poly.renderLines(g);
		}
	}

	// IMPORTANTISIMO HACE QUE CARAS SE VEN DELANTE Y QUE CARAS DETRAS
	// TODO
	private MyPolygon[] sortPolygons() {
		List<MyPolygon> polyList = new ArrayList<MyPolygon>();

		for (MyPolygon myPolygon : this.polygons) {
			polyList.add(myPolygon);
		}

		Collections.sort(polyList, new Comparator<MyPolygon>() {
			@Override
			public int compare(MyPolygon p1, MyPolygon p2) {
				return p2.getAverageDepth() - p1.getAverageDepth() < 0 ? 1 : -1;
			}
		});

		for (int i = 0; i < this.polygons.length; i++) {
			this.polygons[i] = polyList.get(i);
		}

		return this.polygons;
	}

	// [START]
	// =====================TRANSFORMATIONS===================================================
	// Rotation
	public void rotate(double xDegrees, double yDegrees, double zDegrees) {
		for (MyPolygon p : this.polygons) {
			p.rotate(xDegrees, yDegrees, zDegrees);
		}
		this.sortPolygons();
	}

	public void rotateX(double xDegrees) {
		for (MyPolygon p : this.polygons) {
			p.rotateX(xDegrees);
		}
		this.sortPolygons();
	}

	public void rotateY(double yDegrees) {
		for (MyPolygon p : this.polygons) {
			p.rotateY(yDegrees);
		}
		this.sortPolygons();
	}

	public void rotateZ(double zDegrees) {
		for (MyPolygon p : this.polygons) {
			p.rotateZ(zDegrees);
		}
		this.sortPolygons();
	}

	public void scale(double scalefactorX, double scalefactorY,
			double scalefactorZ) {
		for (MyPolygon p : this.polygons) {
			p.scale(scalefactorX, scalefactorY, scalefactorZ);
		}
		this.sortPolygons();
	}

	public void scaleX(double scalefactorX) {
		for (MyPolygon p : this.polygons) {
			p.scaleX(scalefactorX);
		}
		this.sortPolygons();
	}

	public void scaleY(double scalefactorY) {
		for (MyPolygon p : this.polygons) {
			p.scaleY(scalefactorY);
		}
		this.sortPolygons();
	}

	public void translate(int xDist, int yDist, int zDist) {
		for (MyPolygon p : this.polygons) {
			p.translate(xDist, yDist, zDist);
		}
		this.sortPolygons();
	}
	// [END]
	// ==========================================================================================

	private void setPolygonColor() {
		for (MyPolygon poly : this.polygons) {
			poly.setColor(this.color);
		}
	}

	private void setPolygonColor(Color color) {
		for (MyPolygon poly : this.polygons) {
			poly.setColor(color);
		}
	}

}
