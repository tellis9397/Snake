package edu.amherst.Final;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Fill extends JPanel{
	
	Color color = Color.BLUE;
	boolean isSnake;
	boolean isApple;
	Board board;
	int x;
	int y;
	
	public Fill(int size, Board parent, int x, int y){
		super();
		board = parent;
		this.x = x;
		this.y = y;
		setPreferredSize (new Dimension(size,size));
		setBackground(Color.BLACK);		
	}

	public void makeSnake(){
		isSnake = true;
	}
	
	public void makeApple(){
		isApple = true;
	}
	
//	public void fillColor(){
//		color = Color.BLUE;
//		if(isSnake = true){
//			color = Color.WHITE;
//		}else if(isApple = true){
//			color = Color.RED;
//		}
//	}
//	
//	public void setColor (Color c) {
//		color = c;
//		repaint();
//	}
//
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
//			g.setColor (color);
//			g.fillRect (30, 30, getSize().width-60, getSize().height-60);
	}
}
