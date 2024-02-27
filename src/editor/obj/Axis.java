package editor.obj;

import java.awt.Color;
import java.awt.Graphics;

import shapes.MyPolyhedro;
import shapes.Point3D;
import shapes.Poly3D;

public class Axis extends MyPolyhedro {

    // XYZ AXIS
    private Poly3D xAxis;
    private Poly3D yAxis;
    private Poly3D zAxis;

    public Axis(int xLenght, int yLenght, int zLenght) {
        init(xLenght, yLenght, zLenght);
    }

    public void init(int width, int height, int depth) {

        xAxis = new Poly3D(
                Color.RED,
                new Point3D(0, 0, 0),
                new Point3D(width, 0, 0));
        yAxis = new Poly3D(
                Color.GREEN,
                new Point3D(0, 0, 0),
                new Point3D(0, width, 0));
        zAxis = new Poly3D(
                Color.BLUE,
                new Point3D(0, 0, 100),
                new Point3D(0, 0, -100));

        mPolygons = new Poly3D[3];
        mPolygons[0] = xAxis;
        mPolygons[1] = yAxis;
        mPolygons[2] = zAxis;

    }

    @Override
    public void render(Graphics g) {

    }

}
