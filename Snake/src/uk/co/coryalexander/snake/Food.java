package uk.co.coryalexander.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Food {

	int x, y;
	Screen screen;
	
	public Food(Screen screen) {
		this.screen = screen;
		this.x = (new Random().nextInt(((screen.getSize().width-200)/10)-1)+1) * 10;
		this.y = (new Random().nextInt(((screen.getSize().height-100)/10)-1)+1) * 10;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 10, 10);
	}
	
	public void changeLoc() {
		this.x = (new Random().nextInt(((screen.getSize().width-200)/10)-1)+1) * 10;
		this.y = (new Random().nextInt(((screen.getSize().height-100)/10)-1)+1) * 10;
		System.out.println("X: " + x);
		System.out.println("Y: " + y );
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
