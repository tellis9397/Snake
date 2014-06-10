package edu.amherst.Final;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;



public class Board extends JPanel{
	final static int LINE_WIDTH = 2;
	final static int ROWS = 36;
	final static int COLUMNS = 36;
	final static int SQUARE_SIZE = 15;
	public Snake mySnake;
	public Apple myApple;
	public Animator animator;
	char dir = 'w';
	int point = 0;
	Fill[][] pixels = new Fill[ROWS][COLUMNS];
	LineBorder border;
	Playground playground;	

	Board(Component parent) {
		playground = (Playground) parent;
		addKeyListener (new MyKeyListener());
		setOpaque(true);
		setFocusable(true);
		requestFocusInWindow(true);
		
		setLayout (new GridLayout(ROWS, COLUMNS, LINE_WIDTH, LINE_WIDTH));		
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLUMNS; c++){
				pixels[r][c] = new Fill(SQUARE_SIZE, this, r, c);
				add(pixels[r][c]);	
			}


		border = new LineBorder(Color.BLACK, LINE_WIDTH);
		setBorder(border);
		setBackground(Color.BLACK);

		animator = new Animator(this);
		(new Thread(animator)).start();
		mySnake = new Snake(3);
		initial(ROWS/2, COLUMNS/2);		
		start();			
		repaint();
	}

	public void restart(){
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLUMNS; c++){
				pixels[r][c].setBackground(Color.BLACK);
			}
		playground.update();
		mySnake = new Snake(3);
		dir = 'w';
		initial(ROWS/2, COLUMNS/2);
		start();
	}
	
	public void start(){
		animator.startClock();
	}
	
	public void stop(){
		animator.stopClock();
	}
	public void initial(int x, int y){
		int i = 0;

		myApple = new Apple(random(), random());
		while(i<mySnake.size){
			mySnake.snake.addLast(pixels[x][y]);
			pixels[x][y].setBackground(Color.WHITE);
			if(pixels[myApple.x][myApple.y] != pixels[x][y]){
				pixels[myApple.x][myApple.y].setBackground(Color.RED);
			}else{
				myApple = new Apple(random(), random());
			}
			i++;
			x++;
			
		}
	}
	
	public void setDir(char t){
		dir=t;
		System.out.println(dir);
	}
	
	public char getDir(){
		return dir;
	}
	
	public boolean checkLoss(){
		int i = 0;
		for(Fill t : mySnake.snake){
			if(i>0){
				if(pixels[t.x][t.y]==pixels[mySnake.snake.getFirst().x][mySnake.snake.getFirst().y]){
					stop();
					return true;		
				}
			}
			i++;
		}
		return false;
	}
	
	public boolean checkWin(){
		if(mySnake.snake.size() == ROWS*COLUMNS){
			return true;
		}else{
		return false;
		}
	}
	
	public boolean ifGrow(){
		if(pixels[mySnake.snake.getFirst().x][mySnake.snake.getFirst().y]==pixels[myApple.x][myApple.y]){
			myApple = new Apple(random(), random());
			for(int i = 0; i<mySnake.snake.size(); i++){
				Fill t = mySnake.snake.get(i);
				if(pixels[t.x][t.y]==pixels[myApple.x][myApple.y]){
					myApple = new Apple(random(), random());
					i = 0;
				}
			}	
			pixels[myApple.x][myApple.y].setBackground(Color.RED);
			mySnake.snake.addLast(pixels[mySnake.snake.getLast().x][mySnake.snake.getLast().y]);
			playground.points();
			playground.update();
			return true;
		}else{
			return false;
		}
	}

	public void move(){
		try{
		if(dir == 'w'){
			mySnake.snake.addFirst(pixels[mySnake.snake.getFirst().x-1][mySnake.snake.getFirst().y]);
			mySnake.snake.getFirst().setBackground(Color.WHITE);
			if(checkWin()){
				playground.win = true;
				playground.update();
			}
			if(checkLoss()){
				playground.lose = true;
				playground.update();
			}
			if(ifGrow() == false)
				mySnake.snake.getLast().setBackground(Color.BLACK);
				mySnake.snake.removeLast();		
			
		}else if(dir == 's'){
			mySnake.snake.addFirst(pixels[mySnake.snake.getFirst().x+1][mySnake.snake.getFirst().y]);
			mySnake.snake.getFirst().setBackground(Color.WHITE);
			if(checkWin()){
				playground.win = true;
				playground.update();
			}
			if(checkLoss()){
				playground.lose = true;
				playground.update();
			}
			if(ifGrow() == false)
				mySnake.snake.getLast().setBackground(Color.BLACK);
				mySnake.snake.removeLast();		
		}else if(dir == 'a'){
			mySnake.snake.addFirst(pixels[mySnake.snake.getFirst().x][mySnake.snake.getFirst().y-1]);
			mySnake.snake.getFirst().setBackground(Color.WHITE);
			if(checkWin()){
				playground.win = true;
				playground.update();
			}
			if(checkLoss()){
				playground.lose = true;
				playground.update();
			}
			if(ifGrow() == false)
				mySnake.snake.getLast().setBackground(Color.BLACK);
				mySnake.snake.removeLast();		
		}else if(dir == 'd'){
			mySnake.snake.addFirst(pixels[mySnake.snake.getFirst().x][mySnake.snake.getFirst().y+1]);
			mySnake.snake.getFirst().setBackground(Color.WHITE);
			if(checkWin()){
				playground.win = true;
				playground.update();
			}
			if(checkLoss()){
				playground.lose = true;
				playground.update();
			}
			if(ifGrow() == false)
				mySnake.snake.getLast().setBackground(Color.BLACK);
				mySnake.snake.removeLast();		
		}
		}catch(ArrayIndexOutOfBoundsException e){
			stop();
			playground.lose = true;			
			playground.update();
		}

	}

	public int random(){
		return (int)(Math.random()*ROWS);
	}


	class MyKeyListener extends KeyAdapter{

		public void keyPressed(KeyEvent e){
			int c = e.getKeyCode();
			switch (c) {
			case KeyEvent.VK_RIGHT:
				if(dir!='a'){
					dir = 'd';
				}
				break;
			case KeyEvent.VK_LEFT:
				if(dir!='d'){
					dir = 'a';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(dir!='w'){
					dir = 's';
				}
				break;
			case KeyEvent.VK_UP:
				if(dir!='s'){
					dir = 'w';
				}
				break;
			}
		}
	}

	public class Animator implements Runnable{
		private static final int DELAY = 90;

		private long clockTime1;
		private long elapsedTime1;
		private boolean running;
		private Component component;

		public Animator (Component component){
			this.component = component;
		}

		public synchronized void run(){
			try{
				while (true){
					if(running){
						move();
						wait(DELAY);
					}
					else {
						wait();
					}
					component.repaint();
				}
			}
			catch(InterruptedException e){

			}
		}
		public synchronized void startClock(){
			clockTime1 = System.nanoTime();
			running = true;
			notify();
		}

		public synchronized void stopClock() {
			elapsedTime1 = elapsedTime();
			running = false;
		}

		public synchronized long elapsedTime(){
			if(!running) return elapsedTime1;
			else return elapsedTime1 + System.nanoTime() - clockTime1;
		}
	}	
}

