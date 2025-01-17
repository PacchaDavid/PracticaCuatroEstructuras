package com.graph.controller.tda.graph;

import java.lang.reflect.Array;
import java.util.HashMap;

import com.graph.controller.tda.list.LinkedList;

@SuppressWarnings("unchecked")
public class LabeledGraph<E> extends DirectedGraph {
    protected Class<E> eClass;
    protected E[] labels;
    protected HashMap<E,Integer> dictVertices;
    
    public LabeledGraph(Integer numVertices, Class<E> _class) {
        super(numVertices);
        this.eClass = _class;
        labels = (E[])Array.newInstance(_class, numVertices + 1);
        dictVertices = new HashMap<>();
    }

    public Integer getVertice(E v) {
        return dictVertices.get(v);
    }

    public Boolean isEdgeL(E v1, E v2) throws Exception {
        if (!isAllGraphLabeled()) return false;
        return isEdge(getVertice(v1), getVertice(v2));
    }

    public LinkedList<Adjacency> adjacenciesL(E v) {
        if (!isAllGraphLabeled()) return null;
        return adjacencyList(getVertice(v));
    }

    public void insertEdgeL(E v1, E v2, Float weight) throws Exception {
        if (!isAllGraphLabeled()) return;
        addEdge(getVertice(v1), getVertice(v2), weight);
    }

    public void labelVertex(Integer v, E data) {
        labels[v] = data;
        dictVertices.put(data,v);
    }

    public E getLabelOfVertice(Integer v) {
        return labels[v];
    }

    public E[] getLabels() {
        return labels;
    }

    public Boolean isAllGraphLabeled() {
        for (int i = 1; i < labels.length; i++) {
            if (labels[i] == null) return false;
        } 
        return true;
    }

    @Override
    public String toString() {
        String grafo = "";
        try {
            for (int i = 1; i <= this.numVertices(); i++) {
                grafo += "V" + i + "[" + getLabelOfVertice(i).toString() + "]" + "\n";
                LinkedList<Adjacency> list = adjacencyList(i);

                Adjacency[] ady = list.toArray(Adjacency.class);

                for (int j = 0; j < ady.length; j++) {
                    Adjacency a  = ady[j];
                    grafo += "ady V" + a.getDestination() + "[" + getLabelOfVertice(a.getDestination()) + "]" + " weight: " + a.getWeight() + "\n";
                }

            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return grafo;
    }

    public void floydWarshall() throws Exception {
        float[][] dist = new float[numVertices() + 1][numVertices() + 1];

        // Inicializar la matriz de distancias
        for (int i = 1; i <= numVertices(); i++) {
            for (int j = 1; j <= numVertices(); j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Float.POSITIVE_INFINITY;
                }
            }
        }

        // Llenar la matriz con las distancias de las aristas directas
        for (int i = 1; i <= numVertices(); i++) {
            for (Adjacency adj : adjacencyList(i).toArray(Adjacency.class)) {
                dist[i][adj.getDestination()] = adj.getWeight();
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 1; k <= numVertices(); k++) {
            for (int i = 1; i <= numVertices(); i++) {
                for (int j = 1; j <= numVertices(); j++) {
                    if (dist[i][k] != Float.POSITIVE_INFINITY && dist[k][j] != Float.POSITIVE_INFINITY) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Mostrar los resultados
        for (int i = 1; i <= numVertices(); i++) {
            for (int j = 1; j <= numVertices(); j++) {
                if (dist[i][j] == Float.POSITIVE_INFINITY) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

     // Método para ejecutar el algoritmo de Bellman-Ford
    public void bellmanFord(int source) {
        // Inicializar las distancias
        float[] dist = new float[numVertices() + 1];
        //Arrays.fill(dist, Float.POSITIVE_INFINITY);

        for (int i = 0; i < dist.length; i++) dist[i] = Float.POSITIVE_INFINITY;


        dist[source] = 0;

        // Relajar todas las aristas numVertices()-1 veces
        for (int i = 1; i <= numVertices() - 1; i++) {
            for (int u = 1; u <= numVertices(); u++) {
                for (Adjacency adj : adjacencyList(u).toArray(Adjacency.class)) {
                    int v = adj.getDestination();
                    float weight = adj.getWeight();
                    if (dist[u] != Float.POSITIVE_INFINITY && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }

        // Verificar si hay ciclos negativos
        for (int u = 1; u <= numVertices(); u++) {
            for (Adjacency adj : adjacencyList(u).toArray(Adjacency.class)) {
                int v = adj.getDestination();
                float weight = adj.getWeight();
                if (dist[u] != Float.POSITIVE_INFINITY && dist[u] + weight < dist[v]) {
                    System.out.println("El grafo contiene un ciclo negativo");
                    return;
                }
            }
        }

        // Imprimir las distancias más cortas desde el vértice fuente
        System.out.println("Distancias más cortas desde el vértice " + source + ":");
        for (int i = 1; i <= numVertices(); i++) {
            if (dist[i] == Float.POSITIVE_INFINITY) {
                System.out.println("V" + i + " : INFINITO");
            } else {
                System.out.println("V" + i + " : " + dist[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        LabeledGraph<String> graph = new LabeledGraph<>(10,String.class);

        graph.labelVertex(1, "Guaman");
        graph.labelVertex(2, "Cevallos");
        graph.labelVertex(3, "Ivan");
        graph.labelVertex(4, "Jgraso");
        graph.labelVertex(5, "Pancho");
        graph.labelVertex(6, "Riofrío");
        graph.labelVertex(7, "Ontaneda");
        graph.labelVertex(8, "Nole");
        graph.labelVertex(9, "Tomalá");
        graph.labelVertex(10,"Tansado");

        graph.addEdge(1, 3, 4f);
        graph.addEdge(2, 6, -4f);
        graph.addEdge(9, 7, 4f);
        graph.addEdge(8, 10, 4f);
        graph.addEdge(6, 4, 4f);
        graph.addEdge(10, 3, 4f);
        graph.addEdge(1, 2, 4f);
        graph.addEdge(5, 3, 4f);

        graph.bellmanFord(1);
    }
}
