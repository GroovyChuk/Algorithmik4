package Graph.GUI;

import javax.swing.*;

import Graph.Utilities.Constants;

import java.awt.*;

public class JMainFrame extends JFrame {

    private static final long serialVersionUID = -2500396438164019657L;
    JGraphPanel jGraphPanel = new JGraphPanel();
    JLeftMenuPanel jLeftMenuPanel = new JLeftMenuPanel(jGraphPanel);

    public JMainFrame() throws HeadlessException {
        super("justGraphX");
        this.setSize(new Dimension(Constants.FRAME_SIZE_X,
                Constants.FRAME_SIZE_Y));
        this.add(jLeftMenuPanel, BorderLayout.WEST);
        this.add(jGraphPanel);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
