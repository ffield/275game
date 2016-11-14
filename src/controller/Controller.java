package controller;

import javax.swing.JPanel;
import model.Game;
import model.Hazard;
import model.HazardType;
import model.MovementType;
import model.Player;
import model.PowerupType;
import model.State;
import model.Tool;

import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;

public class Controller extends JPanel {
	private static final long serialVersionUID = 1L;
	KeyFunctions k = new KeyFunctions();
	Game game;
	int count = 0;
	int powerupCount = 0;
	Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public int FRAMEHEIGHT = (int) SCREENSIZE.getHeight();
	public int FRAMEWIDTH = (int) SCREENSIZE.getWidth();
	Color color;

	public Controller() {
		game = new Game(SCREENSIZE);
		count = 0;
		bindKeyWith("y.up", KeyStroke.getKeyStroke("UP"), new VerticalAction(-(this.game.getPlayer().getYvel())));
		bindKeyWith("y.down", KeyStroke.getKeyStroke("DOWN"), new VerticalAction(this.game.getPlayer().getYvel()));
		bindKeyWith("x.left", KeyStroke.getKeyStroke("LEFT"), new HorizontalAction(-(this.game.getPlayer().getXvel())));
		bindKeyWith("x.right", KeyStroke.getKeyStroke("RIGHT"), new HorizontalAction(this.game.getPlayer().getXvel()));
		bindKeyWith("tool.space", KeyStroke.getKeyStroke("SPACE"), new SpaceAction());

	}

	protected void bindKeyWith(String name, KeyStroke keyStroke, Action action) {
		InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getActionMap();
		im.put(keyStroke, name);
		am.put(name, action);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		int xsaltindex = 0;
		int ysaltindex = 0;
		for(int i = 0; i<FRAMEWIDTH; i+=(FRAMEWIDTH/40)){
			System.out.println(i + " " + xsaltindex);
			for(int j = 0; j<FRAMEHEIGHT; j+=(FRAMEHEIGHT/20)){
				double xsaltindexprep = i / ((double) FRAMEWIDTH);
				xsaltindex = (int) (40 * xsaltindexprep);
				double ysaltindexprep = j / ((double) FRAMEHEIGHT);
				ysaltindex = (int) (20 * ysaltindexprep);
				if(game.getBoard().getTile(xsaltindex, ysaltindex) == 1){
					g2d.setColor(Color.BLUE);
				}else{
					g2d.setColor(Color.CYAN);
				}
				g2d.fill(new Rectangle(i, j, FRAMEWIDTH/40, FRAMEHEIGHT/20));
				//ysaltindex++;
			}
			//xsaltindex++;
		}
		Color netcolor = new Color(1);
		switch (game.getPlayer().getTool()) {
		case TRASH:
			netcolor = netcolor.LIGHT_GRAY;
			break;
		case RECYCLE:
			netcolor = netcolor.DARK_GRAY;
			break;
		case COMPOST:
			netcolor = netcolor.GREEN;
			break;
		}
		g2d.setColor(netcolor);
		Rectangle net = game.getPlayer().getBounds();
		net.setLocation(game.getPlayer().getXpos() - 10, game.getPlayer().getYpos() - 10);
		g2d.fill(net);
		g2d.setColor(game.getPlayer().getColor());
		g2d.fill(game.getPlayer().getBounds());
		String salt = "Salt: ";
		String points = "Points: ";
		salt = salt + game.getPlayer().getSalinity();
		points = points + game.getPoints();
		int size = 20;
		Font p = new Font("Broadway", Font.PLAIN, size);
		g2d.setFont(p);
		g2d.setColor(Color.BLACK);
		g2d.drawString(salt, 10, 20);
		g2d.drawString(points, 150, 20);
		for (int i = 0; i < game.getPossibleHazards().getHazardsList().size(); i++) {
			if (game.getPossibleHazards().getHazardsList().get(i).getSpawntime() < count)
				g2d.setColor(game.getPossibleHazards().getHazardsList().get(i).getColor());
			g2d.fill(game.getPossibleHazards().getHazardsList().get(i).getBounds());
		}
		int x = 20;
		for (int i = 0; i < game.getPlayer().getLife(); i++) {
			g2d.drawOval(x, 20, 30, 20);
			x += 40;
		}
		if (game.isGameOver()) {
			int fontSize = 60;
			Font f = new Font("Arial Black", Font.BOLD, fontSize);
			Font s = new Font("Comic Sans MS", Font.PLAIN, 20);
			g2d.setFont(f);
			g2d.setColor(Color.RED);
			g2d.drawString("GAME OVER", (int) (FRAMEWIDTH / 2.5), FRAMEHEIGHT / 2);
			game.stop();
		}
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
							System.out.println("help");
							// clear all enemies off screen
						} else if (collided.getPowerupType().equals(PowerupType.SPEED)) {
							game.getPlayer().SpeedUp();
						}
					} else {
						if (game.getPlayer().getState().equals(State.INVINCIBLE)) {
							if (collided.getypos() > game.getPlayer().getYpos()) {
								collided.setMovementType(MovementType.COLLIDEDDOWN);
							} else if (collided.getypos() <= game.getPlayer().getYpos()) {
								collided.setMovementType(MovementType.COLLIDEDUP);
							}
						}
						else if (collided.getType().equals(HazardType.TRASH)) {
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
		// System.out.print(xsaltindexprep+" ");
		// System.out.println(game.getPlayer().getXpos());
		game.getPlayer().updateSalinity();
	}

	public void update() {
		if (game.getPlayer().getState().equals(State.JUSTHIT)) {
			powerupCount += 1;
			if (game.getPlayer().getColor() == color.MAGENTA)
				game.getPlayer().setColor(color.WHITE);
			else
				game.getPlayer().setColor(color.MAGENTA);
			if (powerupCount ==75) {
				game.getPlayer().setState(State.NEUTRAL);
				game.getPlayer().setColor(color.MAGENTA);
				powerupCount = 0;
			}
		}
		if (game.getPlayer().getState().equals(State.INVINCIBLE)) {
			powerupCount += 1;
			game.getPlayer().setColor(color.YELLOW);
			if (powerupCount == 200) {
				game.getPlayer().setColor(color.MAGENTA);
				game.getPlayer().setState(State.NEUTRAL);
				powerupCount = 0;
			}
		}
		if (game.getPlayer().getState().equals(State.SPEEDUP)) {
			powerupCount += 1;
			game.getPlayer().setColor(color.RED);
			if (powerupCount == 200) {
				game.getPlayer().setXvel(10);
				game.getPlayer().setYvel(10);
				game.getPlayer().setColor(color.MAGENTA);
				game.getPlayer().setState(State.NEUTRAL);
				powerupCount = 0;
			}
		}

		count++;
		// System.out.println("Count: " + count);
		for (int i = 0; i < game.getPossibleHazards().getHazardsList().size(); i++) {
			if (game.getPossibleHazards().getHazardsList().get(i).getSpawntime() < count) {
				game.getPossibleHazards().getHazardsList().get(i).move();
			}
		}
		bindKeyWith("y.up", KeyStroke.getKeyStroke("UP"), new VerticalAction(-(this.game.getPlayer().getYvel())));
		bindKeyWith("y.down", KeyStroke.getKeyStroke("DOWN"), new VerticalAction(this.game.getPlayer().getYvel()));
		bindKeyWith("x.left", KeyStroke.getKeyStroke("LEFT"), new HorizontalAction(-(this.game.getPlayer().getXvel())));
		bindKeyWith("x.right", KeyStroke.getKeyStroke("RIGHT"), new HorizontalAction(this.game.getPlayer().getXvel()));
		repaint();
		saltOnMovement();
		onCollision();
	}

	private int getCount() {
		// TODO Auto-generated method stub
		return this.count;
	}

	private void setCount(int count) {
		// TODO Auto-generated method stub
		this.count = count;
	}

	private void upCount() {
		count++;

	}

	public abstract class MoveAction extends AbstractAction {

		private int delta;

		public MoveAction(int delta) {
			this.delta = delta;
		}

		public int getDelta() {
			return delta;
		}

		protected abstract void applyDelta();

		@Override
		public void actionPerformed(ActionEvent e) {
			applyDelta();
		}

	}

	public abstract class ToolAction extends AbstractAction {

		public ToolAction() {
			// game.getPlayer().SwitchTool();
		}

		protected abstract void switchTool();

		@Override
		public void actionPerformed(ActionEvent e) {
			switchTool();
		}

	}

	public class SpaceAction extends ToolAction {

		public SpaceAction() {
			super();
		}

		@Override
		protected void switchTool() {

			game.getPlayer().SwitchTool();
			System.out.println(game.getPlayer().getTool());

			// repaint();
		}
	}

	public class VerticalAction extends MoveAction {

		public VerticalAction(int delta) {
			super(delta);
		}

		@Override
		protected void applyDelta() {
			int delta = getDelta();
			game.getPlayer().setYpos(game.getPlayer().getYpos() + delta);
			if (game.getPlayer().getYpos() < 0) {
				game.getPlayer().setYpos(0);
			} else if (game.getPlayer().getYpos() + 30 > getHeight()) {
				game.getPlayer().setYpos(getHeight() - 30);
			}
			// repaint();
		}

	}

	public class HorizontalAction extends MoveAction {

		public HorizontalAction(int delta) {
			super(delta);
		}

		@Override
		protected void applyDelta() {
			int delta = getDelta();
			game.getPlayer().setXpos(game.getPlayer().getXpos() + delta);
			if (game.getPlayer().getXpos() < 0) {
				game.getPlayer().setXpos(0);
			} else if (game.getPlayer().getXpos() + 30 > getWidth()) {
				game.getPlayer().setXpos(getWidth() - 30);
			}
			// repaint();
		}

	}

	public Game getGame() {
		return this.game;
	}
}
