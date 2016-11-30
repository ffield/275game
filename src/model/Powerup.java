package model;

import java.awt.Color;
import java.util.Random;

public class Powerup extends Hazard {
	PowerupType powerupType;
	Color color;

	public Powerup(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.LEFT, "");
		this.setType(HazardType.POWERUP);
		Random r = new Random();
		switch (r.nextInt(3)) {
		case 0:
			this.powerupType = PowerupType.INVINCIBLE;
			color = color.YELLOW;
			imageType = "invincible";
			break;
		case 1:
			this.powerupType = PowerupType.CLEAR;
			color = color.ORANGE;
			imageType = "clear";
			break;
		case 2:
			this.powerupType = PowerupType.SPEED;
			color = color.RED;
			imageType = "speed";
			break;
		
		case 3:
			this.powerupType = PowerupType.ADDLIFE;
			color = color.WHITE;
			imageType = "addlife";
			break;
		}
	}
		
		public Powerup(int xpos, int ypos, int xvel, int yvel, int spawntime, PowerupType powerupType) {
			super(xpos, ypos, xvel, yvel, spawntime, MovementType.LEFT, "recycle");
			this.setType(HazardType.POWERUP);
			switch (powerupType) {
			case INVINCIBLE:
				color = color.YELLOW;
				this.setImageType("trashpicker");
				break;
			case CLEAR:
				color = color.ORANGE;
				this.setImageType("fish_eggs");
				break;
			case SPEED:
				color = color.RED;
				this.setImageType("zebramussel");
				break;
			case ADDLIFE:
				color = color.WHITE;
				this.setImageType("food_pellet");
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