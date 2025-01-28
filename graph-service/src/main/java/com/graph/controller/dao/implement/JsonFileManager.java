package com.graph.controller.dao.implement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.graph.controller.tda.graph.LabeledGraph;

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

    public static JsonElement graphToJson(LabeledGraph<?> labeledGraph) {
        return labeledGraph.graphToJson();
    }

    public static LabeledGraph<Object> graphFromJson(Class<?> _class_, Boolean forShow) throws Exception {
        String graphNameClass = _class_.getSimpleName();
        JsonElement element = null;
        try {
            element = new Gson().fromJson(readFile("Graph" + graphNameClass), JsonElement.class);
        } catch (Exception e) {
            System.out.println("Errorsito:" + e.getMessage());
        }

        boolean nullElement = element == null;

        if (nullElement) {
            return new LabeledGraph<>(1, Object.class);
        }

        
        JsonObject vertices = element.getAsJsonObject().get("vertices").getAsJsonObject();

        Integer numVertices = (forShow) ? vertices.size() : vertices.size() + 1;
        
        LabeledGraph<Object> graph = new LabeledGraph<>(numVertices, Object.class);

        Gson gson = new Gson();
        for (int j = 0; j < vertices.size(); j++) {
            JsonObject obj = vertices.get("v" + Integer.toString(j + 1)).getAsJsonObject();
            graph.labelVertex(j + 1, gson.fromJson(obj.toString(), _class_));
        }

        JsonObject obj = element.getAsJsonObject().get("adjacencies").getAsJsonObject();
        for (int j = 1; j <= obj.size(); j++) {
            String key = "v" + Integer.toString(j);
            JsonArray adjs = obj.get(key).getAsJsonArray();
            
            for (int i = 0; i < adjs.size(); i++) {
                JsonObject adjacency = adjs.get(i).getAsJsonObject();
                graph.addEdge(j, adjacency.get("destination").getAsInt(), adjacency.get("weight").getAsFloat());
            }
        }
        
        return graph;
    }

    public static LabeledGraph<Object> graphFromJson(Class<?> class1) throws Exception {
        return graphFromJson(class1,false);
    } 

    /* public static void main(String[] args) throws Exception {
        LabeledGraph<Object> labeledGraph = new LabeledGraph<>(4, Object.class);
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

        saveFile(labeledGraph.graphToJson(), "GraphVeterinaria");

        labeledGraph = graphFromJson(Veterinaria.class);
        System.out.println(labeledGraph);
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
