package controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpMenu extends JPanel{
	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int FRAMEHEIGHT = (int) SCREENSIZE.getHeight();
	public static int FRAMEWIDTH = (int) SCREENSIZE.getWidth();
	JButton back = new JButton();
	
	public HelpMenu(){
		setLayout(null);
		setSize((int) (.75*FRAMEWIDTH), (int) (.5*FRAMEHEIGHT));
		JLabel thumb = new JLabel();
		try{
			Image backp = ImageIO.read(new File("images/back.png"));
			Image backgroundp = ImageIO.read(new File("images/tutorial.png"));
			back.setIcon(new ImageIcon(backp));
			thumb.setIcon(new ImageIcon(backgroundp));
		} catch (Exception ex){
			System.out.print(ex);
		}
		Dimension d = new Dimension();
		d.setSize(54, 20);
		back.setPreferredSize(d);
		add(thumb);
		add(back);
	}
}
