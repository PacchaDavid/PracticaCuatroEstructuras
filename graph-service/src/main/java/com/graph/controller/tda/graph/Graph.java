package com.graph.controller.tda.graph;

import com.graph.controller.tda.list.LinkedList;

public abstract class Graph {
    public abstract Integer numEdges();
    public abstract Integer numVertices();
    public abstract Boolean isEdge(Integer v1, Integer v2) throws Exception;
    public abstract Float weightEdge(Integer v1, Integer v2) throws Exception;
    public abstract void addEdge(Integer v1, Integer v2, Float weight) throws Exception;
    public abstract void addEdge(Integer v1, Integer v2) throws Exception;
    public abstract LinkedList<Adjacency> adjacencyList(Integer v);


    public String toString() {
        String graphString = "";
        
        try {
            for (int i = 1; i <= numVertices().intValue(); i++) {
                graphString += "V" + i  + "\n";
                LinkedList<Adjacency> adjacencies = adjacencyList(i);
                
                Adjacency adj[] = adjacencies.toArray(Adjacency.class);
                
                for(int j = 0; j < adj.length; j++) {
                    final Adjacency a = adj[j];
                    graphString += "Adj V" + a.getDestination() + " weight: " + a.getWeight() + "\n";
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();    
        }
        return graphString;
    }

}
