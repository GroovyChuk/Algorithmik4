package Graph.GUI;

import Graph.Edge;
import Graph.Node;
import Graph.Utilities.CSVReader;
import Graph.Utilities.Constants;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class JGraphPanel extends JPanel{

    ArrayList<Node> nodeArrayList;
    ArrayList<Edge> edgeArrayList;


    public JGraphPanel()
    {
        this.setPreferredSize(new Dimension(Constants.PANEL_GRAPH_SIZE_X,Constants.PANEL_GRAPH_SIZE_Y));

        nodeArrayList = CSVReader.getNodeList();
        edgeArrayList = CSVReader.getEdgeList();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        Color defaultColor = g.getColor();

        for(Edge currentEdge:edgeArrayList){
            int pointX1 = currentEdge.getNode1().getPosX() * Constants.SIZE_MULTIPLIKATOR;
            int pointY1 = currentEdge.getNode1().getPosY() * Constants.SIZE_MULTIPLIKATOR;

            int pointX2 = currentEdge.getNode2().getPosX() * Constants.SIZE_MULTIPLIKATOR;
            int pointY2 = currentEdge.getNode2().getPosY() * Constants.SIZE_MULTIPLIKATOR;

            FontMetrics fm = g.getFontMetrics();
            double textWidth = fm.getStringBounds(currentEdge.toString(), g).getWidth();

            g.drawLine(pointX1,pointY1,pointX2,pointY2);
            g.setColor(Color.BLUE);
            g.drawString(currentEdge.toString(),(int)((pointX1+pointX2)/2) - (int)(textWidth/2),(int)((pointY1+pointY2)/2) );
            g.setColor(defaultColor);
        }


        for(Node currentNode:nodeArrayList){
            int positionX = currentNode.getPosX() * Constants.SIZE_MULTIPLIKATOR;
            int positionY = currentNode.getPosY() * Constants.SIZE_MULTIPLIKATOR;

            int [] color = currentNode.getColor();
            g.setColor(new Color (color[0],color[1],color[2]));
            g.fillOval(positionX-(Constants.NODE_WIDTH/2),positionY-(Constants.NODE_HEIGHT/2), Constants.NODE_WIDTH,Constants.NODE_HEIGHT);
            g.setColor(Color.WHITE);

            FontMetrics fm = g.getFontMetrics();
            double textWidth = fm.getStringBounds(currentNode.toString(), g).getWidth();

            g.drawString(currentNode.toString(),(int)(positionX-textWidth/2),(int)(positionY+fm.getAscent()/2));

        }


    }

}
