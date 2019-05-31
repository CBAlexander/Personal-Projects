package uk.co.coryalexander.conquer;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    private String name;

    public Graph() {
        vertices = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    public Vertex getVertex(String name) {
        for(Vertex v : vertices) {
            if(v.getName().equals(name)) {
                return v;
            }
        }
        System.out.println("Could not find vertex with name: \"" + name + "\"");
        return null;
    }

    public ArrayList<Edge> getEdgesFrom(String name) {
        ArrayList<Edge> tempEdges = new ArrayList<Edge>();
        for(Edge e : edges) {
            if(e.getStartVertex().getName().equals(name) || e. getEndVertex().getName().equals(name)) {
                tempEdges.add(e);
            }
        }

        return tempEdges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
}
