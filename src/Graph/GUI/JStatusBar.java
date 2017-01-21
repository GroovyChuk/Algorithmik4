package Graph.GUI;

import javax.swing.*;
import java.awt.*;
import Graph.Utilities.Constants;


public class JStatusBar extends JPanel{

    public static JLabel jStatusLabel = new JLabel();

    public JStatusBar ()
    {
        this.setPreferredSize(new Dimension(Constants.PANEL_STATUS_BAR_SIZE_X, Constants.PANEL_STATUS_BAR_SIZE_Y));
        this.setLayout(new BorderLayout());
        this.add(jStatusLabel,BorderLayout.WEST);

        writeToStatusBar("Ready");
    }

    public static void writeToStatusBar (String status)
    {
        jStatusLabel.setText(" ["+ status + "]");
    }
}
