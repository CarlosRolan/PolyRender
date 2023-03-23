package shapes;

public interface Transformations {

    // ROTATIONS
    public void rotateX(double XDegrees);

    public void rotateY(double YDegrees);

    public void rotateZ(double ZDegrees);

    // SCALE
    public void scale(double scalefactorX, double scalefactorY, double scalefactorZ);

    public void scaleX(double scaleFactorX);

    public void scaleY(double scaleFactorY);

    public void scaleZ(double scaleFactorZ);

    // TRANSLATE
    public void translateX(int xDist);

    public void translateY(int yDist);

    public void translateZ(int ZDist);

}
