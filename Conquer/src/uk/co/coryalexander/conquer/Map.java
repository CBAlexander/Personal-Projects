package uk.co.coryalexander.conquer;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    private int reinforcements1, reinforcements2;

    private boolean turnOver;

    private ActionButton endTurn;

    private boolean player;

    private String turnString = "Player 2's Turn!";

    private int turnX, turnY;

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
        reinforcements1 = 10;
        reinforcements2 = 10;
        endTurn = new ActionButton("END TURN", 1100, 650, 100, 25, null);
        endTurn.setVisible(true);
        buttons.add(endTurn);
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

        g.setColor(new Color(69, 74 ,84));
        g.fillRect(1000, 575, 280, 120);
        g.setColor(Color.WHITE);
        g.drawString("Reinforcements: " + (!player ? reinforcements1 : reinforcements2), 1100, 630);
        g.drawString((!player ? "Player 1" : "Player 2"), 1100, 600);

        if (selected != null) {
            for(ActionButton button : buttons) {
                if(button.isVisible() && !button.getName().equals("END TURN")) button.render(g);
            }
        }

        endTurn.render(g);

        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString(turnString, turnX - 300, turnY);


        bufferStrategy.show();

    }

    public void nextTurn() {

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(Vertex v : graph.getVertices()) {
            if(v.isPlayerOwned()) {
                if(!player) reinforcements1++;
                if(player) reinforcements2++;
                v.setPlayerOwned(false);
            }else{
                v.setPlayerOwned(true);
            }


        }

        boolean win = true;
        for(Vertex v : graph.getVertices()) {
            if(!player) {
                if(v.isPlayerOwned()) {
                    win = false;
                    break;
                }
            }

            if(player) {
                if(!v.isPlayerOwned()) {
                    win = false;
                    break;
                }
            }

            if(win) {
                turnString = (!player ? "PLAYER 1 WINS!!!" : "PLAYER 2 WINS!!!");
                JOptionPane.showMessageDialog(this, turnString);
                System.exit(0);
            }


        }
        setSelected(null);
        player = !player;
        turnX = Values.windowWidth / 2;
        turnY = Values.windowHeight /2;
        turnString = (!player ? "Player 1's Turn!" : "Player 2's Turn");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       turnString = "";

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

    public int getReinforcements1() {
        return reinforcements1;
    }

    public void setReinforcements1(int reinforcements1) {
        this.reinforcements1 = reinforcements1;
    }

    public int getReinforcements2() {
        return reinforcements2;
    }

    public void setReinforcements2(int reinforcements2) {
        this.reinforcements2 = reinforcements2;
    }

    public boolean isTurnOver() {
        return turnOver;
    }

    public void setTurnOver(boolean turnOver) {
        this.turnOver = turnOver;
        if(turnOver == true) nextTurn();
    }

    public boolean isPlayer() {
        return player;
    }
}
