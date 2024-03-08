package engine.scene3D;

public interface SceneActions {

    void select(int x1, int y1);

    void zoomIn();

    void zoomOut();

    void rotate(int x1, int y1, int x2, int y2);

    void drawPainter(int x1, int y1, int x2, int y2);

    void cleanPainter();

    void drawHipotenuse(int x1, int y1, int x2, int y2);

    void drawGizmo(int x1, int y1, int x2, int y2);

    void rotateGizmo(int x1, int y1, int x2, int y2);

}
