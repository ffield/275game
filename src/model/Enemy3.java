package model;

import java.lang.Math;
/**
 * Enemy3 Model Object
 * <p>
 * Enemy3 represents the Trout. Enemy3's attributes are the same as all hazards
 * 
 * @author Group 1
 * @see Hazard
 */
public class Enemy3 extends Hazard {
	/**
	 * Constructor
	 * @param xpos X position of hazard
	 * @param ypos Y position of hazard
	 * @param xvel X velocity of hazard
	 * @param yvel Y velocity of hazard
	 * @param spawntime integer representing when the enemy will spawn.
	 */
	public Enemy3(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE, "fish_trout_left");
		this.setType(HazardType.ENEMY3);
	}
}