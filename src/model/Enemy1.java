package model;

import java.lang.Math;
/**
 * Enemy1 Model Object
 * <p>
 * Enemy1 represents the Catfish. Enemy1's attributes are the same as all hazards
 * 
 * @author Group 1
 * @see Hazard
 */
public class Enemy1 extends Hazard {
	/**
	 * Constructor
	 * @param xpos X position of hazard
	 * @param ypos Y position of hazard
	 * @param xvel X velocity of hazard
	 * @param yvel Y velocity of hazard
	 * @param spawntime integer representing when the enemy will spawn.
	 */
	public Enemy1(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE, "fish_catfish_left_0");
		this.setType(HazardType.ENEMY1);
	}
}