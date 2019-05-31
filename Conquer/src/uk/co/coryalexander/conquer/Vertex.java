package uk.co.coryalexander.conquer;

import java.awt.*;

public class Vertex {

    private String name;

    private int x;
    private int y;
    private int size;
    private int armyCount;

    private boolean playerOwned;
    private boolean potential;
    private boolean selected;

    public Vertex(String name, int x, int y, int size, int armyCount) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
        this.armyCount = armyCount;
    }


    public void update() {

    }

    public void render(Graphics2D g) {
        if(playerOwned) {
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.RED);
        }

        g.fillOval(x, y, size, size);
        if(selected) {
            g.setColor(Color.WHITE);
            g.drawOval(x, y, size, size);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(name, x - (size / 2), y + size + 16);
        g.setColor(Color.WHITE);
        g.drawString("" + armyCount, x + 3, y + (size-8));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void setArmyCount(int armyCount) {
        this.armyCount = armyCount;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPlayerOwned(boolean playerOwned) {
        this.playerOwned = playerOwned;
    }

    public boolean isPlayerOwned() {
        return playerOwned;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isPotential() {
        return potential;
    }

    public void setPotential(boolean potential) {
        this.potential = potential;
    }
}
