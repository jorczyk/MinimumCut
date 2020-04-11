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
        this.edges = vertices.values()
                .stream()
                .map(this::createEdgesFormVertex)
                .flatMap(Set::stream)
                .collect(Collectors.toList());
    }

    public Map<Integer, Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Graph contract(Edge edge) {
        int contractedVertexId = createContractedVertex(edge);
        this.edges.remove(edge);
        redirectEdges(edge, contractedVertexId);
        return this;
    }

    private void redirectEdges(Edge edge, int newId) {
        this.edges.forEach(e -> e.redirect(edge, newId));
    }

    private int createContractedVertex(Edge edge) {
        Vertex vertexA = vertices.get(edge.getTailId());
        Vertex vertexB = vertices.get(edge.getHeadId());
        Integer newId = getHighestId()+1;
        Vertex newVertex = Vertex.ofTwoVertices(vertexA, vertexB, newId);
        vertexA.getAdjacencyList().forEach(index -> vertices.get(index).upinVertexReference(vertexA));
        vertexB.getAdjacencyList().forEach(index -> vertices.get(index).upinVertexReference(vertexB));
        newVertex.getAdjacencyList().forEach(index -> vertices.get(index).addToAdjacencyList(newId));
        this.vertices.put(newId, newVertex);
        return newId;
    }

    private Set<Edge> createEdgesFormVertex(Vertex vertex) {
        return vertex.getAdjacencyList().stream()
                .filter(id -> vertex.getId() < id) //adding only from smaller to bigger to avoid duplicates
                .map(id -> Edge.createEdgeForSingleAdjacency(vertex, id)).collect(Collectors.toSet());
    }

    private Integer getHighestId() {
        return this.vertices.keySet().stream().max(Integer::compare).orElse(-1);
    }

}
