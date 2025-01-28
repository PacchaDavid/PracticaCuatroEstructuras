package com.graph.controller.tda.graph;

import com.graph.controller.models.Mascota;
import com.graph.controller.models.builder.MascotaBuilder;

public class Prueba {
    public static void main(String[] args) throws Exception {
        
        final Integer NUMERO_DATOS = 30;

        LabeledGraph<Mascota> labeledGraph = new LabeledGraph<>(NUMERO_DATOS, Mascota.class);

        for (int i = 1; i <= labeledGraph.numVertices(); i++) {
            labeledGraph.labelVertex(i, new MascotaBuilder().mascotaWithGenericData().build());
        }

        labeledGraph.edgeAllVertices();

        Long t0 = System.currentTimeMillis();
        //labeledGraph.floydWarshall();
        labeledGraph.bellmanFord(1);
        Long t1 = System.currentTimeMillis();

        System.out.println("Total time: " + (t1 - t0) + "ms");
    }
}
