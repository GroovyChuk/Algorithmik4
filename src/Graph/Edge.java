package Graph;

import java.util.HashMap;


public class Edge {

    double value;

    Node node1;
    Node node2;

    public Edge (Node node1,Node node2){
        this.value = Math.sqrt(Math.pow((node2.getPosX()-node1.getPosX()),2) + Math.pow((node2.getPosY()-node1.getPosY()),2));

        this.node1 = node1;
        this.node2 = node2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }

    public String toString()
    {
        return String.valueOf((int)value);
    }

    public double getWeight() {
        return value;
    }
}
