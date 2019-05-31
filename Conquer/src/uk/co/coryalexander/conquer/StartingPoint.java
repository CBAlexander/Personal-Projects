package uk.co.coryalexander.conquer;

import javax.swing.*;

public class StartingPoint extends JFrame implements Runnable{

    private static StartingPoint instance;

    Thread gameThread;

    Map map;
    Menu menu;

    private void init() {
        setSize(Values.windowWidth, Values.windowHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFocusable(true);
        setTitle(Values.windowTitle);
        setLayout(null);

        gameThread = new Thread(this);

        map = new Map();
        //menu = new Menu();
        start();
    }

    private void start() {
        add(map);
        //add(menu);
        setVisible(true);
        gameThread.start();
    }

    @Override
    public void run() {
        while(true) {
            long oldTime = System.nanoTime();
            long counterOldTime = System.currentTimeMillis();
            int frames = 0, updates = 0;
            while (true) {
                long newTime = System.nanoTime();
                long counterNewTime = System.currentTimeMillis();
                if (newTime - oldTime >= 1000000000 / 60F) {
                    update();
                    updates++;
                    oldTime = System.nanoTime();
                }
                render();
                frames++;

                if (counterNewTime - counterOldTime >= 1000) {
                    System.out.println(String.format("FPS: %d | UPS: %d", frames, updates));
                    counterOldTime = System.currentTimeMillis();
                    frames = 0;
                    updates = 0;
                }
            }
        }
    }

    public void update() {
        map.update();
       // menu.update();
    }

    public void render() {
        map.render();
      //  menu.render();
    }

    public static StartingPoint getInstance() {
        if(instance == null) {
            instance = new StartingPoint();
        }

        return instance;
    }

    public static void main(String[] args) {
        new StartingPoint().getInstance().init();
    }
}
