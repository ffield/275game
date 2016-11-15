package model;

import java.awt.Color;
import java.util.Random;

public class Powerup extends Hazard {
	PowerupType powerupType;
	Color color;

	public Powerup(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.LEFT);
		this.setType(HazardType.POWERUP);
		Random r = new Random();
		switch (r.nextInt(3)) {
		case 0:
			this.powerupType = PowerupType.INVINCIBLE;
			color = color.YELLOW;
			break;
		case 1:
			this.powerupType = PowerupType.CLEAR;
			color = color.ORANGE;
			break;
		case 2:
			this.powerupType = PowerupType.SPEED;
			color = color.RED;
			break;
		}
		
	}
		
		public Powerup(int xpos, int ypos, int xvel, int yvel, int spawntime, PowerupType powerupType) {
			super(xpos, ypos, xvel, yvel, spawntime, MovementType.LEFT);
			this.setType(HazardType.POWERUP);
			switch (powerupType) {
			case INVINCIBLE:
				color = color.YELLOW;
				break;
			case CLEAR:
				color = color.ORANGE;
				break;
			case SPEED:
				color = color.RED;
				break;
			}
			this.powerupType = powerupType;
			
	}

	public PowerupType getPowerupType() {
		return this.powerupType;
	}

	public Color getColor() {
		return this.color;
	}
}