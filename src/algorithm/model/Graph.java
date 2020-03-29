package algorithm.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

    private Map<Integer, Vertex> vertices;
    private List<Edge> edges;

    public Graph(Map<Integer, Vertex> vertices) {
        this.vertices = vertices;
        this.edges = vertices.values().stream().map(this::createEdgesFormVertex).flatMap(Set::stream).collect(Collectors.toList());
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void contract(Edge edge) {
        this.edges.remove(edge);
        this.vertices.remove(edge.getHeadId());
        this.vertices.remove(edge.getTailId());
        Vertex vertexA = vertices.get(edge.getTailId());
        Vertex vertexB = vertices.get(edge.getHeadId());
        Integer newId = getHighestId();
        this.vertices.put(newId, Vertex.ofTwoVertices(vertexA, vertexB, newId));
    }

    private Set<Edge> createEdgesFormVertex(Vertex vertex) {
        return vertex.getAdjacencyList().stream()
                .filter(id -> vertex.getId() < id) //adding only from smaller to bigger to avoid duplicates
                .map(id -> Edge.createEdgeForSingleAdjacency(vertex, id)).collect(Collectors.toSet());
    }

    private Integer getHighestId() {
        return this.vertices.keySet().stream().max(Integer::compare).get();
    }

}
