package uk.co.coryalexander.snake;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnakePart {

	private int x;
	private int y;
	
	public SnakePart(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 10, 10);
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 10, 10);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
