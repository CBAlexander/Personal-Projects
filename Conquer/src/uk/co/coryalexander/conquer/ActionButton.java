package uk.co.coryalexander.conquer;

import java.awt.*;

public class ActionButton {

    private String name;
    private int x, y, width, height;
    private boolean visible;

    private Vertex associatedVertex;

    public ActionButton(String name, int x, int y, int width, int height, Vertex associatedVertex) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.associatedVertex = associatedVertex;
    }

    public void render(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(name, (x + 7), (y + 20));
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vertex getAssociatedVertex() {
        return associatedVertex;
    }

    public void setAssociatedVertex(Vertex associatedVertex) {
        this.associatedVertex = associatedVertex;
    }
}
