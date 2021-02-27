package com.Lesso7;

import java.util.*;

public class Graph {
    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;


    public Graph(int maxVertexCount) {
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
        this.vertexList= new ArrayList<Vertex>(maxVertexCount);
    }

    public void addVertex(String label){
        vertexList.add(new Vertex(label));
    }
    public void addEdge(String startLabel, String endLabel){
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);
        if (startIndex == -1 || endIndex == -1) {
        throw new IllegalArgumentException("Invalid label for index");
        }
        adjMat[startIndex][endIndex]=true;
        adjMat[endIndex][startIndex]=true;
    }

    private int indexOf(String label){
        for (int i = 0; i < getSize(); i++) {
            if (label.equals(vertexList.get(i).getValue())){
                return i;
            }
        }
        return -1;
    }
    public void addEdges(String startLabel, String secondLabel, String ... labels){
        addEdge(startLabel,secondLabel);
        for (String label:labels){
            addEdge(startLabel,label);
        }
    }
    public int getSize(){
        return vertexList.size();
    }
    public void display(){
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]){
                    System.out.print("-> "+vertexList.get(j));
                }
            }
            System.out.println();

        }
    }
    public void depthFirstSearch(String startLabel){
        int startIndex= indexOf(startLabel);
        if (startIndex==-1){
            throw new IllegalArgumentException("Invalid start label");
        }
        Stack<Vertex> stack= new Stack<>();
        Vertex vertex=vertexList.get(startIndex);
        visitVertex(stack, vertex,true);
        while (!stack.isEmpty()){
            vertex= getNearUnvisitedVertex(stack.peek());
            if (vertex!=null){
                visitVertex(stack,vertex,true);
            }else {
                stack.pop();
            }
        }
        resetVertexState();
    }

    private void resetVertexState() {
        for (Vertex vertex:vertexList) {
            vertex.setVisited(false);
            vertex.setPreviousVertex(null);
        }
    }
    private Vertex getNearUnvisitedVertex(Vertex current) {
        int currentIndex= vertexList.indexOf(current);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[currentIndex][i]&&!vertexList.get(i).isVisited){
               return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex,boolean display) {
        System.out.println(vertex);
        vertex.setVisited(true);
        stack.push(vertex);
    }
    private void visitVertex(Queue<Vertex> queue, Vertex vertex, boolean display) {
       if (display)
        System.out.println(vertex);

        vertex.setVisited(true);
        queue.add(vertex);
    }

    public void breadthFirstSearch(String startLabel){
        int startIndex= indexOf(startLabel);

        if (startIndex==-1){
            throw new IllegalArgumentException("Invalid start label");
        }
        Queue<Vertex> queue= new LinkedList<>();
        Vertex vertex=vertexList.get(startIndex);

        visitVertex(queue, vertex,true);
        while (!queue.isEmpty()){
            vertex= getNearUnvisitedVertex(queue.peek());
            if (vertex!=null){
                visitVertex(queue,vertex,true);
            }else {
                queue.remove();
            }
        }
        resetVertexState();
    }
    public List<Vertex> findTheShortRoad(String startLabel, String endLabel){
        int startIndex= indexOf(startLabel);
        ArrayList<Vertex> shortWay = new ArrayList<>();
        if (startIndex==-1)
            throw new IllegalArgumentException("Invalid start label");
        Queue<Vertex> queue= new LinkedList<>();
        Vertex vertex=vertexList.get(startIndex);
        visitVertex(queue, vertex,false);
        Vertex previousVertex=vertex;

        while (!queue.isEmpty()){
         vertex= getNearUnvisitedVertex(queue.peek());
            if (vertex!=null){
                vertex.setPreviousVertex(previousVertex);
                visitVertex(queue,vertex,false);
                if (vertex.getValue().equals(endLabel)){
                    break;
                }
            }else {
                queue.remove();
                previousVertex=queue.peek();
            }
        }
        while (vertex!=null){
            shortWay.add(vertex);
            vertex=vertex.getPreviousVertex();
        }
        Collections.reverse(shortWay);

        resetVertexState();
        return shortWay;
    }

}
