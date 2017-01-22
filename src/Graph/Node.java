package Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Graph.GUI.JGraphPanel;
import Graph.Utilities.Constants;

public class Node implements Comparable<Node> {

    private String name;
    private Point xyPosition;

    private Node pi = Constants.DEFAULT_NODE;
    private double key = Constants.INFINITY;

    private boolean marked;
    private int[] color = new int[3];

    public Constants.NODE_TYPE nodeType;

    private ArrayList<Edge> edges;

    public Node(String name, int x, int y) {
        this.name = name;
        this.xyPosition = new Point(x, y);
        this.edges = new ArrayList<Edge>();
        this.key = Constants.INFINITY;
        this.pi = Constants.DEFAULT_NODE;
        changeNodeType(Constants.NODE_TYPE.NODE_NORMAL);
    }

    public ArrayList<Edge> getEdges(JGraphPanel p){
        if (!edges.isEmpty())
            return edges;
        else{
            for(Edge currentEdge : p.getEdgeArrayList()){
                if(currentEdge.getNode1().equals(this))
                    edges.add(currentEdge);
                else if(currentEdge.getNode2().equals(this)){
                    edges.add(new Edge(this,currentEdge.getNode1()));
                }
            }
            return edges;
        }
    }

    public Node(String name, int x, int y, boolean marked) {
        this(name, x, y);
        markNode(marked);
    }

    public void markNode(boolean mark) {
        this.setMarked(mark);
    }

    public int getPosX() {
        return (int) xyPosition.getX();
    }

    public int getPosY() {
        return (int) xyPosition.getY();
    }

    public Node getPi() {
        return pi;
    }

    public void setPi(Node pi) {
        this.pi = pi;
    }

    public void setKey(double key) {
        this.key = key;
    }

    public double getKey() {
        return key;
    }

    public String toString() {
        return this.name;
    }

    public void changeNodeType(Constants.NODE_TYPE newNodeType) {
        this.nodeType = newNodeType;

        switch (nodeType) {
            case NODE_START:
                color = Constants.NODE_START_COLOR;
                break;
            case NODE_END:
                color = Constants.NODE_END_COLOR;
                break;
            case NODE_NORMAL:
                color = Constants.NODE_NORMAL_COLOR;
                break;
        }
    }

    public int[] getColor() {
        return this.color;
    }

    @Override
    public int compareTo(Node n) {
        if (this.key == n.key)
            return 0;
        else if (this.key > n.key)
            return 1;
        else
            return -1;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
