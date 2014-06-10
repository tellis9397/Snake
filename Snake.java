package edu.amherst.Final;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.*;

public class Snake extends JFrame {
	  int size;
	  char dir = 'w';
	  Board board;
	  Apple a;
	  LinkedList<Fill> snake = new LinkedList<Fill>();
	  
	  
		public Snake(int s){
			size = s;
			
		}

				
		public void setDir(char t){
			dir=t;
			System.out.println(dir);
		}
}
