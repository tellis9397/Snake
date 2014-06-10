package edu.amherst.Final;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Playground extends JPanel  implements ActionListener{
	JLabel label;
	JLabel score;
	JButton restart;
	Board board;
	Game game;
	int point = 0;
	boolean lose = false;
	boolean win = false;
	
	Playground(Game g){
		super(new FlowLayout(FlowLayout.CENTER, 50, 50));
		label = new JLabel("Use the Arrow Keys!");	
		score = new JLabel("SCORE: " + (points()-1));
		restart = new JButton("Click to Restart!");
		restart.setFocusable(false);
		board = new Board(this);
		add(score);
		add(label);
		add(board);
		add(restart);

		restart.addActionListener(this);
		game = g;
	}


	
	public int points(){
		point++;
		return point;
	}

	public void update(){
		score.setText("SCORE: " + (points()/2));
		if(lose){
			label.setText("YOU LOSE!");
		}
		if(win){
			label.setText("HOLY SH*T YOU WIN");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(restart)){ 
			lose = false;
			win = false;
			point = 0;
			label.setText("Use the Arrow Keys!");
			board.restart();		

		}
	}
}
