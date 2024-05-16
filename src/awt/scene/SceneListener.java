package awt.scene;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import engine.Parameters;
import scene3D.SceneActions;

public class SceneListener
		implements
		MouseListener,
		MouseMotionListener,
		KeyListener, KeyEventDispatcher, MouseWheelListener {

	public enum MyClickType {
		rightClick, leftClick, wheelClick, nextClick, previousClick
	}

	public enum SceneMode {
		DRAWING, EDITOR
	}

	private int lastX;
	private int lastY;
	private SceneMode currentMode;

	private boolean draggin;

	private SceneActions mSceneActions;

	public SceneListener(SceneActions actions) {
		mSceneActions = actions;
	}

	// EVENTS
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		int wheelRotation = e.getWheelRotation();
		if (wheelRotation == 1) {
			// System.out.println("zoom-");
			System.out.println(Parameters.DISTANT_FROM_CANVAS);
			mSceneActions.zoomOut();
		} else {
			System.out.println(Parameters.DISTANT_FROM_CANVAS);
			mSceneActions.zoomIn();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("DRAGGIN[" + e.getX() + "," + e.getY() + "]");
		mSceneActions.rotate(lastX, lastY, e.getX(), e.getY());
		mSceneActions.drawPainter(lastX, lastY, e.getX(), e.getY());
		mSceneActions.drawHipotenuse(lastX, lastY, e.getX(), e.getY());
		mSceneActions.drawGizmo(lastX, lastY, e.getX(), e.getY());
		// mSceneListener.drawTempLine(x, y, e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println("POS[" + e.getX() + "," + e.getY() + "]");
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		// System.out.println("P1[" + x + "," + y + "]");
		// System.out.println("P2[" + e.getX() + "," + e.getY() + "]");

		// mSceneListener.drawLine(x, y, e.getX(), e.getY());
		mSceneActions.select(e.getX(), e.getY());
		mSceneActions.cleanPainter();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("MOUSE HAS ENTERED");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("MOUSE HAS EXIT");
	}

	private String getClickType(MouseEvent e) {
		switch (e.getClickCount()) {
			case -1:
				return null;
			case 1:
				return MyClickType.leftClick.toString();
			case 2:
				return MyClickType.wheelClick.toString();
			case 3:
				return MyClickType.rightClick.toString();
			case 4:
				return MyClickType.previousClick.toString();
			case 5:
				return MyClickType.nextClick.toString();
			default:
				return null;
		}

	}

	// KEYBOARD IMPLEMENTATION
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("KEY TYPED " + e.getKeyCode());

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KEY PRESSED " + e.getKeyCode());

		int keyCOde = e.getKeyCode();

		switch (keyCOde) {
			case 38:
				mSceneActions.zoomIn();
				break;
			case 40:
				mSceneActions.zoomOut();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KEY RELEASED " + e.getKeyCode());

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		return false;
	}

}
