package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Movers {
	int xpos;
	int ypos;
	int xvel;
	int yvel;
	String imageType;
	Color color;
	BufferedImage image;

	Movers(int xpos, int ypos, int xvel, int yvel, String imageType) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.xvel = xvel;
		this.yvel = yvel;
		this.imageType = imageType;
		try {
    		image = ImageIO.read(new File("images/" + imageType + ".png"));
        	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	}
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public int getXvel() {
		return xvel;
	}

	public void setXvel(int xvel) {
		this.xvel = xvel;
	}

	public int getYvel() {
		return yvel;
	}

	public void setYvel(int yvel) {
		this.yvel = yvel;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}
	
	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * moveX updates the x position with the given velocity
	 * 
	 * @author Jeanine
	 * @param vel
	 *            an int that represents the velocity that you want the x
	 *            position to move at
	 * @return xpos
	 */
	public void moveX(int vel) {
		xpos += vel;
	}

	/**
	 * moveY updates the Y position with the given velocity
	 * 
	 * @author Jeanine
	 * @param vel
	 *            an int that represents the velocity that you want the Y
	 *            position to move at
	 * @return ypos
	 */
	public void moveY(int vel) {
		ypos += vel;
	}

}
