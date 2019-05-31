package uk.co.coryalexander.conquer;

import java.awt.*;

public class Edge {

    private Vertex startVertex, endVertex;

    public Edge(Vertex startVertex,  Vertex endVertex) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        if(startVertex.isPlayerOwned() && endVertex.isPlayerOwned()) g.setColor(Color.GREEN);
        if((startVertex.isPlayerOwned() || endVertex.isPlayerOwned()) && !(startVertex.isPlayerOwned() && endVertex.isPlayerOwned())) g.setColor(Color.ORANGE);
        if(!startVertex.isPlayerOwned() && !endVertex.isPlayerOwned()) g.setColor(Color.RED);
        g.setStroke(new BasicStroke(3));
        g.drawLine(startVertex.getX() + (startVertex.getSize()/2), startVertex.getY() + (startVertex.getSize()/2), endVertex.getX() + (endVertex.getSize()/2), endVertex.getY() + (endVertex.getSize()/2));
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }
}
