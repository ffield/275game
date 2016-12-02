package model;

import java.lang.Math;

public class Enemy1 extends Hazard {
	public Enemy1(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE, "fish_catfish_left_0");
		this.setType(HazardType.ENEMY1);
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