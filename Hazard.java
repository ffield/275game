package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hazard extends Movers {
	int spawntime;
	String hazardImageType;
	HazardType type;
	MovementType movementType;
	Game game;
	BufferedImage image;

	public Hazard(int xpos, int ypos, int xvel, int yvel, int spawntime, MovementType hazardMovementType, String imageType) {
		super(xpos, ypos, xvel, yvel);
		System.out.println("Is this file writing getting called al ot");
		this.spawntime = spawntime;
		this.movementType = hazardMovementType;
		this.hazardImageType = imageType;
		color = Color.BLACK;;
    	try {
    		image = ImageIO.read(new File("images/" + hazardImageType + ".png"));
        	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
	}

	public int getSpawntime() {
		return spawntime;
	}

	public Rectangle getBounds() {
		Rectangle r = new Rectangle(xpos, ypos, 200, 200);
		return r;
	}

	public void setSpawntime(int spawntime) {
		this.spawntime = spawntime;
	}

	public HazardType getType() {
		return this.type;
	}

	public void setType(HazardType hazardType) {
		this.type = hazardType;
	}

	/**
	 * Moves the hazard object one unit to the left in the game.
	 * 
	 */

	public void move() {
		switch (movementType) {
		case ENEMYMOVE:
			this.xpos = xpos - xvel;
			double y = Math.sin(xpos / 20) * (-12);
			this.ypos = ((int) y) + ypos;
			break;
		case LEFT:
			this.xpos = xpos - 1;
			break;
		case COLLIDEDUP:
			this.xpos = xpos + 5;
			this.ypos = ypos - 1;
			break;
		case COLLIDEDDOWN:
			this.xpos = xpos + 5;
			this.ypos = ypos + 1;
		case RIGHT:
			this.xpos = xpos + 1;
			break;
		case ENEMY2MOVE:
			if (this.getFoundLine().equals(false)) {
				if (this.ypos == game.getPlayer().getYpos()) {
					this.setFoundLine(true);
				}
				else if (this.ypos < game.getPlayer().getYpos()) {
					this.ypos += 5;
					this.xpos -=1;
				}
				else if (this.ypos > game.getPlayer().getLife()) {
					this.ypos -=5;
					this.xpos-=1;
				}
			}
			else {
				this.xpos -= 15;
			}
			
			break;
		}
	}

	private Object getFoundLine() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setFoundLine(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public int getxpos() {
		return xpos;
	}

	public void setxpos(int xpos) {
		this.xpos = xpos;
		super.setXpos(xpos);
	}

	public int getypos() {
		return ypos;
	}

	public void setypos(int ypos) {
		this.ypos = ypos;
		super.setYpos(ypos);
	}

	public int getxvel() {
		return xvel;
	}

	public void setxvel(int xvel) {
		this.xvel = xvel;
	}

	public int getyvel() {
		return yvel;
	}

	public void setyvel(int yvel) {
		this.yvel = yvel;
	}

	/**
	 * Increase the xvel of the hazard by the specified integer increment.
	 * 
	 * @param increment
	 *            the amount the velocity is increased
	 */
	public void increasexvel(int increment) {
		xvel = xvel + increment;
	}

	public MovementType getMovementType() {
		return this.movementType;
	}

	public void setMovementType(MovementType newMovementType) {
		this.movementType = newMovementType;
	}

	public PowerupType getPowerupType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getToolType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setGame(Game classGame){
		this.game = classGame;
	}

	public BufferedImage getImage() {
		return image;
			}
}
