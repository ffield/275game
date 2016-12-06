package model;

import java.awt.Rectangle;
import java.lang.Math;

public class Enemy3 extends Hazard {
	public Enemy3(int xpos, int ypos, int xvel, int yvel, int spawntime, int playery) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE3, playery, "eel");
		this.setType(HazardType.ENEMY3);
	}
	
	@Override
	public Rectangle getBounds() {
		Rectangle r = new Rectangle(xpos, ypos, 80, 40);
		return r;
	}

}