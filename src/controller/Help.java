package controller;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Help extends JPanel{
	static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int FRAMEHEIGHT = (int) SCREENSIZE.getHeight();
	public static int FRAMEWIDTH = (int) SCREENSIZE.getWidth();
	JLabel background = new JLabel();
	JButton back = new JButton();
	JFrame frame;
	
	public void setFrame(JFrame aframe){
		this.frame = aframe;
	}
	public Help(JFrame aframe){
		frame = aframe;
		frame.setSize((int) (.8*FRAMEWIDTH), (int) (.8*FRAMEHEIGHT));
		try {
//			background = new JLabel(new ImageIcon(ImageIO.read(new File("Images/tutorial.png"))));
			back = new JButton(new ImageIcon(ImageIO.read(new File("Images/back.png"))));
		} catch (IOException e) {
			System.out.println(e);
		}
//		background.setLayout(new FlowLayout(FlowLayout.RIGHT));
//		Container cp = frame.getContentPane();
//		//frame.setContentPane(background);
		Help h = this;
//		back.setBackground(null);
		back.setBounds((int)(.47*FRAMEWIDTH), (int)(.39*FRAMEHEIGHT), 200, 80);
		back.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				//frame.setContentPane(cp);
//				h.setVisible(false);
				StartMenu sm = new StartMenu(frame);
				frame.add(sm);
				frame.remove(h);
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		background.add(back);
		back.setBounds((int)(.6*FRAMEWIDTH), (int)(.6*FRAMEHEIGHT), 100, 100);
		//frame.add(background);
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage backg = null;
        BufferedImage back = null;
        try {
			backg = ImageIO.read(new File("Images/tutorial.png"));
			back = ImageIO.read(new File("Images/back.png"));
		} catch (IOException e) {
			System.out.println(e);
		}
        g.drawImage(backg, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(back, (int)(.47*FRAMEWIDTH), (int)(.39*FRAMEHEIGHT), 200, 80, this);
        this.back.setBounds((int)(.47*FRAMEWIDTH), (int)(.39*FRAMEHEIGHT), 200, 80);
    }
}
