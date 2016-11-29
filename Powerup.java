package model;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Powerup extends Hazard {
	PowerupType powerupType;
	Color color;

	public Powerup(int xpos, int ypos, int xvel, int yvel, int spawntime) {
		super(xpos, ypos, xvel, yvel, spawntime, MovementType.LEFT,"powerup");
		this.setType(HazardType.POWERUP);
		Random r = new Random();
		switch (r.nextInt(3)) {
		case 0:
			this.powerupType = PowerupType.INVINCIBLE;
			color = color.YELLOW;
			this.hazardImageType = "invincible";
			break;
		case 1:
			this.powerupType = PowerupType.CLEAR;
			color = color.ORANGE;
			this.hazardImageType = "clear";
			break;
		case 2:
			this.powerupType = PowerupType.SPEED;
			color = color.RED;
			this.hazardImageType = "speedup";
			break;
		}
		try {
    		this.image = ImageIO.read(new File("images/" + hazardImageType + ".png"));
        	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
	}

	public PowerupType getPowerupType() {
		return this.powerupType;
	}

	public Color getColor() {
		return this.color;
	}
}