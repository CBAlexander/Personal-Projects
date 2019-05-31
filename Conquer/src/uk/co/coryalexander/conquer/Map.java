package uk.co.coryalexander.conquer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map extends Canvas {

    private Graph graph;

    private Vertex selected, targeted;

    private BufferedImage bgImg;

    private ArrayList<ActionButton> buttons;

    private int reinforcements;

    private boolean turnOver;

    public Map() {
        init();
    }

    public void init() {
        setBounds(0, 0, Values.windowWidth, Values.windowHeight);
        try {
            bgImg = ImageIO.read(new File("map.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        buttons = new ArrayList<ActionButton>();
        reinforcements = 10;
        addMouseListener(new MouseHandler(this));

        graph = new Graph();

        graph.addVertex(new Vertex("Paris", 400, 460, 25, 25));
        graph.addVertex(new Vertex("London", 340, 380, 25, 15));
        graph.addVertex(new Vertex("Warsaw", 795, 370, 25, 20));
        graph.addVertex(new Vertex("Rome", 610, 570, 25, 30));
        graph.addVertex(new Vertex("Berlin", 625, 375, 25, 10));
        graph.addVertex(new Vertex("Madrid", 180, 600, 25, 15));
        graph.addVertex(new Vertex("Oslo", 575, 215, 25, 20));
        graph.addVertex(new Vertex("Athens", 870, 620, 25, 30));
        graph.addVertex(new Vertex("East EU", 790, 470, 25, 30));
        graph.addVertex(new Vertex("Reykjavik", 115, 80, 25, 30));


        graph.getVertex("Rome").setPlayerOwned(true);
        graph.getVertex("Warsaw").setPlayerOwned(true);
        graph.getVertex("East EU").setPlayerOwned(true);

        graph.addEdge(new Edge(graph.getVertex("Rome"), graph.getVertex("Athens")));
        graph.addEdge(new Edge(graph.getVertex("Rome"), graph.getVertex("Berlin")));
        graph.addEdge(new Edge(graph.getVertex("Rome"), graph.getVertex("Paris")));
        graph.addEdge(new Edge(graph.getVertex("Paris"), graph.getVertex("London")));
        graph.addEdge(new Edge(graph.getVertex("Paris"), graph.getVertex("Berlin")));
        graph.addEdge(new Edge(graph.getVertex("Paris"), graph.getVertex("Madrid")));
        graph.addEdge(new Edge(graph.getVertex("Berlin"), graph.getVertex("Oslo")));
        graph.addEdge(new Edge(graph.getVertex("Berlin"), graph.getVertex("Warsaw")));
        graph.addEdge(new Edge(graph.getVertex("Berlin"), graph.getVertex("East EU")));
        graph.addEdge(new Edge(graph.getVertex("East EU"), graph.getVertex("Warsaw")));
        graph.addEdge(new Edge(graph.getVertex("East EU"), graph.getVertex("Athens")));
        graph.addEdge(new Edge(graph.getVertex("Reykjavik"), graph.getVertex("London")));
        graph.addEdge(new Edge(graph.getVertex("Reykjavik"), graph.getVertex("Oslo")));


    }


    public void update() {

    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g.drawImage(bgImg, 0, 0, Values.windowWidth, Values.windowHeight, null);

        for (Edge e : graph.getEdges()) {
            e.render(g);
        }

        for (Vertex v : graph.getVertices()) {
            v.render(g);
        }

        if (selected != null) {
            for(ActionButton button : buttons) {
                if(button.isVisible()) button.render(g);
            }
        }

        g.drawString("Reinforcements: " + reinforcements, 1100, 20);
            bufferStrategy.show();

    }

    public void nextTurn() {
        for(Vertex v : graph.getVertices()) {
            if(v.isPlayerOwned()) {
                reinforcements++;
            }
        }

        setTurnOver(false);
    }

    public Graph getGraph() {
        return graph;
    }

    public Vertex getSelected() {
        return selected;
    }

    public void setSelected(Vertex selected) {
        if(this.selected != null) this.selected.setSelected(false);
        this.selected = selected;
        if(this.selected != null) selected.setSelected(true);
    }

    public Vertex getTargeted() {
        return targeted;
    }

    public void setTargeted(Vertex targeted) {
        if(this.targeted != null) this.targeted.setSelected(false);
        this.targeted = targeted;
        if(this.targeted != null) targeted.setSelected(true);
    }

    public ArrayList<ActionButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<ActionButton> buttons) {
        this.buttons = buttons;
    }

    public int getReinforcements() {
        return reinforcements;
    }

    public void setReinforcements(int reinforcements) {
        this.reinforcements = reinforcements;
    }

    public boolean isTurnOver() {
        return turnOver;
    }

    public void setTurnOver(boolean turnOver) {
        this.turnOver = turnOver;
        if(turnOver == true) nextTurn();
    }
}
