package algorithm.model;

import java.util.ArrayList;
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

    public void upinVertexReference(Vertex vertex) {
        this.adjacencyList = this.adjacencyList.stream()
                .filter(index -> !index.equals(vertex.getId()))
                .collect(Collectors.toList());
    }

    public void addToAdjacencyList(Integer id) {
        this.adjacencyList.add(id);
    }

    private static List<Integer> mergeAdjacencyLists(Vertex a, Vertex b) {
        List<Integer> listA = new ArrayList<>();
        listA.addAll(a.getAdjacencyList());
        List<Integer> listB = new ArrayList<>();
        listB.addAll(b.getAdjacencyList());
        return Stream.concat(listA.stream(), listB.stream()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", adjacencyList=" + adjacencyList +
                '}';
    }
}
