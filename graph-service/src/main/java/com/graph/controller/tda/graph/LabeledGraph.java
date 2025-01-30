package com.graph.controller.tda.graph;

import java.lang.reflect.Array;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.graph.controller.tda.graph._interface.ModelEdge;
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

        for (int i = 1; i <= numVertices(); i++) {
            for (int j = 1; j <= numVertices(); j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Float.POSITIVE_INFINITY;
                }
            }
        }

        for (int i = 1; i <= numVertices(); i++) {
            for (Adjacency adj : adjacencyList(i).toArray(Adjacency.class)) {
                dist[i][adj.getDestination()] = adj.getWeight();
            }
        }

        for (int k = 1; k <= numVertices(); k++) {
            for (int i = 1; i <= numVertices(); i++) {
                for (int j = 1; j <= numVertices(); j++) {
                    if (dist[i][k] != Float.POSITIVE_INFINITY && dist[k][j] != Float.POSITIVE_INFINITY) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

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

    public void bellmanFord(int source) {
        float[] dist = new float[numVertices() + 1];

        for (int i = 0; i < dist.length; i++) dist[i] = Float.POSITIVE_INFINITY;


        dist[source] = 0;
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

        System.out.println("Distancias más cortas desde el vértice " + source + ":");
        for (int i = 1; i <= numVertices(); i++) {
            if (dist[i] == Float.POSITIVE_INFINITY) {
                System.out.println("V" + i + " : INFINITO");
            } else {
                System.out.println("V" + i + " : " + dist[i]);
            }
        }
    }

    public String writeGraphAsJavaScriptCode() throws Exception {
        StringBuilder sb = new StringBuilder("var nodes = new vis.DataSet([\n");
        for (int i = 1; i <= numVertices(); i++) {
            sb.append("\t{ id: " + i + ", label: " + "\"" + getLabelOfVertice(i).toString() + "\" }");
            if (i < numVertices()) sb.append(",\n");
        }

        sb.append("\n]);\n\n");
        
        sb.append("var edges = new vis.DataSet([\n");

        LinkedList<Adjacency>[] lista = getAdjacencies();
        for (int i = 1; i < lista.length; i++) {
            LinkedList<Adjacency> adjs = lista[i];
            for (int j = 0; j < adjs.getSize(); j++) {
                sb.append("\t{ from: " + i + ", to: " + adjs.get(j).getDestination() + ", label: "+ "\'" + adjs.get(j).getWeight() + "\'" + " }");
                if (j < adjs.getSize() - 1) sb.append(",\n");
            }
            if (i < lista.length - 1 && !adjs.isEmpty()) sb.append(",\n");
        }

        sb.append("\n]);\n\n");
        sb.append("var container = document.getElementById(\"mynetwork\");\n\n");
        sb.append("var data = {\n").append("\tnodes: nodes,\n").append("\tedges:edges\n};\n\n");
        sb.append("var options = {};\n\n");
        sb.append("var network = new vis.Network(container, data, options);");

        return sb.toString();
    }

    public void edgeAll(ModelEdge<E> modelEdge) throws Exception {

        LinkedList<Adjacency> adjs[] = getAdjacencies();
        for (int j = 1; j < adjs.length; j++) {
            LinkedList<Adjacency> adj = adjs[j];
            for (int i = 0; i < adj.getSize(); i++) {
                Adjacency ad = adj.get(i);
                addEdge(j, ad.getDestination(), modelEdge.heuristicaVertices(getLabelOfVertice(j), getLabelOfVertice(ad.getDestination())));
            }
        }

    }

    public static JsonElement graphToJson(LabeledGraph<?> labeledGraph) {
        StringBuilder sb = new StringBuilder("{");
        Gson gson = new Gson();
        sb.append("\"vertices\": {");

        for (int i = 1; i <= labeledGraph.numVertices(); i++) {
            String labelJson = gson.toJson(labeledGraph.getLabelOfVertice(i));

            sb.append("\"v" + i + "\": " + labelJson);
            if(i < labeledGraph.numVertices()) {
                sb.append(",");
            }
        }

        sb.append("}");
        sb.append(",\"adjacencies\": {");

        LinkedList<Adjacency>[] adjacencies = labeledGraph.getAdjacencies();

        for (int i = 1; i < adjacencies.length; i++) {
            sb.append("\"v" + i + "\": [");
            for (int j = 0; j < adjacencies[i].getSize(); j++) {

                try {
                    sb.append(gson.toJson(adjacencies[i].get(j)));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (j < adjacencies[i].getSize() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");

            if (i < adjacencies.length - 1) {
                sb.append(",");
            }
        }

        sb.append("} }");

        return gson.fromJson(sb.toString(), JsonElement.class);
    }

    public JsonElement graphToJson() {
        return graphToJson(this);
    }

    public void edgeAllVertices() throws Exception {
        int min = 1;
        int max = numVertices();
        for (int i = 1; i <= numVertices(); i++) {

            double randomNumber = (Math.random() * (max - min + 1) + min);
            int randomDestination = (int)randomNumber;
            float weight = (float)randomNumber;
            
            if (randomDestination == i) { 
                randomDestination = (int)(Math.random() * (max - min + 1) + min);
            }

            this.addEdge(i, randomDestination, weight);
        }
    }

}
