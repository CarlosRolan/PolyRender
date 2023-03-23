package myInputs;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyBoard implements KeyListener, KeyEventDispatcher {

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("KEY PRESSED " + e.getKeyChar());

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isActionKey())
			System.out.println("KEY PRESSED " + e.paramString());
		else
			System.out.println("KEY PRESSED " + e.getKeyChar());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KEY PRESSED " + e.getKeyChar());

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		System.out.println(e.getKeyCode());
		return false;
	}

}
