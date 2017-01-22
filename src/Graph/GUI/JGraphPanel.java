package Graph.GUI;

import Graph.*;
import Graph.Utilities.CSVReader;
import Graph.Utilities.Constants;

import javax.swing.*;

import java.awt.*;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

public class JGraphPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<Node> nodeArrayList;
    private ArrayList<Edge> edgeArrayList;
    private ArrayList<Edge> dijkstraPath;
    private boolean drawPi;

    public JGraphPanel() {
        this.setPreferredSize(new Dimension(Constants.PANEL_GRAPH_SIZE_X,
                Constants.PANEL_GRAPH_SIZE_Y));

        nodeArrayList = CSVReader.getNodeList();
        edgeArrayList = CSVReader.getEdgeList();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color defaultColor = g.getColor();

        for (Edge currentEdge : edgeArrayList) {
            int pointX1 = currentEdge.getNode1().getPosX()
                    * Constants.SIZE_MULTIPLIKATOR;
            int pointY1 = currentEdge.getNode1().getPosY()
                    * Constants.SIZE_MULTIPLIKATOR;

            int pointX2 = currentEdge.getNode2().getPosX()
                    * Constants.SIZE_MULTIPLIKATOR;
            int pointY2 = currentEdge.getNode2().getPosY()
                    * Constants.SIZE_MULTIPLIKATOR;

            FontMetrics fm = g.getFontMetrics();
            double textWidth = fm.getStringBounds(currentEdge.toString(), g)
                    .getWidth();

            g.drawLine(pointX1, pointY1, pointX2, pointY2);
            g.setColor(Color.BLUE);
            g.drawString(currentEdge.toString(),
                    (int) ((pointX1 + pointX2) / 2) - (int) (textWidth / 2),
                    (int) ((pointY1 + pointY2) / 2));
            g.setColor(defaultColor);
        }

        // Dijkstra
        if (dijkstraPath != null) {
            for (Edge currenEdge : dijkstraPath) {
                g.setColor(Color.RED);
                g.drawLine(currenEdge.getNode1().getPosX() * Constants.SIZE_MULTIPLIKATOR,
                        currenEdge.getNode1().getPosY() * Constants.SIZE_MULTIPLIKATOR,
                        currenEdge.getNode2().getPosX() * Constants.SIZE_MULTIPLIKATOR,
                        currenEdge.getNode2().getPosY()	* Constants.SIZE_MULTIPLIKATOR);
            }
        }

        // MCST
        for (Node currentNode : nodeArrayList) {
            // draw lines to parent after algorithm was executed
            if (drawPi && !currentNode.nodeType.equals(Constants.NODE_TYPE.NODE_START)) {
                g.setColor(Color.RED);
                g.drawLine(
                        currentNode.getPosX() * Constants.SIZE_MULTIPLIKATOR,
                        currentNode.getPosY() * Constants.SIZE_MULTIPLIKATOR,
                        currentNode.getPi().getPosX() * Constants.SIZE_MULTIPLIKATOR,
                        currentNode.getPi().getPosY() * Constants.SIZE_MULTIPLIKATOR);
            }
        }
        drawPi = false;

        for (Node currentNode : nodeArrayList) {

            int positionX = currentNode.getPosX()
                    * Constants.SIZE_MULTIPLIKATOR;
            int positionY = currentNode.getPosY()
                    * Constants.SIZE_MULTIPLIKATOR;

            int[] color = currentNode.getColor();

            g.setColor(new Color(color[0], color[1], color[2]));
            g.fillOval(positionX - (Constants.NODE_WIDTH / 2), positionY
                            - (Constants.NODE_HEIGHT / 2), Constants.NODE_WIDTH,
                    Constants.NODE_HEIGHT);

            g.setColor(Color.WHITE);

            FontMetrics fm = g.getFontMetrics();
            double textWidth = fm.getStringBounds(currentNode.toString(), g)
                    .getWidth();

            g.drawString(currentNode.toString(),
                    (int) (positionX - textWidth / 2),
                    (int) (positionY + fm.getAscent() / 2));

        }

    }

    public ArrayList<Edge> getEdgeArrayList() {
        return edgeArrayList;
    }

    public ArrayList<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    public boolean getDrawPi() {
        return drawPi;
    }

    public void setDrawPi(boolean drawPi) {
        this.drawPi = drawPi;
    }

    public ArrayList<Edge> getDijkstraPath() {
        return dijkstraPath;
    }

    public void setDijkstraPath(ArrayList<Edge> dijkstraPath) {
        this.dijkstraPath = dijkstraPath;
    }
}
