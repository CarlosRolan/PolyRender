package editor;

public interface SceneActions {

    public static final int ACTION_DRAW_LINE = 0;

    void zoomIn();

    void zoomOut();

    void rotate();

    void drawTempLine(int x1, int y1, int x2, int y2);

    void drawLine(int x1, int y1, int x2, int y2);

}
