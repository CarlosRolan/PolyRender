package engine.obj.shapes;

import java.awt.Color;

import engine.obj.basic.Object3D;
import engine.obj.basic.Point3D;
import engine.obj.basic.Poly3D;

public class Axis extends Object3D {

    // XYZ AXIS
    private Poly3D xAxis;
    private Poly3D yAxis;
    private Poly3D zAxis;

    private Poly3D xPos;
    private Poly3D yPos;
    private Poly3D zPos;

    public Axis(int xLenght, int yLenght, int zLenght) {
        init(xLenght, yLenght, zLenght);
    }

    public void init(int width, int height, int depth) {

        xAxis = new Poly3D(
                Color.RED,
                new Point3D(-width, 0, 0),
                new Point3D(0, 0, 0),
                new Point3D(width, 0, 0));
        yAxis = new Poly3D(
                Color.GREEN,
                new Point3D(0, -height, 0),
                new Point3D(0, 0, 0),
                new Point3D(0, height, 0));
        zAxis = new Poly3D(
                Color.BLUE,
                new Point3D(0, 0, -depth),
                new Point3D(0, 0, 0),
                new Point3D(0, 0, depth));

        xPos = new Poly3D(
                Color.RED,
                new Point3D(width, 25, -25),
                new Point3D(width, 25, 25),
                new Point3D(width, -25, +25),
                new Point3D(width, -25, -25));

        yPos = new Poly3D(
                Color.GREEN,
                new Point3D(25, height, -25),
                new Point3D(25, height, 25),
                new Point3D(-25, height, +25),
                new Point3D(-25, height, -25));

        zPos = new Poly3D(
                Color.BLUE,
                new Point3D(25, 25, depth),
                new Point3D(25, -25, depth),
                new Point3D(-25, -25, depth),
                new Point3D(-25, 25, depth));

        mPolygons = new Poly3D[6];
        mPolygons[0] = xAxis;
        mPolygons[1] = yAxis;
        mPolygons[2] = zAxis;
        mPolygons[3] = xPos;
        mPolygons[4] = yPos;
        mPolygons[5] = zPos;

    }

}
