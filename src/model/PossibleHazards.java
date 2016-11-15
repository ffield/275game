package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

public class PossibleHazards {
	int hazard_num;
	ArrayList<Hazard> hazards;

	/**
	 * constructor for the possible hazards class which contains all of the
	 * possible hazards that can spawn in a given round
	 * 
	 */
	public PossibleHazards(int hazard_num) {
		this.hazard_num = hazard_num;
		this.hazards = new ArrayList<Hazard>();
	}

	public void generateHazards(Dimension screenSize, int level) {
		Random gen = new Random();
		System.out.println(level);
		int spawnwindow = 750;
		int FRAMEHEIGHT = (int) screenSize.getHeight();
		int FRAMEWIDTH = (int) screenSize.getWidth();
		int spawnInt;
		int vel;
		switch(level){
		default: 
			//gen = new Random();
			hazard_num = 25;
			vel = 1;
			for (int i = 0; i < hazard_num; i++) {
				spawnInt = gen.nextInt(spawnwindow);
				System.out.println("Spawn: " + spawnInt);
				switch (gen.nextInt(10)) {
				case 0:
				case 1:
				case 2:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
					break;
				case 3:
				case 4:
				case 5:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
					break;
				case 6:
				case 7:
				case 8:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
					break;
				case 9:
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
				}

			}
			break;
			
		case 2: 
			hazard_num = 35;
			for (int i = 0; i < hazard_num; i++) {
				vel = 3;
				spawnInt = gen.nextInt(spawnwindow);
				//System.out.println("Spawn: " + spawnInt);
				switch (gen.nextInt(14)) {
				case 0:
				case 1:
				case 2:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
					break;
				case 3:
				case 4:
				case 5:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
					break;
				case 6:
				case 7:
				case 8:
					hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
					break;
				case 9:
				case 10:
				case 11:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
					break;
				case 12:
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
				case 13:
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
				
				}
				

			}
			break;
			
		case 3: 
			vel = 5;
			spawnInt = gen.nextInt(spawnwindow);
			hazard_num = 45;
			for (int i = 0; i < hazard_num; i++) {
				spawnInt = gen.nextInt(spawnwindow);
				System.out.println("Spawn: " + spawnInt);
				switch (gen.nextInt(18)) {
				case 0:
				case 1:
				case 2:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
					break;
				case 3:
				case 4:
				case 5:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
					break;
				case 6:
				case 7:
				case 8:
					hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
					break;
				case 9:
				case 10:
				case 11:
					hazards.add(new Enemy2(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
					break;
				case 12:
				case 13:
				case 14:
					hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
					break;
				case 15:
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
				case 16:
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
				case 17:
					hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.CLEAR));
				}
				

			}
			break;
			
		
	case 4: 
		gen = new Random();
		spawnInt = gen.nextInt(spawnwindow);
		vel = 7;
		hazard_num = 60;
		for (int i = 0; i < hazard_num; i++) {
			spawnInt = gen.nextInt(spawnwindow);
			System.out.println("Spawn: " + spawnInt);
			switch (gen.nextInt(18)) {
			case 0:
			case 1:
			case 2:
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
				break;
			case 3:
			case 4:
			case 5:
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
				break;
			case 6:
			case 7:
			case 8:
				hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				break;
			case 9:
			case 10:
			case 11:
				hazards.add(new Enemy2(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				break;
			case 12:
			case 13:
			case 14:
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
				break;
			case 15:
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
			case 16:
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
			case 17:
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.CLEAR));
			}
			

		}
		break;
		
		
	case 5: 
		vel = 9;
		spawnInt = gen.nextInt(spawnwindow);
		hazard_num = 80;
		for (int i = 0; i < hazard_num; i++) {
			spawnInt = gen.nextInt(spawnwindow);
			System.out.println("Spawn: " + spawnInt);
			switch (gen.nextInt(18)) {
			case 0:
			case 1:
			case 2:
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.TRASH));
				break;
			case 3:
			case 4:
			case 5:
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.RECYCLE));
				break;
			case 6:
			case 7:
			case 8:
				hazards.add(new Enemy1(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				break;
			case 9:
			case 10:
			case 11:
				hazards.add(new Enemy2(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				break;
			case 12:
			case 13:
			case 14:
				hazards.add(new Trash(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, Tool.COMPOST));
				break;
			case 15:
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.SPEED));
			case 16:
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.INVINCIBLE));
			case 17:
				hazards.add(new Powerup(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt, PowerupType.CLEAR));
			case 18:
			case 19:
			case 20:
				hazards.add(new Enemy3(FRAMEWIDTH, gen.nextInt(FRAMEHEIGHT) + 1, vel, 0, spawnInt));
				break;
			}
			

		}
		break;
		
		}
	}
		
	

	/**
	 * @param hazards
	 *            hazards are an array of all possible hazards Sets the array
	 *            containing all of the possible hazards
	 */

	public void setpossibleHazards(ArrayList<Hazard> hazards) {
		this.hazards = hazards;
	}

	/**
	 * an array of all possible hazards
	 * 
	 * @return void gets the possible Hazards from the array list
	 */
	public ArrayList<Hazard> getHazardsList() {
		return this.hazards;
	}

	public void moveLeft() {
		for (int i = 0; i < hazard_num; i++) {
			hazards.get(i).move();
		}
	}

	/**
	 * @param hazard
	 *            an array of all possible hazards adds a single hazard to the
	 *            array
	 * 
	 */
	public void addHazard(Hazard hazard) {
		hazards.add(hazard);
	}

	/**
	 * @param index
	 *            index is an int that represents the position we want in the
	 *            array Removes a hazard from the array at the given index
	 */
	public void removeHazard(int index) {
		hazards.remove(index);
	}

	/**
	 * select a random hazard in array
	 */
	public Hazard selectRandom() {
		Random gen = new Random();
		return hazards.get(gen.nextInt(hazards.size()));
	}

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