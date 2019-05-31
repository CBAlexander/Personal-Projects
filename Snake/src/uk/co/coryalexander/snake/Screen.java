package uk.co.coryalexander.snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Screen extends Canvas implements Runnable{

	private Thread t;
	private Dimension size;
	private Snake snake;
	private KeyHandler kh;
	private Food food;
	private int score;
	private int highScore;
	
	public Screen(Dimension size) {
		this.size = size;
		t = new Thread(this);
		snake = new Snake(this);
		setKh(new KeyHandler(snake));
		this.addKeyListener(getKh());

		food = new Food(this);
		
		try {
			FileReader fr = new FileReader("res/hs.txt");
			BufferedReader br = new BufferedReader(fr);
			highScore = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	
	public void run() {
		while(true) {
			render();
			update();
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				System.out.println("Error 1: Sleep in gameloop failed :(");
			}
		}
	}


	private void update() {
		snake.update();
	}


	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, size.width, size.height);

		for(int x = 0; x < 59; x++) {
			for(int y = 0; y < 59; y++) {
				g.drawRect(x*10, y*10, 10, 10);
			}
		}
		
		snake.render(g);
		food.render(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Serif", Font.BOLD, 12));
		g.drawString("SCORE: "+score, size.width - 190, 10);
		g.drawString("HIGH SCORE: "+highScore, size.width - 190, 20);
		g.drawRect(0, 0, size.width-200, size.height-100);
		bs.show();
	}
	
	public Thread getGameLoop() {
		return t;
	}


	public KeyHandler getKh() {
		return kh;
	}


	public void setKh(KeyHandler kh) {
		this.kh = kh;
	}
	
	public Food getFood() {
		return food;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public Dimension getSize() {
		return size;
	}
}
