package algorithm.impl;

import algorithm.model.Edge;
import algorithm.model.Graph;
import algorithm.model.Vertex;
import reader.DataReader;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RandomizedContractionSolver {

//    code up and run the randomized contraction algorithm for the min cut problem and use
//    it on the above graph to compute the min cut. (HINT: Note that you'll have to figure out an
//    implementation of edge contractions. Initially, you might want to do this naively, creating a new graph
//    from the old every time there's an edge contraction. But you should also think about more e?icient
//    implementations.)
//    (WARNING: As per the video lectures, please make sure to run the algorithm many
//    times with di?erent random seeds, and remember the smallest cut that you ever find.)

    private String inputFilePath;
    private int lowestMinCut;

    public RandomizedContractionSolver(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        this.lowestMinCut = Integer.MAX_VALUE;
    }

    public int calculateMinCut() {
        Graph graph = DataReader.getGraphFromFile(this.inputFilePath);
        int numberOfIterations = (int)(Math.pow(graph.getVertices().size(), 2) * Math.log(graph.getVertices().size()));
        for(int i=0;i<numberOfIterations;i++){
            Graph copyGraph = DataReader.getGraphFromFile(this.inputFilePath);
            int tempScore = mainLoop(copyGraph);
            if(tempScore < lowestMinCut) {
                lowestMinCut = tempScore;
            }
        }
        return lowestMinCut;
    }

    private int mainLoop(Graph graph) {
        while(graph.getVertices().size() > 2) {
            Edge randomPickedEdge = pickRandomEdge(graph.getEdges());
            graph = graph.contract(randomPickedEdge);
        }
        return graph.getEdges().size();
    }

    private Edge pickRandomEdge(List<Edge> edges) {
        Random rand = new Random(System.currentTimeMillis());
        int index = rand.nextInt(edges.size()-1);
        return edges.get(index);
    }

}
