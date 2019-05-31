package uk.co.coryalexander.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	Snake snake;
	long start;
	
	public KeyHandler(Snake s) {
		snake = s;
		start = 100;
	}
	
	public void keyPressed(KeyEvent e) {
		
		long now;
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_RIGHT:
			if((snake.getSpeed() != 3) || (snake.getSnakeList().size() == 1)) {
				now = System.currentTimeMillis();
				if(now-start < 50) {
					start = now;
					break;
				} else {
				snake.setSpeed(1);
				start = now;
				}
			}
			break;
		case KeyEvent.VK_DOWN:
			if((snake.getSpeed() != 4) || (snake.getSnakeList().size() == 1)){
				now = System.currentTimeMillis();
				if(now-start < 50) {
					start = now;
					break;
				} else {
				snake.setSpeed(2);
				start = now;
				}
			}
			break;
		case KeyEvent.VK_LEFT:
			if((snake.getSpeed() != 1) || (snake.getSnakeList().size() == 1)) {
				now = System.currentTimeMillis();
				if(now-start < 50) {
					start = now;
					break;
				} else {
				snake.setSpeed(3);
				start = now;
				}
			}
			break;
		case KeyEvent.VK_UP:
			if((snake.getSpeed() != 2) || (snake.getSnakeList().size() == 1)) {
				now = System.currentTimeMillis();
				if(now-start < 50) {
					start = now;
					break;
				} else {
				snake.setSpeed(4);
				start = now;
				}
			}
			break;
		}
	}

	
	public void keyReleased(KeyEvent e) {
		
	}

	
	public void keyTyped(KeyEvent e) {
		
	}

}
