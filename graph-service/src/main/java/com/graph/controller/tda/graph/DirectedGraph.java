package com.graph.controller.tda.graph;

import com.graph.controller.tda.list.LinkedList;

@SuppressWarnings("unchecked")
public class DirectedGraph extends Graph {
    private Integer numVertices;
    private Integer numEdges;
    private LinkedList<Adjacency>[] adjacencies;

    public DirectedGraph(Integer numVertices) {
        this.numVertices = numVertices;
        this.numEdges = 0;
        this.adjacencies = new LinkedList[numVertices + 1];
        for (int i = 1; i < adjacencies.length; i ++) {
            adjacencies[i] = new LinkedList<>();
        }
    }

    public Boolean isValidVertice(Integer v) {
        if (v.intValue() < 0 || v.intValue() > numVertices.intValue()) 
            return false;
        
        return true;
    }

    @Override
    public Integer numVertices() {
        return this.numVertices;
    }

    @Override
    public Integer numEdges() {
        return this.numEdges;
    }

    @Override
    public LinkedList<Adjacency> adjacencyList(Integer v) {
        return this.adjacencies[v];
    }

    @Override
    public Boolean isEdge(Integer v1, Integer v2) throws Exception {
        if (!isValidVertice(v1) || !isValidVertice(v2)) return false;

        LinkedList<Adjacency> adjs = adjacencies[v1];
        if (adjs.isEmpty()) {
            return false;
        }

        Adjacency[] vector = adjs.toArray(Adjacency.class);

        for (int i = 0; i < vector.length; i++) {
            final Adjacency a = vector[i];
            if (a.getDestination().intValue() == v2.intValue()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addEdge(Integer v1, Integer v2, Float weight) throws Exception {
        if (!isValidVertice(v1) || !isValidVertice(v2)) return;
        if (isEdge(v1, v2)) return;
        adjacencies[v1].add(new Adjacency(v2,weight));
        numEdges++;
    }

    @Override
    public void addEdge(Integer v1, Integer v2) throws Exception {
        addEdge(v1, v2, Float.NaN);
    }

    @Override
    public Float weightEdge(Integer v1, Integer v2) throws Exception {
        if (!isEdge(v1, v2)) return Float.NaN;
        Adjacency[] vector = adjacencies[v1-1].toArray(Adjacency.class);
        for (int i = 0; i < vector.length; i++) {
            if (vector[i].getDestination() == v2.intValue()) {
                return vector[i].getWeight();
            }
        }
        return Float.NaN;
    }

    public void dijkstra() {
        //TODO
    }

    public void setAdjacencies(LinkedList<Adjacency>[] adjacencies) {
        this.adjacencies = adjacencies;
    }

    public void setNumEdges(Integer numEdges) {
        this.numEdges = numEdges;
    }

    public void setNumVertices(Integer numVertices) {
        this.numVertices = numVertices;
    }

    public LinkedList<Adjacency>[] getAdjacencies() {
        return adjacencies;
    }
}
