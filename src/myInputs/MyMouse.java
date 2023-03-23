package myInputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import view.MyView;

public class MyMouse
		implements
			MouseListener,
			MouseMotionListener,
			MouseWheelListener {

	boolean isDragging = false;
	int x;
	int y;

	// EVENTS
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("MOUSE WHEEL MOVED:");
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isDragging) {
			System.out.println("TO------------------>");
		} else {
			System.out.println("MOUSE DRAGGED FROM:" + "[" + e.getY() + "]"
					+ "[" + e.getX() + "]");
			y = e.getY();
			x = e.getX();
			isDragging = true;
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("MOUSE MOVED FROM:" + "[" + e.getY() + "]" + "["
				+ e.getX() + "]");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("MOUSE CLICKED = press and realese");
		System.out.println("[" + e.getY() + "]" + "[" + e.getX() + "]");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(
				"MOUSE PRESSED " + e.getButton() + ":" + getClickType(e)
						+ " AT:" + "[" + e.getX() + "]" + "[" + e.getY() + "]");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isDragging) {
			System.out.println("FROM [" + x + "," + y + "]" + "[" + e.getX()
					+ "]" + "[" + e.getY() + "]");
		} else {
			System.out.println("MOUSE RELEASED AT:" + "[" + e.getX() + "]" + "["
					+ e.getY() + "]");
		}

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
			case -1 :
				return null;
			case 1 :
				return MyClickType.leftClick.toString();
			case 2 :
				return MyClickType.wheelClick.toString();
			case 3 :
				return MyClickType.rightClick.toString();
			case 4 :
				return MyClickType.previousClick.toString();
			case 5 :
				return MyClickType.nextClick.toString();
			default :
				return null;
		}

	}

}
