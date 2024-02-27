package motorGraphic;

public class MyBuffer extends Thread {

    private double mFps = 0;
    private boolean mRunning = false;

    public void countFrame() {
        mFps++;
    }

    public void restartFrameCount() {
        mFps = 0;
    }

    public Double getFps() {
        return mFps;
    }

    public boolean isRunning() {
        return mRunning;
    }

    public void setRunning(boolean running) {
        mRunning = running;
    }

    public void setFps(double fps) {
        mFps = fps;
    }

    public MyBuffer() {
        mRunning = false;
    }

    public MyBuffer(Runnable r) {
        super(r);
    }

    public void startBuffer() {
        mRunning = true;
        try {
            start();
        } catch (IllegalThreadStateException e) {
            e.printStackTrace();
        }
    }

    public void stopBuffer() {
        try {
            join();
            mRunning = false;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
