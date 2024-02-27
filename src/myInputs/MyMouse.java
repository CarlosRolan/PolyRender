package myInputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import editor.SceneActions;

public class MyMouse
		implements
		MouseListener,
		MouseMotionListener,
		MouseWheelListener {

	int x;
	int y;

	private boolean draggin;

	private SceneActions mSceneListener;

	public MyMouse() {

	}

	public MyMouse(SceneActions actions) {
		mSceneListener = actions;
	}

	// EVENTS
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotation = e.getWheelRotation();
		if (rotation == 1) {
			//System.out.println("zoom-");
			mSceneListener.zoomOut();
		} else {
			//System.out.println("zoom+");
			mSceneListener.zoomIn();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//System.out.println("DRAGGIN[" + e.getX() + "," + e.getY() + "]");
		mSceneListener.drawTempLine(x, y, e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("POS[" + e.getX() + "," + e.getY() + "]");
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		//System.out.println("P1[" + x + "," + y + "]");
		//System.out.println("P2[" + e.getX() + "," + e.getY() + "]");

		mSceneListener.drawLine(x, y, e.getX(), e.getY());

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

}
