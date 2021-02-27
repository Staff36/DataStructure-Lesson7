package com.Lesso7;

import java.util.Objects;


public class Vertex {
    private Vertex previousVertex;
    boolean isVisited;
    private String value;


    private boolean isVisited() {
        return isVisited;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getValue() {
        return value;
    }

    public Vertex(String value) {
        this.value = value;
        isVisited=false;
    }

    public void setValue(String value) {
        this.value = value;

    }

    @Override
    public String toString() {
        return value;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
