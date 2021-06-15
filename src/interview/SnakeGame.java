package interview;
import java.awt.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JPanel;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
	
	//we need the actionListener
	//methods I need, to start the game, to stop the game, a method to paint components to draw the food and the snake
	//given specifics for the question, the game is played in a rectangle, food is placed on a random grid
	//when the snake encounters the food it grows by 1 grid, game end when it hits its own body or the sides/top and bottom of the window
	//direction it goes up down right and left(we can use keypressed by implementing keyListener? and it can either be string or char)
	//if enough time left add high score
	public static final int w=700;
	public static final int h=700;
	public static final int d=10; //grid size
	//String direction ="Up";
	char dir='U';
	 //to see if the game is running, initialized false 
	boolean running=false;
	Random r;
	Timer t;
//	boolean up=false;
//	boolean down=false;
//	boolean right=false;
//	boolean left=false;
	
	
	//the coordinates for the snake body parts in x and y 
	final int snakex[] = new int[700];
	final int snakey[] = new int[700];
	int snake=5;//initial body parts of snake increments by one as snake eats food
	int sfood; //initial food eaten by the snake 
	// the food is placed on a random grid so we need an x and y coordinates
	int eggx, eggy;
	int score;
	int hscore;
	
	public SnakeGame() {
		//Constructor for the game
		r=new Random();
		this.setPreferredSize(new Dimension(w,h));
		this.addKeyListener(this);
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		start();
				
	}
	//method to start the game  
	public void start() {
		egg();
		running=true;
		t= new Timer(120,this); // the game moves at 120 millisecond
		t.start();
	}
	 public void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 draw(g);
		 
	 }
	 //a method to draw the food as well as the snake
	 public void draw(Graphics e) {
		/* if(running) {*/
		 //drawing the egg
		 e.setColor(Color.yellow);
		 e.fillOval(eggx, eggy, d, d); //since the egg shouldn't be bigger than the grid we set the cooridnates to draw at x,y and pass in the dimension
		 //drawing the head of the snake
		 for(int i=0; i<snake; i++) {
			 //this is the head of the snake
			 if(i==0) {
				 e.setColor(Color.green);
				 e.fillRect(snakex[i], snakey[i], d, d);
			 }
			 //if it doesn't equal 0 then it's the body
			 else {
				 e.setColor(Color.blue);
				 e.fillRect(snakex[i], snakey[i], d, d);
			 }
	
		 }
//		 } else {
		 //calls on the highscore method and displays the highest score and the gameover
//		 highScore(food);
//			 gameover(e);
//		 }
		 }
	 //}
 	//a method to populate new food for the snake to eat on a random place on the grid
	 public void egg() {
			//this will place the food on a random grid every time it gets eaten by the snake
			eggy= r.nextInt((int)(h/d))*d;
			eggx= r.nextInt((int)(w/d))*d;
			
		}

	//method to actually play the game
		 public void play() {
			 //the snake moves one body part/one grid out of time?
			 for(int s= snake; s>0; s--) {
				 snakex[s]=snakex[s-1];
				 snakey[s]=snakey[s-1];
			 }
			 switch(dir) {
			 case 'R':
				 snakex[0]=snakex[0]+ d;
				 break;
			 case 'L':
				 snakex[0]=snakex[0]- d;
				 break;
			 case 'U':
				 snakey[0]=snakey[0]- d;
				 break;
			 case 'D':
				 snakey[0]=snakey[0]+ d;
				 break;
			 }			
	}
	
	public void growBody() {
		if((snakex[0]== eggx) && snakey[0]==eggy) {
			snake++;
			//then populate a new food to keep the game going
			//keep the score of the food eaten
			sfood++;
			egg();
		}
		
	}
	//method to check if the snake hit the borders or it's own body
	public void hitBody() {
		for(int s= snake; s>0; s--) {
			//if it hits its body then the game is over
				if((snakex[0]==snakex[s]) && (snakey[0]== snakey[s])) 
			{
				running=false;
			}
		}
			//tb
			if(snakey[0]<0) {
				running=false;
			}
			//lb
			if(snakex[0]<0) {
				running=false;
			}
			//bb
			if(snakey[0]> h) {
				running=false;
			}//rb
			if(snakex[0]>w) {
				running=false;
			}
			
			if(!running) {
				t.stop();
			}
			
			
		}
		
	
	//method for growing the body by one every time a snake eats the food
	
	//method to print game over
	public void gameover(Graphics g) {
		g.setColor(Color.red);
		g.drawString("Game Over", w-w/2, h-h/2);
		g.setFont(getFont());
		
	}
	//method to save the highest score
	public void highScore(Graphics g) {
		
		if(score> hscore) {
			hscore=score;
			
		}
		g.setColor(Color.green);
		g.drawString("Score: " + sfood, w-w/4, h-h/4);
		
	}
	 @Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		 if(running) {
			 play();
			 growBody();
			 hitBody();
		 }
		 repaint();
			
		}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//if you press the up arrow then the snake moves up a grid same for down,left and right
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(dir !='D') {
				dir='U';
				
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(dir !='L') {
				dir='R';
				
			}
			break;
		case KeyEvent.VK_DOWN:
			if(dir !='U') {
				dir='D';
				
			}
			break;
		case KeyEvent.VK_LEFT:
			if(dir !='R') {
				dir='L';
				
			}
			break;
		}
		//int keyCode= e.getKeyCode();
		
//		if(keyCode == KeyEvent.VK_U) {
//			if(up=true) {
//				down=false;
//				right=false;
//				left=false;
//				
//			}
//			
//			
//		}
//		if(keyCode == KeyEvent.VK_D) {
//			
//		}
//		if(keyCode == KeyEvent.VK_L) {
//			
//		}
//		if(keyCode == KeyEvent.VK_R) {
//	
//		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
