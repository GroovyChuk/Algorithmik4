package Graph.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

import Graph.Edge;
import Graph.Node;
import Graph.GUI.JGraphPanel;

public class Algorithm {
    static Node u = Constants.DEFAULT_NODE;

    public static void graphMCST(Node startNode, JGraphPanel p) {
        u = Constants.DEFAULT_NODE;

        for (Node currentNode : p.getNodeArrayList()) {
            currentNode.setKey(Constants.INFINITY);
            currentNode.setPi(Constants.DEFAULT_NODE);
            currentNode.setMarked(false);
        }
        startNode.setKey(0);
        startNode.setPi(Constants.DEFAULT_NODE);

        PriorityQueue<Node> minHeap = new PriorityQueue<Node>();

        for (Node currentNode : p.getNodeArrayList()) {
            minHeap.add(currentNode);
        }

        while (!minHeap.isEmpty()) {
            u = minHeap.peek();

            for (Edge currentEdge : u.getEdges(p)) {
                Node neighbour = currentEdge.getNode2();
                if (currentEdge.getWeight() < neighbour.getKey()
                        && !neighbour.isMarked()) {
                    minHeap.remove(neighbour);
                    currentEdge.getNode2().setKey(currentEdge.getWeight());
                    currentEdge.getNode2().setPi(u);
                    minHeap.add(neighbour);
                }

            }
            minHeap.remove(u);
            u.setMarked(true);
        }
        p.setDrawPi(true);
    }

    public static ArrayList<Edge> graphDijkstra(Node startNode, Node endNode, JGraphPanel p) {
        u = Constants.DEFAULT_NODE;

        ArrayList<Edge> path = new ArrayList<Edge>();
        Stack<Node> s = null;
        Node x = Constants.DEFAULT_NODE;

        for (Node currentNode : p.getNodeArrayList()) {
            currentNode.setKey(Constants.INFINITY);
            currentNode.setPi(Constants.DEFAULT_NODE);
            currentNode.setMarked(false);
        }
        startNode.setKey(0);
        startNode.setPi(startNode);

        PriorityQueue<Node> minHeap = new PriorityQueue<Node>();

        for (Node currentNode : p.getNodeArrayList()) {
            minHeap.add(currentNode);
        }

        while (!minHeap.isEmpty()) {
            u = minHeap.peek();
            double sum = 0;
            for (Edge currentEdge : u.getEdges(p)) {
                Node neighbour = currentEdge.getNode2();

                if (neighbour.getKey() > (sum = currentEdge
                        .getWeight() + u.getKey()) && !neighbour.isMarked()) {
                    minHeap.remove(neighbour);
                    neighbour.setKey(sum);
                    neighbour.setPi(u);
                    minHeap.add(neighbour);
                }

            }
            minHeap.remove(u);
            u.setMarked(true);
        }

        x = endNode;
        while (!x.equals(startNode)) {
            path.add(new Edge(x, x.getPi()));
            x = x.getPi();
        }
        return path;
    }
}
