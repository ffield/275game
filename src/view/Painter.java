package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Tool;
import model.Wind;

public class Painter extends JPanel{
	
	BufferedImage trash = createImage("images/net.png");
 	BufferedImage recycle = createImage("images/recycle.png");
	BufferedImage compost = createImage("images/compost.png");
	BufferedImage fresh = createImage("images/water_tile.png");
    BufferedImage salt = createImage("images/salt_tile.png");
    BufferedImage heart = createImage("images/fullHeart.png");
    BufferedImage meter = createImage("images/meter.png");
    BufferedImage nowind = createImage("images/neutral_cloud.png");
    BufferedImage northwind = createImage("images/north_cloud.png");
    BufferedImage southwind = createImage("images/south_cloud.png");
	
	ArrayList<String> names;
	ArrayList<Integer> xpos;
	ArrayList<Integer> ypos;
	ArrayList<Integer> xbounds;
	ArrayList<Integer> ybounds;
	int[][] board;
	int lives;
	int level;
	int salinity;
	int score;
	int saltmax;
	Wind wind;
	int FRAMEWIDTH;
	int FRAMEHEIGHT;
	Tool net;
	boolean gameover;
	Map<String, BufferedImage> images = new HashMap<>();
	
	public Painter(){
		File folder = new File("images");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  System.out.println(listOfFiles[i].getName());
		        images.put(listOfFiles[i].getName(), this.getImage(listOfFiles[i].getName()));
		      } 
		    }
	}
	
	public void updateView(ArrayList<String> names, ArrayList<Integer> xpos, ArrayList<Integer> xbounds, ArrayList<Integer> ybounds, ArrayList<Integer> ypos, int[][] board, int lives, int level, int salinity, int score,int saltmax, int FRAMEWIDTH, int FRAMEHEIGHT, Wind wind, Tool net, boolean gameover){
		this.names = names;
		this.xpos = xpos;
		this.ypos = ypos;
		this.board = board;
		this.xbounds = xbounds;
		this.ybounds = ybounds;
		this.lives = lives;
		this.level = level;
		this.salinity = salinity;
		this.score = score;
		this.saltmax=saltmax;
		this.wind = wind;
		this.FRAMEWIDTH = FRAMEWIDTH;
		this.FRAMEHEIGHT = FRAMEHEIGHT;
		this.net = net;
		this.gameover = gameover;
	}
	

	 private BufferedImage createImage(String filename){
	    	BufferedImage bufferedImage;
	    	try {
	    		bufferedImage = ImageIO.read(new File(filename));
	    		return bufferedImage;
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	 }
	 
	 private BufferedImage getImage(String name){
		 BufferedImage image;
		 try {
			 image = ImageIO.read(new File("images/" + name));
			 return image;
     		} 
		 catch (IOException e) {
			e.printStackTrace();
		 }
		 return null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		int xsaltindex = 0;
		int ysaltindex = 0;
//		if (game.getPlayer().getColor() == color.WHITE){
//			g2d.setColor(Color.RED);
//			int xsize = 50;
//			Font xs = new Font("Impact", Font.BOLD, xsize);
//			g2d.setFont(xs);
//			g2d.drawString("X", game.getPlayer().getXpos(), game.getPlayer().getYpos());			
//		}
		for(int i = 0; i<40; i++){
			for(int j = 0; j<20; j++){
//				double xsaltindexprep = i / ((double) FRAMEWIDTH);
//				xsaltindex = (int) (40 * xsaltindexprep);
//				double ysaltindexprep = j / ((double) FRAMEHEIGHT);
//				ysaltindex = (int) (20 * ysaltindexprep);
				if(board[i][j] == 1){
					g2d.setColor(Color.BLUE);
				    g2d.drawImage(salt,i*(FRAMEWIDTH/40), j*(FRAMEHEIGHT/20), FRAMEWIDTH/40,FRAMEHEIGHT/20, null);

					
				}else{
					g2d.setColor(Color.CYAN);
				    g2d.drawImage(fresh,i*(FRAMEWIDTH/40), j*(FRAMEHEIGHT/20), FRAMEWIDTH/40,FRAMEHEIGHT/20, null);

				}

			}

		}
		//Color netcolor = new Color(1);
		switch (net) {
		case TRASH:
	       g2d.drawImage(trash, xpos.get(0)+27, ypos.get(0)-25, xbounds.get(0), ybounds.get(0), null);
		 break;
		case RECYCLE:
			g2d.drawImage(recycle, xpos.get(0)+29, ypos.get(0)-25, xbounds.get(0), ybounds.get(0), null);				
			break;
		case COMPOST:
		    g2d.drawImage(compost, xpos.get(0)+27, ypos.get(0)-25, xbounds.get(0), ybounds.get(0), null);
		
			break;
		}
		
		
	
		
	    g2d.drawImage(meter,-100,-30, 280, 230, null);
		String saltstring = "";
		String scorestring = "Points: ";
		String levelstring = "Level: ";
		String windstring = "";
		saltstring = saltstring + salinity;
		scorestring = scorestring + score;
		levelstring = levelstring + level;
		windstring = windstring + wind;

		Font d = new Font("monospaced", Font.BOLD, 30);
		g2d.setFont(d);
		if (salinity <  saltmax/5 ||salinity > saltmax - saltmax/5 ) {
			g2d.setColor(Color.RED);
		}
		else {
			g2d.setColor(Color.GREEN);
		}

		g2d.drawString(saltstring, 20, 90);
		int size = 20;
		Font p = new Font("comic sans ms", Font.BOLD, size);
		g2d.setFont(p);
		g2d.setColor(Color.BLACK);
		g2d.drawString(scorestring, 100, 25);
		g2d.drawString(levelstring, (int)(FRAMEWIDTH/2), 20);
		switch(wind){
		case NEUTRAL:
			g2d.drawImage(nowind, (int)(FRAMEWIDTH/4)+(FRAMEWIDTH/2), 0, 70, 70, null);
			break;
		case NORTH:
			g2d.drawImage(northwind, (int)(FRAMEWIDTH/4)+(FRAMEWIDTH/2), 0, 70, 70, null);
			break;
		case SOUTH:
			g2d.drawImage(southwind, (int)(FRAMEWIDTH/4)+(FRAMEWIDTH/2), 0, 70, 70, null);
			break;
		}
		g2d.drawString(windstring, (int)(FRAMEWIDTH/4)+(FRAMEWIDTH/2)+70, 40);
		BufferedImage image;
		for (int i = 0; i < xpos.size(); i++) {
			if(images!=null){
			image = images.get(names.get(i));
			g2d.drawImage(image, xpos.get(i), ypos.get(i),xbounds.get(i), ybounds.get(i), null);
			}
			else{
				g2d.drawImage(heart, xpos.get(i), ypos.get(i),xbounds.get(i), ybounds.get(i), null);
			}
		}
		int x = 100;
		for (int i = 0; i < lives; i++) {
			g2d.drawImage(heart,x, 35, 30,25, null);
            x += 40;
		}
		g2d.setColor(Color.BLACK);
		if (gameover) {
			int fontSize = 60;
			Font f = new Font("Arial Black", Font.BOLD, fontSize);
			Font s = new Font("Comic Sans MS", Font.PLAIN, 20);
			g2d.setFont(f);
			g2d.setColor(Color.RED);
			g2d.drawString("GAME OVER", (int) (FRAMEWIDTH / 2.5), FRAMEHEIGHT / 2);
		}
	}
}
