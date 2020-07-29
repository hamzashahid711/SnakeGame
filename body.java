package package2;

import java.awt.Color;
import java.awt.Graphics;

public class body {

	private int xcoor, ycoor, height, width; 
	
	public body(int xcoor, int ycoor, int tile) {
		this.xcoor = xcoor;
		this.ycoor = ycoor;
		width = tile;
		height = tile;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xcoor* width , ycoor* getHeight(), width, getHeight());
		g.setColor(Color.BLUE);
		g.fillRect(xcoor* width+2, ycoor* getHeight()+2, width-4, getHeight()-4);
		
		
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getxCoor() {
		return xcoor;
	}
	public void setxCoor(int xCoor) {
		this.xcoor = xCoor;
	}
	public int getyCoor() {
		return ycoor;
	}
	public void setyCoor(int yCoor) {
		this.ycoor = yCoor;
	}
}
