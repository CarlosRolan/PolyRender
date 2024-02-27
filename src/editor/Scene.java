package editor;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import editor.obj.Axis;
import myInputs.MyMouse;
import shapes.MyCube;
import shapes.MyPolyhedro;

public class Scene extends Canvas implements SceneActions {

    private static final long serialVersionUID = 1L;

    Axis a;
    MyCube cube;

    private volatile ArrayList<MyPolyhedro> objects3D = new ArrayList<>();
    private volatile ArrayList<Point[]> lines = new ArrayList<>();

    MyMouse mouse;
    Graphics g;
    Point[] tempLine;

    // OBJECTS TO RENDER
    public Scene() {

        mouse = new MyMouse(this);
        addMouseListener(mouse);
        addMouseWheelListener(mouse);
        addMouseMotionListener(mouse);

    }

    public void init() {
        a = new Axis(getWidth(), getHeight(), 100);
        cube = new MyCube(100);
        objects3D.add(a);
        objects3D.add(cube);
    }

    // RENNDER OUR CANVAS-ALL THINGS U WANNA SEE HAS TO BE RENDER HERE
    // TODO Asegurate que cuando llames a este metodo Scene ya tenga un getWidth() y
    // un getWidth()
    public void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        // CANVAS PROPERTIES
        g = bs.getDrawGraphics();

        // BACKGROUND=====
        g.fillRect(0, 0, getWidth(), getHeight());
        // ===============

        renderObjects();
        renderLines();

        bs.show();
    }

    // EACH FRAME
    public void update() {

    }

    private void renderObjects() {
        for (MyPolyhedro object : objects3D) {
            if (object instanceof Axis) {
                object.renderLines(g, getWidth() / 2, getHeight() / 2);
            } else {
                object.renderFaces(g, getWidth() / 2, getHeight() / 2);
            }

        }
    }

    private void renderLines() {
        for (Point[] line : lines) {
            g.drawLine((int) line[0].getX(), (int) line[0].getY(), (int) line[1].getX(), (int) line[1].getY());
        }

        if (tempLine != null) {
            g.drawLine((int) tempLine[0].getX(), (int) tempLine[0].getY(), (int) tempLine[1].getX(),
                    (int) tempLine[1].getY());
        }
    }

    @Override
    public void rotate() {

    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        int[] center = { getWidth() / 2, getHeight() / 2 };

        int x1new = x1 + center[0];
        int y1new = y1 + center[1];
        int x2new = x2 + center[0];
        int y2new = y2 + center[1];

        Point pointA = new Point(x1, y1);
        Point pointB = new Point(x2, y2);

        Point[] line = { pointA, pointB };

        lines.add(line);
    }

    @Override
    public void zoomIn() {
        a.rotateZ(1);
    }

    @Override
    public void zoomOut() {
        a.rotateZ(-1);
    }

    @Override
    public void drawTempLine(int x1, int y1, int x2, int y2) {
        tempLine = new Point[2];
        tempLine[0] = new Point(x1, y1);
        tempLine[1] = new Point(x2, y2);
    }

}
