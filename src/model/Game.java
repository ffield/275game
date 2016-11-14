package model;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;


public class Game implements Serializable{
	Player player;
	Board board;
	PossibleHazards ph;
	Color color;
	int points;
	public Game(Dimension screenSize){
		System.out.println("Generating game");
		player = new Player(30, 40, 10, 10, 500, 1, 1, 1000, Tool.TRASH, State.NEUTRAL);
		board =  new Board();
		ph = new PossibleHazards(75);
		player.setColor(color.MAGENTA);
        ph.generateHazards(screenSize);
        points = 0;
	}
	public int getPoints() {
		return points;
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
	
	public  void setPlayer(Player player){
		this.player = player;
	}
	
	public PossibleHazards getPossibleHazards(){
		return this.ph;
	}
	
	public  void setPlayer(PossibleHazards ph){
		this.ph = ph;
	}

	public boolean isGameOver(){
		if(player.isSalOver()){
			System.out.println("Salt");
			return true;
		}
		else if(player.isSalUnder()){
			System.out.println("Salt");
			return true;
		}
		else if(player.getLife()<=0){
			System.out.println("Collision");
			return true;
		}
		return false;
	}
	
	public Board getBoard() {
		return this.board;
	}

	

}
