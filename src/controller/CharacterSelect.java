package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CharacterSelect extends JPanel{
	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int FRAMEHEIGHT = (int) SCREENSIZE.getHeight();
	public static int FRAMEWIDTH = (int) SCREENSIZE.getWidth();
	JButton blue = new JButton();
	JButton horseshoe = new JButton();
	JButton oyster = new JButton();
	
	public CharacterSelect(){
		setLayout(new FlowLayout());
		setSize((int) (.33*FRAMEWIDTH), (int) (.5*FRAMEHEIGHT));
		try{
			Image b = ImageIO.read(new File("images/crabbutton.png"));
			Image h = ImageIO.read(new File("images/horsebutton.png"));
			Image o = ImageIO.read(new File("images/oysterbutton.png"));
			System.out.print("did it");
			blue.setIcon(new ImageIcon(b));
			horseshoe.setIcon(new ImageIcon(h));
			oyster.setIcon(new ImageIcon(o));
		} catch (Exception ex){
			System.out.println(ex);
		}
		blue.setBackground(null);
		horseshoe.setBackground(null);
		oyster.setBackground(null);
		add("BlueCrab", blue);
		add("HorseShoe", horseshoe);
		add("Oyster", oyster);
		setBackground(Color.BLUE);
	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		CharacterSelect cs = new CharacterSelect();
		frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);

		frame.add(cs);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
