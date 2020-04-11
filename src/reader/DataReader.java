package reader;

import algorithm.model.Graph;
import algorithm.model.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class DataReader {

    public static Graph getGraphFromFile(String filePath) {
        BufferedReader reader;
        ResourcesReader resourcesReader = new ResourcesReader();
        Map<Integer, Vertex> vertices = new HashMap<>();
        try {
            reader = resourcesReader.readFile(filePath);
            String line = reader.readLine();
            while (line != null) {
                Vertex vertex = parseLine(line);
                vertices.put(vertex.getId(), vertex);
                line = reader.readLine();
            }
            reader.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return new Graph(vertices);
    }

    private static Vertex parseLine(String line) {
        String[] splittedLine = line.split("\\t");
        Integer id = Integer.parseInt(splittedLine[0]);
        List<Integer> adjacencyList = new ArrayList<>();
        for(int i = 1; i<splittedLine.length; i++) {
            adjacencyList.add(Integer.parseInt(splittedLine[i]));
        }
        return new Vertex(id, adjacencyList);
    }

}
