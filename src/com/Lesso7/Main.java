package com.Lesso7;

public class Main {

    public static void main(String[] args) {
        Graph graph= createGraph();
        System.out.println("Size graph is "+graph.getSize());
        graph.display();
        System.out.println("\nThe shortest way is: \n"+graph.findTheShortRoad("Moscow","Voronezh"));
    }
    public static Graph createGraph(){
        Graph graph= new Graph(10);
        graph.addVertex("Moscow");
        graph.addVertex("Tula");
        graph.addVertex("Lipetsk");
        graph.addVertex("Voronezh");
        graph.addVertex("Ryazan");
        graph.addVertex("Tambov");
        graph.addVertex("Saratov");
        graph.addVertex("Kaluga");
        graph.addVertex("Orel");
        graph.addVertex("Kursk");

        graph.addEdges("Moscow","Tula","Ryazan","Kaluga" );
        graph.addEdges("Voronezh","Lipetsk","Saratov","Kursk");
        graph.addEdges("Tula","Lipetsk");
        graph.addEdges("Ryazan","Tambov");
        graph.addEdges("Tambov","Saratov");
        graph.addEdges("Kaluga","Orel");
        graph.addEdges("Orel","Kursk");
        return graph;
    }

}
