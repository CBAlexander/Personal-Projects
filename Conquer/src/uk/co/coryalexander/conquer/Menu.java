package uk.co.coryalexander.conquer;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Menu extends Canvas {

    public Menu() {
        init();
    }

    public void init() {
        setBounds((int) Math.floor(Values.windowWidth * 0.66), 0, (int) Math.floor(Values.windowWidth * 0.33), Values.windowHeight);
    }

    public void update() {

    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if(bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLUE);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);

        bufferStrategy.show();
    }
}
