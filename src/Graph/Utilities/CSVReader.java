package Graph.Utilities;

import Graph.Edge;
import Graph.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kalaman on 09.01.17.
 */
public class CSVReader {

    public static HashMap<String,Node> nodeHashMap = new HashMap<>();

    public static ArrayList<Node> getNodeList ()
    {
        ArrayList<Node> nodeArrayList = new ArrayList<>();

        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader("Beispielgraph.csv"));

            //Creating Nodes
            while ((line = br.readLine()) != null && !line.equals("?")) {

                String[] nodeData = line.split(",");

                nodeData[1] = nodeData[1].replaceAll(" ","");
                nodeData[2] = nodeData[2].replaceAll(" ","");

                Node newNode = new Node(nodeData[0],Integer.parseInt(nodeData[1]),Integer.parseInt(nodeData[2]));
                nodeArrayList.add(newNode);
                nodeHashMap.put(nodeData[0],newNode);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return nodeArrayList;
    }

    public static ArrayList<Edge> getEdgeList ()
    {
        ArrayList<Edge> edgeArrayList = new ArrayList<>();

        if (nodeHashMap.size() == 0)
            getNodeList();

        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader("Beispielgraph.csv"));

            //Creating Nodes
            while ((line = br.readLine()) != null && !line.equals("?")) {
                //Do nothing
            }

            //Adding Edges
            while ((line = br.readLine()) != null) {

                String[] nodeData = line.split(",");

                nodeData[1] = nodeData[1].replaceAll(" ","");

                Edge newEdge = new Edge(nodeHashMap.get(nodeData[0]),nodeHashMap.get(nodeData[1]));
                edgeArrayList.add(newEdge);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return edgeArrayList;
    }

}
