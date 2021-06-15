package interview;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*created an instance for the board frame
		I am creating the window in the boardFrame class and the functionality for the game will be in the SnakeGame 
		class*/
		
		JFrame wframe = new JFrame();
		SnakeGame sg = new SnakeGame();
		wframe.add(sg);
		wframe.setVisible(true);
		wframe.setTitle("SnakeGame");
		wframe.setBackground(Color.black);
		wframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//testing the window
		//wframe.setSize(500,300);
		//call the snakegame class which has all the functionality for the game and add it to the JFrame object
		wframe.pack();
		
	}

}
