package com.graph.controller.tda.graph;

public class UndirectedGraph extends DirectedGraph {
    
    public UndirectedGraph(Integer numVertices) {
        super(numVertices);
    }

    @Override
    public void addEdge(Integer v1, Integer v2, Float weight) throws Exception {
        if (!isValidVertice(v1) || !isValidVertice(v2)) return;
        if (isEdge(v1, v2)) {
            return;
        }

        getAdjacencies()[v1].add(new Adjacency(v2,weight));
        getAdjacencies()[v2].add(new Adjacency(v1, weight));
        
        setNumEdges(numEdges() + 1);
    }

    @Override
    public void addEdge(Integer v1, Integer v2) throws Exception {
        addEdge(v1, v2, Float.NaN);
    }
}
