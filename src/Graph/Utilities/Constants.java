package Graph.Utilities;

import Graph.Node;

/**
 * Created by Jok3r on 13.01.2017.
 */
public class Constants {

    public static final int SIZE_MULTIPLIKATOR = 7;

    public static final int FRAME_SIZE_X = 150 * SIZE_MULTIPLIKATOR;
    public static final int FRAME_SIZE_Y = 100 * SIZE_MULTIPLIKATOR;

    public static final int PANEL_LEFT_MENU_SIZE_X = 40 * SIZE_MULTIPLIKATOR;
    public static final int PANEL_LEFT_MENU_SIZE_Y = FRAME_SIZE_Y;

    public static final int PANEL_GRAPH_SIZE_X = FRAME_SIZE_X - PANEL_LEFT_MENU_SIZE_X;
    public static final int PANEL_GRAPH_SIZE_Y = FRAME_SIZE_Y;

    public static final int PANEL_STATUS_BAR_SIZE_X = FRAME_SIZE_X;
    public static final int PANEL_STATUS_BAR_SIZE_Y = 4 * SIZE_MULTIPLIKATOR;

    public static final int NODE_WIDTH = 25;
    public static final int NODE_HEIGHT = NODE_WIDTH;


    public static final int [] NODE_START_COLOR = {
            115,
            225,
            0
    };
    public static final int [] NODE_NORMAL_COLOR = {
            107,
            107,
            106
    };

    public static final int [] NODE_END_COLOR = {
            255,
            209,
            0
    };

    public enum NODE_TYPE {
        NODE_START,
        NODE_END,
        NODE_NORMAL
    }

    public static final String ALGORITHM_DIJKSTRA = "Dijkstra";
    public static final String ALGORITHM_MCST = "MCST";

    public static final String [] ALGORITHMS = {ALGORITHM_MCST,ALGORITHM_DIJKSTRA};

    public static final Node DEFAULT_NODE = new Node("-1",1337,1337);

}
