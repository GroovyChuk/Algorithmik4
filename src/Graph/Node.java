package Graph;

import Graph.Utilities.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Node {

    private String name;
    private Point xyPosition;

    private boolean marked;
    private int [] color = new int [3];

    public Constants.NODE_TYPE nodeType;

    private ArrayList<Edge> edges;

    public Node (String name,int x,int y){
        this.name = name;
        this.xyPosition = new Point(x,y);
        this.edges = new ArrayList<>();

        changeNodeType(Constants.NODE_TYPE.NODE_NORMAL);
    }

    public Node (String name,int x,int y,boolean marked){
        this(name,x,y);
        markNode(marked);
    }

    public void markNode (boolean mark)
    {
        this.marked = mark;
    }

    public int getPosX ()
    {
        return (int)xyPosition.getX();
    }

    public int getPosY ()
    {
        return (int)xyPosition.getY();
    }

    public String toString()
    {
        return this.name;
    }

    public void changeNodeType (Constants.NODE_TYPE newNodeType){
        this.nodeType = newNodeType;

        switch (nodeType)
        {
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

    public int [] getColor ()
    {
        return this.color;
    }
}
