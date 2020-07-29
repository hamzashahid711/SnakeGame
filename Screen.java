package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import package2.Food;
import package2.body;

public class Screen extends JPanel implements Runnable {
	
	static final int WIDTH = 700, HEIGHT =700;
	
	private boolean running = false;
	private Thread thread; 
	private body b;
	private Random r;
	private ArrayList<body> snake; 
	
	private Food food;
	private ArrayList<Food> foods;
	
	private int xCoor = 10, yCoor = 10;
	private int size = 5;
	private boolean right = true, left = false, up = false, down = false;
	private int step = 0;
	private Key key;
	public Screen() {
		
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		r = new Random();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		snake = new ArrayList<body>();
		foods = new ArrayList<Food>();
		start();
	}
	
	
	public void start() {
		running = true;
		thread = new Thread (this, "Game");
		thread.start(); //calls the run method
	}


	
	public void run () {
		while(running) {
			try {
				tick();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint(); //controls paint method
		}
	}
	

	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		for(int i = 0; i < WIDTH/10; i++) { // draws grid vertically 
			g.drawLine(i*10, 0, i * 10, HEIGHT);
		}
		for(int j = 0; j< HEIGHT/10; j ++) { // draws grid horizontally 
			g.drawLine(0, j*10, WIDTH, j*10);
		}
		for(int k = 0; k < snake.size(); k ++) { // however big the snake list is for loops through it drawing the size of the  snake to the screen 
			snake.get(k).draw(g);
		}
		for(int i = 0; i < foods.size(); i++) {
			foods.get(i).draw(g);

		}
		
	}
	public void tick() throws InterruptedException {
		if(snake.size() == 0) { // if snake list is empty adds a new body object to the list
			b = new body(xCoor, yCoor, 10);
			snake.add(b);
		}
		if(xCoor >= 69 || yCoor>= 69 || xCoor<= 0|| yCoor <=0) { // if snake hits edge it stops 
			stop();
		}
		
		if(foods.size() == 0) {
			int xCoor = r.nextInt(69); // adds a x and y on the grid of 70 x 70 
			int yCoor = r.nextInt(69);
			
			food = new Food(xCoor, yCoor, 10);
			foods.add(food);
			
		}
		for(int i = 0; i < foods.size(); i++) {
			if(xCoor == foods.get(i).getxCoor() && yCoor == foods.get(i).getyCoor()) { // checks to see if the snake is on the food
				size ++;
				foods.remove(i);
				i --;
			}

		}
		
		
		
		for(int j = 0; j < snake.size(); j++) {
			if(xCoor == snake.get(j).getxCoor() && yCoor == snake.get(j).getyCoor()) { //checks to see when the snake hits itself 
				if(j != snake.size() -1) {
					stop();
				}
			}
		}
		
		step ++;
		
		if(step > 950000) { // makes it so the snake does not go too fast
			if(right) xCoor ++;
			if(left) xCoor --;
			if(up) yCoor ++;
			if(down) yCoor --;
			step = 0;
			
			
			b = new body(xCoor, yCoor, 10) ;
			snake.add(b);
			
			if(snake.size() > size) { // if the snakes length is greater than the size variable it will remove the end body part to make the snake the correct size 
				
				snake.remove(0);
			}
			
		}
	}
	private void stop() throws InterruptedException {
		running = false;
		//if reference thread was interrupted it stops the game
		thread.join();
		
		
	}
	private class Key implements KeyListener{
		public void keyPressed(KeyEvent e) {
			int key =  e.getKeyCode();
			//if a key is pressed and the direction it is pressed is not the opposite of the direction which is true it'll change direction
			if(key == KeyEvent.VK_RIGHT && !left) {
				up = false;
				down = false;
				right = true;
			}
			if(key == KeyEvent.VK_LEFT && !right) {
				up = false;
				down = false;
				left = true;
			}
			if(key == KeyEvent.VK_DOWN && !up) {
				up = true;
				left = false;
				right = false;
			}
			if(key == KeyEvent.VK_UP && !down) {
				left = false;
				right = false;
				down = true;
			}
		}
		//no code in the methods because I had to include them cause of implementation
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		}
		
	
}
