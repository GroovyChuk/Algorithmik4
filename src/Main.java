import Graph.GUI.JMainFrame;
import Graph.Node;
import Graph.Utilities.CSVReader;
import Graph.Utilities.Constants;

import java.util.PriorityQueue;


public class Main {

    public static void main(String [] arg){
        CSVReader.getNodeList();

        new JMainFrame();
    }
}
