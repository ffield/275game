package model;

import java.lang.Math;

public class Enemy2 extends Hazard {
	public boolean foundLine = false;
	public Enemy2(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE,"fish");
		this.setType(HazardType.ENEMY2);
	}
	
	public boolean getFoundLine() {
		return this.foundLine;
	}
	
	public void setFoundLine(boolean foundValue){
		this.foundLine = foundValue;
	}
	// public Enemy2(){
	// super(200,200,1,1);
	// }
	//
	// public void moveLeft(){
	// this.xpos = xpos-xvel;
	// double y = Math.sin(xpos/5)*(-25);
	// this.ypos = ((int)y)+ypos;
	// }
}