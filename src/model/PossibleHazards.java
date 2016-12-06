package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
/**
 * PossibleHazards Model Object
 * <p>
 * The PossibleHazards object represents all the of the hazards that attack the player on the screen.  PossibleHazards has attributes:
 * <ul>
 * <li> int hazard_num - The number of hazards in the level
 * <li> ArrayList hazards - The ArrayList containing all the hazards
 * </ul>
 * @author Group 1
 * @see Hazard
 */
public class PossibleHazards {
	int hazard_num;
	ArrayList<Hazard> hazards;

	/**
	 * constructor for the possible hazards class which contains all of the
	 * possible hazards that can spawn in a given round
	 * @param hazard_num The number of hazards in a round
	 * 
	 */
	public PossibleHazards(int hazard_num) {
		this.hazard_num = hazard_num;
		this.hazards = new ArrayList<Hazard>();
	}
	/**
	 * This function returns if the given num is between the given lower and upper limits, inclusively
	 * @param num The number you want compared
	 * @param lower The lower limit you specify
	 * @param upper The upper limit you specify
	 * @return true if it is between inclusively, false otherwise
	 */
	public static boolean isBetweenInc(int num, int lower, int upper){
		return lower<=num&&num<=upper;
	}
	/**
	 * This method generates hazards based on which level it is
	 * @param screenSize - The dimensions of the screen
	 * @param level - the level it is in the game
	 */
	public void generateHazards(Dimension screenSize, int level) {
		Random gen = new Random();
		int spawnwindow = 750;
		int FRAMEHEIGHT = (int) screenSize.getHeight();
		int FRAMEWIDTH = (int) screenSize.getWidth();
		int spawnInt;
		int vel;
		switch(level){
		default: 
			hazard_num = 25;
			vel = FRAMEWIDTH/300;
			for (int i = 0; i < hazard_num; i++) {
				spawnInt = gen.nextInt(spawnwindow);
				System.out.println("Spawn: " + spawnInt);
				int x = gen.nextInt(11);
				if(isBetweenInc(x, 0, 2))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
				else if(isBetweenInc(x, 3, 5))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
				else if(isBetweenInc(x, 6, 8))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
				else if(x==9)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
				else if(x==10)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.ADDLIFE));
			}
			break;
			
		case 2: 
			hazard_num = 35;
			for (int i = 0; i < hazard_num; i++) {
				vel = FRAMEWIDTH/200;
				spawnInt = gen.nextInt(spawnwindow);
				int x = gen.nextInt(15);
				if(isBetweenInc(x, 0, 2))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
				else if(isBetweenInc(x, 3, 5))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
				else if(isBetweenInc(x, 6, 8))
					hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				else if(isBetweenInc(x, 9, 11))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
				else if(x==12)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
				else if(x==13)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.ADDLIFE));
				else if(x==14)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.ADDLIFE));
			}
			break;
			
		case 3: 
			vel = FRAMEWIDTH/150;
			spawnInt = gen.nextInt(spawnwindow);
			hazard_num = 45;
			for (int i = 0; i < hazard_num; i++) {
				spawnInt = gen.nextInt(spawnwindow);
				System.out.println("Spawn: " + spawnInt);
				int x = gen.nextInt(19);
				if(isBetweenInc(x, 0, 2))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
				else if(isBetweenInc(x, 3, 5))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
				else if(isBetweenInc(x, 6, 8))
					hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				else if(isBetweenInc(x, 9, 11))
					hazards.add(new Enemy2(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				else if(isBetweenInc(x, 12, 14))
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
				else if(x==15)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.ADDLIFE));
				else if(x==16)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.SPEED));
				else if(x==17)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
				else if(x==18)
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.CLEAR));
			}
			break;
			
		
	case 4: 
		gen = new Random();
		spawnInt = gen.nextInt(spawnwindow);
		vel = FRAMEWIDTH/125;
		hazard_num = 60;
		for (int i = 0; i < hazard_num; i++) {
			spawnInt = gen.nextInt(spawnwindow);
			System.out.println("Spawn: " + spawnInt);
			int x = gen.nextInt(19);
			if(isBetweenInc(x, 0, 2))
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
			else if(isBetweenInc(x, 3, 5))
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
			else if(isBetweenInc(x, 6, 8))
				hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
			else if(isBetweenInc(x, 9, 11))
				hazards.add(new Enemy2(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
			else if(isBetweenInc(x, 12, 14))
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
			else if(x==15)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.ADDLIFE));
			else if(x==16)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.SPEED));
			else if(x==17)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
			else if(x==18)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.CLEAR));
		}
		break;
		
		
	case 5: 
		vel = FRAMEWIDTH/100;
		spawnInt = gen.nextInt(spawnwindow);
		hazard_num = 80;
		for (int i = 0; i < hazard_num; i++) {
			spawnInt = gen.nextInt(spawnwindow);
			System.out.println("Spawn: " + spawnInt);
			int x = gen.nextInt(22);
			if(isBetweenInc(x, 0, 2))
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
			else if(isBetweenInc(x, 3, 5))
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
			else if(isBetweenInc(x, 6, 8))
				hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
			else if(isBetweenInc(x, 9, 11))
				hazards.add(new Enemy2(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
			else if(isBetweenInc(x, 12, 14))
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
			else if(x==15)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.ADDLIFE));
			else if(x==16)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.SPEED));
			else if(x==17)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
			else if(x==18)
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT)+1, vel, 0, spawnInt, PowerupType.CLEAR));
			else if(isBetweenInc(x, 19, 21))
				hazards.add(new Enemy3(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
		}
		break;
		
		}
	}
		
	

	/** Sets possibleHazards attribute to a given ArrayList of hazards
	 * @param hazards hazards are an array of all possible hazards Sets the array
	 *            containing all of the possible hazards
	 */

	public void setpossibleHazards(ArrayList<Hazard> hazards) {
		this.hazards = hazards;
	}

	/**
	 * Returns the hazards attribute of PossibleHazards object
	 * @return hazards The hazards attribute of this object
	 */
	public ArrayList<Hazard> getHazardsList() {
		return this.hazards;
	}
	/**
	 * This method moves all the hazards in the hazards attribute left
	 */
	public void moveLeft() {
		for (int i = 0; i < hazard_num; i++) {
			hazards.get(i).move();
		}
	}

	/** This method adds a given hazard to the ArrayList of hazards attribute
	 * @param hazard
	 *            an array of all possible hazards adds a single hazard to the
	 *            array
	 * 
	 */
	public void addHazard(Hazard hazard) {
		hazards.add(hazard);
	}

	/** This method removes the hazard from the hazards attribute at a given index.
	 * @param index
	 *            index is an int that represents the position we want in the
	 *            array Removes a hazard from the array at the given index
	 */
	public void removeHazard(int index) {
		hazards.remove(index);
	}

	/**
	 * select a random hazard in array
	 * @return Hazard
	 */
	public Hazard selectRandom() {
		Random gen = new Random();
		return hazards.get(gen.nextInt(hazards.size()));
	}

	/**
	 * This method clears all enemies and trash off the screen
	 * @param screenSize - Dimensions of screen size (Dimension)
	 * @see Dimension
	 */
	public void clearEnemies(Dimension screenSize) {
		ArrayList<Hazard> removeThese = new ArrayList<Hazard>();
		for (Hazard h : hazards) {
			if (h.getType().equals(HazardType.ENEMY1)) {
				if (h.getXpos() < (int) screenSize.getWidth()) {
					removeThese.add(h);
				}
			} else if (h.getType().equals(HazardType.ENEMY2)) {
				if (h.getXpos() < (int) screenSize.getWidth()) {
					removeThese.add(h);
				}
			} else if (h.getType().equals(HazardType.ENEMY3)) {
				if (h.getXpos() < (int) screenSize.getWidth()) {
					removeThese.add(h);
				}
			} else if (h.getType().equals(HazardType.TRASH)) {
				if (h.getXpos() < (int) screenSize.getWidth()) {
					removeThese.add(h);
				}
			}
		}
		for (Hazard h : removeThese) {
			hazards.remove(h);
		}
	}
}