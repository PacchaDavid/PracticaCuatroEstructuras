package com.graph.controller.tda.graph;

public class Adjacency {
    private Integer destination;
    private Float weight;

    public Adjacency() {}

    public Adjacency(Integer destination) {
        this.destination = destination;
        this.weight = Float.NaN;
    }

    public Adjacency(Integer destination,Float weight) {
        this(destination);
        this.weight = weight;
    } 

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
