package model;


import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;


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
		int alpha = 0; // 50% transparent
		Color myColor = new Color(255, 60, 50, alpha);
		player.setColor(myColor);
        points = 0;
        level = 1;
        ph.generateHazards(screenSize, level);
	}
	
	public Game(Dimension screenSize, String imageName){
		System.out.println("Generating game");
		player = new Player(30, 40, 10, 10, 500, 1, 1, 1000, Tool.TRASH, State.NEUTRAL, imageName);
		board = new Board();
		hazardnum = 75;
		ph = new PossibleHazards(hazardnum);
		int alpha = 0; // 50% transparent
		Color myColor = new Color(255, 60, 50, alpha);
		player.setColor(myColor);
        points = 0;
        level = 1;
        ph.generateHazards(screenSize, level);
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
		return this.hazardnum;
	}
	public int getPoints() {
		return this.points;
	}
	
	public String levelGetter() {
		return "Level: "+level;
	}
	
	public int getLevel(){
		return level;
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
			//System.out.println("Salt");
			return true;
		} else if(player.isSalUnder()){
			//System.out.println("Salt");
			return true;
		} else if(player.getLife() <= 0){
			//System.out.println("Collision");
			return true;
		}
		return false;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public ArrayList<Integer> makeXpos(){
		ArrayList<Integer> xpos = new ArrayList<Integer>();
		xpos.add(player.getXpos());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			xpos.add(ph.getHazardsList().get(i).getXpos());
		}
		return xpos;	
	}
	
	public ArrayList<Integer> makeYpos(){
		ArrayList<Integer> ypos = new ArrayList<Integer>();
		ypos.add(player.getYpos());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			ypos.add(ph.getHazardsList().get(i).getYpos());
		}
		return ypos;	
	}
	
	public ArrayList<Integer> makeXbounds(){
		ArrayList<Integer> xbounds = new ArrayList<Integer>();
		xbounds.add((int) player.getBounds().getWidth());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			xbounds.add((int) ph.getHazardsList().get(i).getBounds().getWidth());
		}
		return xbounds;	
	}
	
	public ArrayList<Integer> makeYbounds(){
		ArrayList<Integer> ybounds = new ArrayList<Integer>();
		ybounds.add((int) player.getBounds().getHeight());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			ybounds.add((int) ph.getHazardsList().get(i).getBounds().getHeight());
		}
		return ybounds;	
	}
	
	public ArrayList<String> makeNames(){
		ArrayList<String> names = new ArrayList<String>();
		names.add(player.getImageType());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			names.add(ph.getHazardsList().get(i).getImageType());
		}
		return names;	
	}

	
}
