package model;

import java.awt.Rectangle;
import java.lang.Math;

public class Enemy1 extends Hazard {
	public Enemy1(int xpos, int ypos, int xvel, int yvel, int spawntime, int SCREENHEIGHT) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE, "catfish", SCREENHEIGHT);
		this.setType(HazardType.ENEMY1);
	}
	
	@Override
	public Rectangle getBounds() {
		Rectangle r = new Rectangle(xpos, ypos, 80, 40);
		return r;
	}
	// public Enemy1(){
	// super(200,200,1,1);
	// }

	// public void moveLeft(){
	// this.xpos = xpos-xvel;
	// double y = Math.sin(xpos/6)*15;
	// this.ypos = ((int)y)+ypos;
	// }
}