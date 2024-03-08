package awt.scene;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import engine.PointConverter;
import engine.obj.basic.Object3D;
import engine.obj.basic.Point3D;
import engine.obj.basic.Poly3D;
import engine.scene3D.Scene3D;
import engine.scene3D.SceneActions;

public class SceneWindow extends Canvas implements SceneActions {

    private static final long serialVersionUID = 1L;

    // protected Scene3D scene;
    private Scene3D mScene;
    private SceneListener mSceneListener;
    private Graphics mGraphics;

    public Point[] mPainter;
    public Point[] mH;
    public Object3D gizmo;

    // OBJECTS TO RENDER
    public SceneWindow() {
        mScene = new Scene3D();

        // init listener
        mSceneListener = new SceneListener(this);
        addMouseListener(mSceneListener);
        addMouseMotionListener(mSceneListener);
        addMouseWheelListener(mSceneListener);
    }

    // RENNDER OUR CANVAS-ALL THINGS U WANNA SEE HAS TO BE RENDER HERE
    // TODO Asegurate que cuando llames a este metodo Scene ya tenga un getWidth() y
    // un getWidth()
    public void renderPainter() {
        // Render the pre line
        if (mPainter != null) {
            mGraphics.drawOval((int) mPainter[0].getX(), (int) mPainter[0].getY(), (int) mPainter[1].getX(),
                    (int) mPainter[1].getY());
            mGraphics.drawLine((int) mPainter[0].getX(), (int) mPainter[0].getY(), (int) mPainter[1].getX(),
                    (int) mPainter[1].getY());
            mGraphics.drawRect((int) mPainter[0].getX(), (int) mPainter[0].getY(), (int) mPainter[1].getX(),
                    (int) mPainter[1].getY());
        }
    }

    public void renderHipotenuse() {
        if (mH != null) {
            mGraphics.drawLine((int) mH[0].getX(), (int) mH[0].getY(), (int) mH[1].getX(),
                    (int) mH[1].getY());
        }
    }

    public void renderGizmo() {
        if (gizmo != null) {
            gizmo.renderLines(mGraphics);
        }
    }

    public void render() {

        PointConverter.width = getWidth() / 2;
        PointConverter.height = getHeight() / 2;

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        // CANVAS PROPERTIES
        mGraphics = bs.getDrawGraphics();

        mScene.renderScene(mGraphics, getWidth(), getHeight());

        // renderPainter();
        // renderHipotenuse();
        renderGizmo();

        bs.show();
    }

    // EACH FRAME
    public void update() {

    }

    @Override
    public void select(int x1, int y1) {

    }

    @Override
    public void zoomIn() {

    }

    @Override
    public void zoomOut() {

    }

    @Override
    public void rotate(int x1, int y1, int x2, int y2) {

        double h;
        double a = x2 + (getWidth() / 2);
        double b = y2 + (getHeight() / 2);
        double theta;

        h = Math.sqrt(x2 * x2 + y2 * y2);

        theta = Math.atan2(a, b);

        double angle = (theta * 180) / Math.PI;

        /*
         * 
         * scene.hipotenuse = new Point[2];
         * scene.hipotenuse[0] = new Point(x2, y2);
         * scene.hipotenuse[1] = new Point(getWidth() / 2, getHeight() / 2);
         * 
         * 
         * System.out.println("cateto contiguo=" + x2);
         * System.out.println("cateto opuesto=" + y2);
         * System.out.println("hipotenusa=" + h);
         * System.out.println("theta=" + theta);
         * System.out.println("angle=" + angle);
         * 
         */

        // cubeGhost.npoints = cubeProy[0].npoints;
        // cubeGhost.xpoints = cubeProy[0].xpoints;
        // cubeGhost.ypoints = cubeProy[0].ypoints;
        // mScene.AXIS.rotate(1, 1, 1);
    }

    @Override
    public void cleanPainter() {
        mPainter = null;
    }

    @Override
    public void drawPainter(int x1, int y1, int x2, int y2) {
        mPainter = new Point[2];
        mPainter[0] = new Point(x1, y1);
        mPainter[1] = new Point(x2, y2);
    }

    @Override
    public void drawHipotenuse(int x1, int y1, int x2, int y2) {
        mH = new Point[2];
        mH[0] = new Point(getWidth() / 2, getHeight() / 2);
        mH[1] = new Point(x2, y2);
    }

    @Override
    public void drawGizmo(int x1, int y1, int x2, int y2) {
        x1 = x1 - (getWidth() / 2);
        x2 = x2 - (getWidth() / 2);
        y1 = y1 - (getHeight() / 2);
        y2 = y2 - (getHeight() / 2);
        Point3D px1 = new Point3D(x1, y1, 0);
        Point3D px2 = new Point3D(x2, y2, 0);

        double module = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        Point3D py1 = new Point3D(x2 - x1, y2 - y1, 0);
        Point3D py2 = new Point3D(x2, y2, 0);

        Poly3D linex = new Poly3D(Color.RED, px1, px2);
        Poly3D liney = new Poly3D(Color.GREEN, py1, py2);

        gizmo = new Object3D(linex, liney);

        System.out.println(module);

    }

    @Override
    public void rotateGizmo(int x1, int y1, int x2, int y2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rotateGizmo'");
    }

}
