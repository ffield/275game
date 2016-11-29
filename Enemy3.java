package model;

import java.lang.Math;

public class Enemy3 extends Hazard {
	public Enemy3(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE,"fish");
		this.setType(HazardType.ENEMY3);
	}
	// public Enemy3(){
	// super(200,200,1,1);
	// }

}