package algorithm.impl;

import algorithm.model.Edge;
import algorithm.model.Graph;

/**
 *
 */
public class RandomizedContractionSolver {

//    code up and run the randomized contraction algorithm for the min cut problem and use
//    it on the above graph to compute the min cut. (HINT: Note that you'll have to figure out an
//    implementation of edge contractions. Initially, you might want to do this naively, creating a new graph
//    from the old every time there's an edge contraction. But you should also think about more e?icient
//    implementations.)
//    (WARNING: As per the video lectures, please make sure to run the algorithm many
//    times with di?erent random seeds, and remember the smallest cut that you ever find.)

    private int numberOfIterations;
    private int lowestMinCut = Integer.MAX_VALUE;

    public int calculateMinCut(file) {
        numberOfIterations = (int)Math.pow(Vertices.size(), 3);
        for(int i=0;i<numberOfIterations;i++){
            MyReader reader = new MyReader(file);
            Graph copyGraph = new Graph(vertices);
            int tempScore = mainLoop(copyGraph);
            if(tempScore < lowestMinCut) {
                lowestMinCut = tempScore;
            }
        }
    }

    private int mainLoop(Graph graph) {
        while(graph.getVertices().size() > 2) {
            Edge randomPickedEdge = pickRandomEdge();
            graph.contract(randomPickedEdge);
        }
        return graph.getEdges().size();
    }

    private Edge pickRandomEdge() {
        return null;
    }

}
