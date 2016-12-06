package model;

import java.lang.Math;
/**
 * Enemy1 is a subclass of Hazard
 * <p>
 * Enemy1 represents the Catfish. Enemy1's attributes are the same as all hazards:
 * <ul>
 * <li> int xpos - The X position of the enemy
 * <li> int ypos - The Y position of the enemy
 * <li> int xvel - The X velocity of the enemy
 * <li> int yvel - The Y velocity of the enemy
 * <li> int limit - tool used to set fresh water and salt water zones
 * <li> enum MovementType - The type of movement the enemy takes on
 * <li> String 
 * </ul>
 * 
 * @author Group 1
 * @see Hazard
 */
public class Enemy3 extends Hazard {
	public Enemy3(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.ENEMYMOVE, "fish_trout_left");
		this.setType(HazardType.ENEMY3);
	}
}