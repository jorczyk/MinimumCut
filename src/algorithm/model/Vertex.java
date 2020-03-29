package algorithm.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vertex {

    private Integer id;
    private List<Integer> adjacencyList;

    public Vertex(Integer id, List<Integer> adjacencyList) {
        this.id = id;
        this.adjacencyList = adjacencyList;
    }

    public Integer getId() {
        return id;
    }

    public List<Integer> getAdjacencyList() {
        return adjacencyList;
    }

    public static Vertex ofTwoVertices(Vertex tail, Vertex head, Integer id) {
        return new Vertex(id, mergeAdjacencyLists(tail, head));
    }

    private static List<Integer> mergeAdjacencyLists(Vertex a, Vertex b) {
        //TODO: re-implement
        //remove all self loops
        //iterate over all external and change refs


        Set<Integer> setA = new HashSet<>();
        setA.addAll(a.getAdjacencyList());
        setA.remove(b.getId());
        Set<Integer> setB = new HashSet<>();
        setB.addAll(b.getAdjacencyList());
        setB.remove(a.getId());
        return Stream.concat(setA.stream(), setB.stream()).collect(Collectors.toSet());
    }
}
