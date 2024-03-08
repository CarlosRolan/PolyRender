package engine.scene3D;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import engine.obj.basic.Object3D;
import engine.obj.shapes.Axis;

public class Scene3D {

    protected ArrayList<Object3D> mObjects;
    private Color mBackGroundColor;
    public final Axis AXIS = new Axis(500, 500, 500);

   

    public Scene3D() {
        mObjects = new ArrayList<>();
       
    }

    public void addObject(Object3D object3d) {
        mObjects.add(object3d);
    }

    public ArrayList<Object3D> getSceneObjects() {
        return mObjects;
    }

    public void renderScene(Graphics g, int width, int height) {
        // BACKGROUND=====
        g.fillRect(0, 0, width, height);
        // ===============

        // AXIS
        AXIS.renderLines(g);

    }



    public void render(Graphics g) {
        for (Object3D iter : mObjects) {
            if (iter instanceof Axis) {
                iter.renderLines(g);
            } else {
                iter.renderFaces(g);
            }
        }
    }


}
