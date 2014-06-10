package edu.amherst.Final;



import javax.swing.*;


public class Game extends JFrame{
	Game() {
		super("Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new Playground(this));
		pack();
		setVisible(true);
	}

	public static void main(String[] args){
		new Game();
	}
	
	public void restart(){	
		
		setContentPane(new Playground(this));
		//pack();
		setVisible(true);	
}
}
