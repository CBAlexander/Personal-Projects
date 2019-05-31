package uk.co.coryalexander.snake;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Snake {

	private int length;
	private int speed;
	private int HeadLocX;
	private int HeadLocY;
	private ArrayList<SnakePart> snakeList = new ArrayList<SnakePart>();
	private Screen screen;
	
	public Snake(Screen screen) {
		this.screen = screen;
		snakeList.add(new SnakePart(screen.getSize().width/2, screen.getSize().height/2));
	}
	
	public void render(Graphics2D g) {
		for(SnakePart sp : snakeList) {
			sp.render(g);
		}
	}
	
	public void gameOver() {
		try {
			FileReader fr = new FileReader("/res/hs.txt");
			BufferedReader br = new BufferedReader(fr);
			int highScore = Integer.parseInt(br.readLine());
			if(screen.getScore() > highScore) {
				FileWriter fw = new FileWriter("/res/hs.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(screen.getScore());
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			screen.getGameLoop().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		switch(speed) {
		case 1:
			if(snakeList.get(snakeList.size()-1).getX() >= screen.getSize().getWidth()-210) {
				snakeList.add(new SnakePart(0, snakeList.get(snakeList.size()-1).getY()));
			} else {
			snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX()+10, snakeList.get(snakeList.size()-1).getY()));
			}
			snakeList.remove(0);
			break;
		case 2:
			if(snakeList.get(snakeList.size()-1).getY() >= screen.getSize().getHeight()-110) {
				snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), 0));
			} else {
			snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), snakeList.get(snakeList.size()-1).getY()+10));
			}
			snakeList.remove(0);
			break;
		case 3:
			if(snakeList.get(snakeList.size()-1).getX() <= 0) {
				snakeList.add(new SnakePart(screen.getSize().width-210, snakeList.get(snakeList.size()-1).getY()));
			} else {
			snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX()-10, snakeList.get(snakeList.size()-1).getY()));
			}
			snakeList.remove(0);
			break;
		case 4:
			if(snakeList.get(snakeList.size()-1).getY() <= 0) {
				snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), screen.getSize().height-110));
			} else {
			snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), snakeList.get(snakeList.size()-1).getY()-10));
			}
			snakeList.remove(0);
			break;
		}
		checkCollision();
	}
	
	public void checkCollision() {
		if((snakeList.get(snakeList.size()-1).getX() == screen.getFood().getX()) && snakeList.get(snakeList.size()-1).getY() == screen.getFood().getY()) {
			switch(speed) {
			case 1:
				if(snakeList.get(snakeList.size()-1).getX() >= screen.getSize().getWidth()-210) {
					snakeList.add(new SnakePart(0, snakeList.get(snakeList.size()-1).getY()));
				} else {
				snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX()+10, snakeList.get(snakeList.size()-1).getY()));
				}
				screen.getFood().changeLoc();
				screen.setScore(screen.getScore()+1);
				break;
			case 2:
				if(snakeList.get(snakeList.size()-1).getY() >= screen.getSize().height-110) {
					snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), 0));
				} else {
				snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), snakeList.get(snakeList.size()-1).getY()+10));
				}
				screen.getFood().changeLoc();
				screen.setScore(screen.getScore()+1);
				break;
			case 3:
				if(snakeList.get(snakeList.size()-1).getX() <= 0) {
					snakeList.add(new SnakePart(screen.getSize().width-210, snakeList.get(snakeList.size()-1).getY()));
				} else {
				snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX()-10, snakeList.get(snakeList.size()-1).getY()));
				}
				screen.getFood().changeLoc();
				screen.setScore(screen.getScore()+1);
				break;
			case 4:
				if(snakeList.get(snakeList.size()-1).getY() <= 0) {
					snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), screen.getSize().height-110));
				} else {
				snakeList.add(new SnakePart(snakeList.get(snakeList.size()-1).getX(), snakeList.get(snakeList.size()-1).getY()-10));
				}
				screen.getFood().changeLoc();
				screen.setScore(screen.getScore()+1);
				break;
			}
		}
		
		for(SnakePart sp : snakeList) {
			if(snakeList.get(snakeList.size()-1) == sp) {
				continue;
			}
			if((snakeList.get(snakeList.size()-1).getX() == sp.getX()) && (snakeList.get(snakeList.size()-1).getY() == sp.getY())) {
				gameOver();
			}
		}
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public ArrayList<SnakePart> getSnakeList() {
		return snakeList;
	}
}
