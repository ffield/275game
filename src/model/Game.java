package model;


import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Collection;


public class Game implements Serializable{
	Player player;
	Board board;
	PossibleHazards ph;
	Color color;
	int points;
	int level;
	int hazardnum;
	
	public Game(Dimension screenSize){
		System.out.println("Generating game");
		player = new Player(30, 40, 10, 10, 500, 1, 1, 1000, Tool.TRASH, State.NEUTRAL);
		board = new Board();
		hazardnum = 75;
		ph = new PossibleHazards(hazardnum);
		player.setColor(Color.MAGENTA);
        ph.generateHazards(screenSize);
        points = 0;
        level = 1;
	}
	public void onNextLevel(Dimension screenSize){
		Collection<Hazard> c = getPossibleHazards().getHazardsList();
		Hazard last = new Hazard(0, 0, 0, 0, 0, null);
		for(Hazard h: c){
			if(h.getXpos()>last.getXpos()){
				last = h;
			}
		}
		if(last.getXpos()<=0){
			player.setLife(5);
			levelUp();
			ph = new PossibleHazards(hazardnum);
			ph.generateHazards(screenSize);
		}
	}
	public void levelUp(){
		level++;
		hazardnum = hazardnum+10;
		points=points+100;
	}
	public void setPossibleHazards(PossibleHazards ph){
		this.ph=ph;
	}
	public int getHazardNum(){
		return hazardnum;
	}
	public int getPoints() {
		return points;
	}
	public String getLevel(){
		return "Level: "+level;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void getPoint(){
		points++;
	}
	public void stop(){
		player = null;
		ph = null;
	}
	public Player getPlayer(){
		return this.player;
	}
	public void setPlayer(Player player){
		this.player = player;
	}
	public PossibleHazards getPossibleHazards(){
		return this.ph;
	}
	public void setPlayer(PossibleHazards ph){
		this.ph = ph;
	}
	public boolean isGameOver(){
		if(player.isSalOver()){
			System.out.println("Salt");
			return true;
		} else if(player.isSalUnder()){
			System.out.println("Salt");
			return true;
		} else if(player.getLife() <= 0){
			System.out.println("Collision");
			return true;
		}
		return false;
	}
	
	public Board getBoard() {
		return this.board;
	}

	
}
