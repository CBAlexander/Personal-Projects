package uk.co.coryalexander.snake;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StartingPoint {
	
	private JFrame window;
	private final String TITLE = "Snake";
	private final Dimension SIZE = new Dimension(500, 400);
	private Screen screen;
	
	public StartingPoint() {
		init();
		start();

	}
	
	public void init() {
		window = new JFrame(TITLE);
		window.setSize(SIZE);
		window.setResizable(false);
		window.setDefaultCloseOperation(3);
		window.setLocationRelativeTo(null);
		screen = new Screen(SIZE);
		window.add(screen);
		window.addKeyListener(screen.getKh());
		window.setFocusable(true);
		window.setVisible(true);
	}
	
	public void start() {
		screen.getGameLoop().start();
	}
	
	public void stop() {
		try {
			screen.getGameLoop().join();
		} catch (InterruptedException e) {
			System.out.println("Error 1: GameLoop unable to join");
		}
	}
	
	public static void main(String[] args) {
		new StartingPoint();
	}
}	
