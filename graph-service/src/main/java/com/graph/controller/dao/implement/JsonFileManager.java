package com.graph.controller.dao.implement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.graph.controller.tda.graph.Adjacency;
import com.graph.controller.tda.graph.LabeledGraph;
import com.graph.controller.tda.list.LinkedList;

public class JsonFileManager {

    public final static String jsonContainerDir = "./media/";
    public final static String currentID = "CurrentID"; 
    
    public static void saveFile(Object data, String className) {
        final String json = new GsonBuilder().setPrettyPrinting().create().toJson(data);
        final String fileName = jsonContainerDir + className + ".json";

        try (FileWriter tilinWriter = new FileWriter(fileName)) {
            tilinWriter.write(json);
            tilinWriter.flush();
        } catch (Exception e) {
            System.out.println("JsonFileManager.saveFile() dice: " + e.getMessage());
        }
    }

    public static JsonElement labeledGraphToJson(LabeledGraph<?> labeledGraph) {
        StringBuilder sb = new StringBuilder("{");
        Gson gson = new Gson();
        sb.append("\"vertices\": [");

        for (int i = 1; i <= labeledGraph.numVertices(); i++) {
            String labelJson = gson.toJson(labeledGraph.getLabelOfVertice(i));

            sb.append("{\"v" + i + "\": " + labelJson + "}");
            if(i < labeledGraph.numVertices()) {
                sb.append(",");
            }
        }

        sb.append("]");
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

    /* public static void main(String[] args) throws Exception {
        LabeledGraph<Veterinaria> labeledGraph = new LabeledGraph<>(4, Veterinaria.class);
        Veterinaria v = new VeterinariaBuilder().veterinariaWithGenericData().build();

        labeledGraph.labelVertex(1,v);
        labeledGraph.labelVertex(2,v);
        labeledGraph.labelVertex(3,v);
        labeledGraph.labelVertex(4,v);

        labeledGraph.addEdge(1, 2,3f);
        labeledGraph.addEdge(1, 3,1f);
        labeledGraph.addEdge(1, 4,2f);

        labeledGraph.addEdge(2, 1,2f);
        labeledGraph.addEdge(2, 3,2f);
        labeledGraph.addEdge(3, 4,2f);

        labeledGraph.addEdge(3, 4,2f);
        labeledGraph.addEdge(3, 2,2f);
        labeledGraph.addEdge(3, 1,2f);

        JsonElement jsonElement = labeledGraphToJson(labeledGraph);

        JsonObject obj = jsonElement.getAsJsonObject();
    } */

    public static String readFile(String className) {
        final String fileName = jsonContainerDir + className + ".json";
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = bfr.readLine()) != null) {
                sb.append(line).append('\n');
            }

            return sb.toString();
        } catch (Exception e) {
            System.out.println("JsonFileManager.readFile(str) dice: " + e.getMessage());
            return "";
        }
    }


    // CURRENT ID MANAGMENT ===============================================================

    public static void saveCurrentIdOf(Integer currentId,String className) {
        HashMap<String,Integer> cIdMap = readCurrentIDMap();

        if (cIdMap == null) 
            cIdMap = new HashMap<>();

        final String key = "current" + className + "Id";
        cIdMap.put(key, currentId);
        saveFile(cIdMap,currentID);
    }

    public static HashMap<String,Integer> readCurrentIDMap() {
        Type mapType = new TypeToken<HashMap<String,Integer>>(){}.getType();
        return new Gson().fromJson(readFile(currentID), mapType);
    }

    public static Integer readAndUpdateCurrentIdOf(String className) {
        HashMap<String, Integer> currentIDs = readCurrentIDMap();
        final String key = "current" + className + "Id";

        if(currentIDs == null) {
            saveCurrentIdOf(0,className);
            currentIDs = readCurrentIDMap();
        } 

        if(currentIDs.get(key) == null) {
            saveCurrentIdOf(0, className);
            currentIDs = readCurrentIDMap();
        } 

        Integer currentId = currentIDs.get(key);
        saveCurrentIdOf(++currentId, className);
        return currentId;
    }
}
