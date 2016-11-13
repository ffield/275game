package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyFunctions extends KeyAdapter {
	static boolean[] keys = new boolean[1000];

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		// switch(e.getKeyCode()){
		// case KeyEvent.VK_RIGHT:
		// player.setXvel(10);
		// break;
		// case KeyEvent.VK_LEFT:
		// player.setXvel(-10);
		// break;
		// case KeyEvent.VK_KP_UP:
		// player.setYvel(-10);
		// break;
		// case KeyEvent.VK_DOWN:
		// player.setYvel(10);
		// break;
		// }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		// switch(e.getKeyCode()){
		// case KeyEvent.VK_RIGHT:
		// player.setXvel(0);
		// break;
		// case KeyEvent.VK_LEFT:
		// player.setXvel(0);
		// break;
		// case KeyEvent.VK_UP:
		// player.setYvel(0);
		// break;
		// case KeyEvent.VK_DOWN:
		// player.setYvel(0);
		// break;
		// }
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
