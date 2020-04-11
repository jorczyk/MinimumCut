package algorithm.model;

import java.util.Objects;

public class Edge {

    private Integer tailId;
    private Integer headId;

    public static Edge createEdgeForSingleAdjacency(Vertex source, Integer vertexId) {
        return new Edge(source.getId(), vertexId);
    }

    public Edge(Integer tailId, Integer headId) {
        this.tailId = tailId;
        this.headId = headId;
    }

    public Integer getTailId() {
        return tailId;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void redirect(Edge other, int newId) {
        if(this.getTailId().equals(other.getTailId()) || this.getTailId().equals(other.getHeadId()))
            this.tailId = newId;
        if(this.getHeadId().equals(other.getTailId()) || this.getHeadId().equals(other.getHeadId()))
            this.headId = newId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (tailId.equals(edge.tailId) && headId.equals(edge.headId)) ||
                (tailId.equals(edge.headId) && headId.equals(edge.tailId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(tailId, headId);
    }

}
