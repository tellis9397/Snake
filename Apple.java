package edu.amherst.Final;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Apple extends JPanel{
	
	int x;
	int y;
	Board board;

	
	Apple(int a, int b){
		x = a;
		y = b;
	}
	
	public int getXC(){	
		return x; 
	}
	
	public int getYC(){
		return y;
	}
}
