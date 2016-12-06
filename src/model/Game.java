package model;


import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game Model Object
 * <p>
 * The Game object combines all elements of the game and sets logic for the game. Attributes include:
 * <ul>
 * <li> Player player - The character the user is controlling
 * <li> Board board - The board in the game
 * <li> PossibleHazards ph - all of the possible Hazards in the current level
 * <li> Color color - Color of the player
 * <li> int points - how many points the player has gotten
 * <li> int level - Which level is the game on
 * <li> int hazardnum - How many hazards are there in the current level
 * <li> Wind wind - The wind direction
 * </ul>
 * @author Group 1
 *
 */
public class Game implements Serializable{
	Player player;
	Board board;
	PossibleHazards ph;
	Color color;
	int points;
	int level;
	int hazardnum;
	Wind wind;
	/**
	 * Constructor
	 * @param screenSize The Dimensions of the screen you are playing on.
	 */
	public Game(Dimension screenSize){
		System.out.println("Generating game");
		player = new Player(30, 40, 10, 10, 500, 1, 1, 1000, Tool.TRASH, State.NEUTRAL);
		board = new Board();
		hazardnum = 75;
		wind = Wind.NEUTRAL;
		ph = new PossibleHazards(hazardnum);
		int alpha = 0; // 50% transparent
		Color myColor = new Color(255, 60, 50, alpha);
		player.setColor(myColor);
        points = 0;
        level = 1;
        ph.generateHazards(screenSize, level);
	}
	/**
	 * Constructor
	 * @param screenSize The dimensions of the screen you are playing on (Dimension)
	 * @param imageName The name of the image of the player (String)
	 */
	public Game(Dimension screenSize, String imageName){
		System.out.println("Generating game");
		player = new Player(30, 40, 10, 10, 500, 1, 1, 1000, Tool.TRASH, State.NEUTRAL, imageName);
		board = new Board();
		hazardnum = 75;
		wind = Wind.NEUTRAL;
		ph = new PossibleHazards(hazardnum);
		int alpha = 0; // 50% transparent
		Color myColor = new Color(255, 60, 50, alpha);
		player.setColor(myColor);
        points = 0;
        level = 1;
        ph.generateHazards(screenSize, level);
	}
	/**
	 * This method levels up the game, adds 10 to hazardnum attribute, and adds 100 to points attribute
	 */
	public void levelUp(){
		level++;
		hazardnum = hazardnum+10;
		points=points+100;
	}
	/**
	 * Sets possible hazards attribute to given PossibleHazards
	 * @param ph PossibleHazards
	 */
	public void setPossibleHazards(PossibleHazards ph){
		this.ph=ph;
	}
	/**
	 * Returns the hazardnum attribute
	 * @return hazardnum The number of hazards in the given round
	 */
	public int getHazardNum(){
		return this.hazardnum;
	}
	/**
	 * Returns the number of points the player has accumulated
	 * @return points The number of points in the game
	 */
	public int getPoints() {
		return this.points;
	}
	/**
	 * Returns a string containing the level
	 * @return a string containing the level number
	 */
	public String levelGetter() {
		return "Level: "+level;
	}
	/**
	 * Returns the current level
	 * @return the current level
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * Returns the current wind direction
	 * @return the wind direction
	 */
	public Wind getWind() {
		return wind;
	}
	/**
	 * Sets wind direction to given Wind
	 * @param wind Wind direction (Wind)
	 * @see Wind
	 */
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	/**
	 * Sets points to given integer
	 * @param points integer indicating how many points the player accumulated
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	/**
	 * Adds a point to the points attribute
	 */
	public void getPoint(){
		points++;
	}
	/**
	 * Stops the current game by setting player to null and possiblehazards attribute to null
	 */
	public void stop(){
		player = null;
		ph = null;
	}
	/**
	 * Returns the player
	 * @return player
	 */
	public Player getPlayer(){
		return this.player;
	}
	/**
	 * Sets the player in the game to a given Player
	 * @param player the player you want to set as the new player
	 */
	public void setPlayer(Player player){
		this.player = player;
	}
	/**
	 * Returns possible hazards object attribute
	 * @return
	 */
	public PossibleHazards getPossibleHazards(){
		return this.ph;
	}
	/**
	 * Sets PossibleHazards attribute
	 * @param ph a PossibleHazards object
	 */
	public void setPlayer(PossibleHazards ph){
		this.ph = ph;
	}
	/**
	 * Returns boolean indicating if game is over
	 * @return true if your salinity is outside bounds, or if life is 0, false otherwise
	 */
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
	/**
	 * Returns the board attribute
	 * @return board
	 */
	public Board getBoard() {
		return this.board;
	}
	/**
	 * Returns ArrayList of possible Hazards' x positions
	 * @return xpos (ArrayList of all hazards' x positions)
	 */
	public ArrayList<Integer> makeXpos(){
		ArrayList<Integer> xpos = new ArrayList<Integer>();
		xpos.add(player.getXpos());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			xpos.add(ph.getHazardsList().get(i).getXpos());
		}
		return xpos;	
	}
	/**
	 * Returns ArrayList of possible Hazards' y positions
	 * @return ypos (ArrayList of all hazards' y positions)
	 */
	public ArrayList<Integer> makeYpos(){
		ArrayList<Integer> ypos = new ArrayList<Integer>();
		ypos.add(player.getYpos());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			ypos.add(ph.getHazardsList().get(i).getYpos());
		}
		return ypos;	
	}
	/**
	 * Returns ArrayList of possible Hazards' x bounds
	 * @return xbounds (ArrayList of all hazards' x bounds)
	 */
	public ArrayList<Integer> makeXbounds(){
		ArrayList<Integer> xbounds = new ArrayList<Integer>();
		xbounds.add((int) player.getBounds().getWidth());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			xbounds.add((int) ph.getHazardsList().get(i).getBounds().getWidth());
		}
		return xbounds;	
	}
	/**
	 * Returns ArrayList of possible Hazards' y bounds
	 * @return ybounds (ArrayList of all hazards' y bounds)
	 */
	public ArrayList<Integer> makeYbounds(){
		ArrayList<Integer> ybounds = new ArrayList<Integer>();
		ybounds.add((int) player.getBounds().getHeight());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			ybounds.add((int) ph.getHazardsList().get(i).getBounds().getHeight());
		}
		return ybounds;	
	}
	/**
	 * Returns ArrayList of possible Hazards' image names
	 * @return names (ArrayList of all hazards' image names)
	 */
	public ArrayList<String> makeNames(){
		ArrayList<String> names = new ArrayList<String>();
		names.add(player.getImageType());
		for(int i = 0; i<ph.getHazardsList().size(); i++){
			names.add(ph.getHazardsList().get(i).getImageType());
		}
		return names;	
	}
	/**
	 * This method determines if it's time for the next level, and then levels you up and generates new hazards
	 * @param screenSize Dimensions of screen
	 */
	public void onNextLevel(Dimension screenSize) {
		ArrayList<Hazard> c = getPossibleHazards().getHazardsList();
		if (c.size() == 0) {
			levelUp();
			setPossibleHazards(new PossibleHazards(getHazardNum()));
			getPossibleHazards().generateHazards(screenSize, getLevel());
		}
	}
	/**
	 * This method deletes the hazards as they touch the end of the screen
	 * @param screenSize Dimension of screen
	 */
	public void onOffScreen(Dimension screenSize) {
		int FRAMEHEIGHT = (int) screenSize.getHeight();
		ArrayList<Hazard> c = getPossibleHazards().getHazardsList();
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getXpos() <= 0 || c.get(i).getYpos() <= -30 || c.get(i).getYpos() >= FRAMEHEIGHT + 30) {
				getPossibleHazards().removeHazard(i);
			}
		}
	}
	/**
	 * Changes wind direction randomly
	 */
	public void changeWind() {
		Random gen = new Random();
		int x = gen.nextInt(3);
		switch(x){
		case 0:
			setWind(Wind.NEUTRAL);
			break;
		case 1:
			setWind(Wind.NORTH);
			break;
		case 2:
			setWind(Wind.SOUTH);
			break;
		}
	}
	/**
	 * Determines how salinity affects player
	 * @param screenSize Dimension of screen
	 */
	public void saltOnMovement(Dimension screenSize) {
		int FRAMEWIDTH = (int) screenSize.getWidth();
		int FRAMEHEIGHT = (int) screenSize.getHeight();
		double xsaltindexprep = getPlayer().getXpos() / ((double) FRAMEWIDTH);
		int xsaltindex = (int) (40 * xsaltindexprep);
		double ysaltindexprep = getPlayer().getYpos() / ((double) FRAMEHEIGHT);
		int ysaltindex = (int) (20 * ysaltindexprep);
		getPlayer().setSaldelta(getBoard().getTile(xsaltindex, ysaltindex));
		getPlayer().updateSalinity();
	}
	/**
	 * This method handles how wind affects the board
	 */
	public void handleWind() {
		Wind wind = getWind();
		switch(wind){
		case NEUTRAL:
			break;
		case NORTH:
			getBoard().northWind();
			break;
		case SOUTH:
			getBoard().southWind();
			break;
		}
	}
}
