package controller;

import javax.swing.JPanel;
import view.Painter;
import model.Game;
import model.Hazard;
import model.HazardType;
import model.MovementType;
import model.Player;
import model.PossibleHazards;
import model.PowerupType;
import model.State;
import model.Tool;
import model.Wind;

import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;

public class Controller extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	KeyFunctions k = new KeyFunctions();
	Game game;
	String name;
	Painter painter;
	int count = 0;
	int powerupCount = 0;
	Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public int FRAMEHEIGHT = (int) SCREENSIZE.getHeight();
	public int FRAMEWIDTH = (int) SCREENSIZE.getWidth();
	Color color;
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	int alpha = 0; // makes transparent
	Color myColor = new Color(255, 60, 50, alpha);
	// String sourceimage = ("images/net.png");
	// BufferedImage img = createImage(sourceimage);
	// BufferedImage recycle = createImage("images/recycle.png");
	// BufferedImage compost = createImage("images/compost.png");
	// BufferedImage player = createImage("images/bluecrab_0.png");
	// BufferedImage fresh = createImage("images/water_tile.png");
	// BufferedImage salt = createImage("images/salt_tile.png");
	// BufferedImage heart = createImage("images/fullHeart.png");

	public Controller() {
		game = new Game(SCREENSIZE);
		painter = new Painter();
		count = 0;
		addKeyListener(this);
		name = game.getPlayer().getImageType();
		name = name.replace(".png", "");
		up = false;
		down = false;
		left = false;
		right = false;
	}

	public Controller(String imageName) {
		game = new Game(SCREENSIZE, imageName);
		painter = new Painter();
		count = 0;
		addKeyListener(this);
		name = game.getPlayer().getImageType();
		name = name.replace(".png", "");
		up = false;
		down = false;
		left = false;
		right = false;
	}

	protected void bindKeyWith(String name, KeyStroke keyStroke, Action action) {
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		im.put(keyStroke, name);
		am.put(name, action);
	}

	// private BufferedImage createImage(String filename){
	// BufferedImage bufferedImage;
	// try {
	// bufferedImage = ImageIO.read(new File(filename));
	// return bufferedImage;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return null;}

	@Override
	public void paint(Graphics g) {
		painter.paint(g);
	}

	public void onCollision() {
		Rectangle playerr = game.getPlayer().getBounds();
		Rectangle hazardr;
		Hazard collided;
		if (game.getPlayer().getState().equals(State.JUSTHIT)) {

		} else {
			for (int i = 0; i < game.getPossibleHazards().getHazardsList().size(); i++) {
				hazardr = game.getPossibleHazards().getHazardsList().get(i).getBounds();
				collided = game.getPossibleHazards().getHazardsList().get(i);
				if (playerr.intersects(hazardr)) {
					if (collided.getType().equals(HazardType.POWERUP)) {
						game.getPossibleHazards().removeHazard(i);
						if (collided.getPowerupType().equals(PowerupType.INVINCIBLE)) {
							game.getPlayer().Invincibility();
						} else if (collided.getPowerupType().equals(PowerupType.CLEAR)) {
							game.getPossibleHazards().clearEnemies(SCREENSIZE);
							// System.out.println("help");
							// clear all enemies off screen
						} else if (collided.getPowerupType().equals(PowerupType.SPEED)) {
							game.getPlayer().SpeedUp();
						} else if (collided.getPowerupType().equals(PowerupType.ADDLIFE)) {
							game.getPlayer().setLife(game.getPlayer().getLife() + 1);
						}
					} else {
						if (game.getPlayer().getState().equals(State.INVINCIBLE)) {
							if (collided.getypos() > game.getPlayer().getYpos()) {
								collided.setMovementType(MovementType.COLLIDEDDOWN);
							} else if (collided.getypos() <= game.getPlayer().getYpos()) {
								collided.setMovementType(MovementType.COLLIDEDUP);
							}
						} else if (collided.getType().equals(HazardType.TRASH)) {
							if (game.getPlayer().getTool().equals(collided.getToolType())) {
								game.getPossibleHazards().removeHazard(i);
								game.getPoint();
							} else {
								System.out.println("One less life");
								game.getPossibleHazards().removeHazard(i);
								game.getPlayer().LoseLife();
							}
						} else {
							System.out.println("One less life");
							game.getPossibleHazards().removeHazard(i);
							game.getPlayer().LoseLife();
						}
					}
				}
			}
		}
	}

	public void saltOnMovement() {
		double xsaltindexprep = game.getPlayer().getXpos() / ((double) FRAMEWIDTH);
		int xsaltindex = (int) (40 * xsaltindexprep);
		double ysaltindexprep = game.getPlayer().getYpos() / ((double) FRAMEHEIGHT);
		int ysaltindex = (int) (20 * ysaltindexprep);
		game.getPlayer().setSaldelta(game.getBoard().getTile(xsaltindex, ysaltindex));
		game.getPlayer().updateSalinity();
	}

	public void update() {
		String empty = "empty";
		String invincible = "invincible_"+name;
		String speedy = "speedy_"+name;
		System.out.println(name + " : " +game.getPlayer().getImageType());
		if (game.getPlayer().getState().equals(State.JUSTHIT)) {
			powerupCount += 1;
			if (game.getPlayer().getImageType().replace(".png", "").equals(name))
				game.getPlayer().setImageType(empty);
			else
				game.getPlayer().setImageType(name);
			if (powerupCount >= 75) {
				game.getPlayer().setState(State.NEUTRAL);
				game.getPlayer().setImageType(name);
				powerupCount = 0;
			}
		}
		if (game.getPlayer().getState().equals(State.INVINCIBLE)) {
			powerupCount += 1;
			if (game.getPlayer().getImageType().replace(".png", "").equals(name))
				game.getPlayer().setImageType(invincible);
			else
				game.getPlayer().setImageType(name);
			if (powerupCount >= 200) {
				game.getPlayer().setState(State.NEUTRAL);
				game.getPlayer().setImageType(name);
				powerupCount = 0;
			}
		}
		if (game.getPlayer().getState().equals(State.SPEEDUP)) {
			powerupCount += 1;
			if (game.getPlayer().getImageType().replace(".png", "").equals(name))
				game.getPlayer().setImageType(speedy);
			else
				game.getPlayer().setImageType(name);
			if (powerupCount >= 200) {
				game.getPlayer().setXvel(10);
				game.getPlayer().setYvel(10);
				game.getPlayer().setImageType(name);
				game.getPlayer().setState(State.NEUTRAL);
				powerupCount = 0;
			}
		}
		count++;
		for (int i = 0; i < game.getPossibleHazards().getHazardsList().size(); i++) {
			if (game.getPossibleHazards().getHazardsList().get(i).getSpawntime() < count) {
				game.getPossibleHazards().getHazardsList().get(i).move();
				game.getPossibleHazards().getHazardsList().get(i).setPlayery(game.getPlayer().getYpos());
			}
		}
		keyUpdate();
		painter.updateView(game.makeNames(), game.makeXpos(), game.makeXbounds(), game.makeYbounds(), game.makeYpos(),
				game.getBoard().getArr(), game.getPlayer().getLife(), game.getLevel(), game.getPlayer().getSalinity(),
				game.getPoints(), game.getPlayer().getSalmax(), FRAMEWIDTH, FRAMEHEIGHT, game.getWind(), game.getPlayer().getTool(),
				game.isGameOver());
		repaint();
		if (game.isGameOver())
			game.stop();
		if(count%300 == 0)
			changeWind();
		if(count%100 == 0)
			handleWind();
		saltOnMovement();
		onCollision();
		onOffScreen();
		onNextLevel(SCREENSIZE);
	}

		
	public void changeWind() {
		Random gen = new Random();
		int x = gen.nextInt(3);
		switch(x){
		case 0:
			game.setWind(Wind.NEUTRAL);
			break;
		case 1:
			game.setWind(Wind.NORTH);
			break;
		case 2:
			game.setWind(Wind.SOUTH);
			break;
		}
	}
	
	public void handleWind() {
		Wind wind = game.getWind();
		System.out.println(wind);
		switch(wind){
		case NEUTRAL:
			break;
		case NORTH:
			game.getBoard().northWind();
			break;
		case SOUTH:
			game.getBoard().southWind();
			break;
		}
	}
	
	public void onOffScreen() {
		ArrayList<Hazard> c = game.getPossibleHazards().getHazardsList();
		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getXpos() <= 0 || c.get(i).getYpos() <= -30 || c.get(i).getYpos() >= FRAMEHEIGHT + 30) {
				game.getPossibleHazards().removeHazard(i);
			}
		}
	}

	public void onNextLevel(Dimension screenSize) {
		ArrayList<Hazard> c = game.getPossibleHazards().getHazardsList();
		System.out.println("Size: " + c.size());
		if (c.size() == 0) {
			count = 0;
			game.levelUp();
			game.setPossibleHazards(new PossibleHazards(game.getHazardNum()));
			game.getPossibleHazards().generateHazards(screenSize, game.getLevel());
		}
	}

	public Game getGame() {
		return this.game;
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	public void keyUpdate() {
		if (down) {

			game.getPlayer().setYpos(game.getPlayer().getYpos() + game.getPlayer().getYvel());
			if (game.getPlayer().getYpos() < 0) {
				game.getPlayer().setYpos(0);
			} else if (game.getPlayer().getYpos() + 30 > getHeight()) {
				game.getPlayer().setYpos(getHeight() - 30);
			}
		}
		if (up) {
			game.getPlayer().setYpos(game.getPlayer().getYpos() - game.getPlayer().getYvel());
			if (game.getPlayer().getYpos() < 0) {
				game.getPlayer().setYpos(0);
			} else if (game.getPlayer().getYpos() + 30 > getHeight()) {
				game.getPlayer().setYpos(getHeight() - 30);
			}
		}
		if (left) {

			game.getPlayer().setXpos(game.getPlayer().getXpos() - game.getPlayer().getXvel());
			if (game.getPlayer().getXpos() < 0) {
				game.getPlayer().setXpos(0);
			} else if (game.getPlayer().getXpos() + 30 > getWidth()) {
				game.getPlayer().setXpos(getWidth() - 30);
			}
		}

		if (right) {

			game.getPlayer().setXpos(game.getPlayer().getXpos() + game.getPlayer().getXvel());
			if (game.getPlayer().getXpos() < 0) {
				game.getPlayer().setXpos(0);
			} else if (game.getPlayer().getXpos() + 30 > getWidth()) {
				game.getPlayer().setXpos(getWidth() - 30);
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_DOWN) {
			down = true; // removing velx = 0 allows us to go vertically and
							// horizontlly at the same time

		}
		if (code == KeyEvent.VK_UP) {
			up = true;

		}
		if (code == KeyEvent.VK_LEFT) {

			left = true;
		}

		if (code == KeyEvent.VK_RIGHT) {

			right = true;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_DOWN) {
			down = false; // removing velx = 0 allows us to go vertically and
							// horizontlly at the same time

		}
		if (code == KeyEvent.VK_UP) {
			up = false;

		}
		if (code == KeyEvent.VK_LEFT) {

			left = false;
		}

		if (code == KeyEvent.VK_RIGHT) {

			right = false;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == ' ') {
			game.getPlayer().SwitchTool();
		}

	}
}
