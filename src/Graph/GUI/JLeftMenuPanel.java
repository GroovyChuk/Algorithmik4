package Graph.GUI;

import Graph.Node;
import Graph.Utilities.CSVReader;
import Graph.Utilities.Constants;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JLeftMenuPanel extends JPanel{

    JButton jButtonStartAlgorithm;

    JTextField jStartNodeTextField;
    JTextField jEndNodeTextField;

    JLabel jStartNodeLabel;
    JLabel jEndNodeLabel;

    JComboBox jAlgorithmComboBox;

    JPanel boxLayoutPanel;
    public JGraphPanel jGraphPanel;

    public static JTextArea menuConsole;

    public static Node startNode = Constants.DEFAULT_NODE;
    public static Node endNode = Constants.DEFAULT_NODE;



    public JLeftMenuPanel(JGraphPanel jGraphPanel)
    {
        this.setPreferredSize(new Dimension(Constants.PANEL_LEFT_MENU_SIZE_X, Constants.PANEL_LEFT_MENU_SIZE_Y));
        this.setLayout(new BorderLayout());

        initComponents();
        addBoxLayout();
        addFlowLayout();
        addTextFieldListeners();
        addComboBoxListeners();

        writeToConsole("Ready ...");

        this.jGraphPanel = jGraphPanel;
        this.add(jButtonStartAlgorithm,BorderLayout.SOUTH);
    }

    private void addBoxLayout() {
        boxLayoutPanel = new JPanel();
        boxLayoutPanel.setLayout(new BoxLayout(boxLayoutPanel,BoxLayout.Y_AXIS));

        //Lets the console auto scroll
        DefaultCaret caret = (DefaultCaret)menuConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane scrollPane = new JScrollPane(menuConsole);

        this.add(scrollPane,BorderLayout.NORTH);
        this.add(boxLayoutPanel);
    }

    private void addFlowLayout()
    {
        JPanel jPanelNodeStart = new JPanel();
        JPanel jPanelNodeEnd = new JPanel();
        JPanel jPanelAlgorithm = new JPanel();

        jPanelNodeStart.setMaximumSize(new Dimension(Constants.PANEL_LEFT_MENU_SIZE_X,40));
        jPanelNodeEnd.setMaximumSize(new Dimension(Constants.PANEL_LEFT_MENU_SIZE_X,40));

        jPanelNodeStart.setLayout(new FlowLayout());
        jPanelNodeEnd.setLayout(new FlowLayout());
        jPanelAlgorithm.setLayout(new FlowLayout());

        jPanelNodeStart.add(this.jStartNodeLabel);
        jPanelNodeStart.add(this.jStartNodeTextField);

        jPanelNodeEnd.add(this.jEndNodeLabel);
        jPanelNodeEnd.add(this.jEndNodeTextField);

        jPanelAlgorithm.add(new JLabel("Algorithm"));
        jPanelAlgorithm.add(this.jAlgorithmComboBox);

        this.boxLayoutPanel.add(jPanelNodeStart);
        this.boxLayoutPanel.add(jPanelNodeEnd);
        this.boxLayoutPanel.add(jPanelAlgorithm);

        //boxLayoutPanel;
    }

    public static void writeToConsole (String message){
        if (menuConsole.getText().length() > 0)
            menuConsole.setText(menuConsole.getText() + "\n-> " + message);
        else
            menuConsole.setText("-> " + message);
    }

    public void initComponents()
    {
        jButtonStartAlgorithm = new JButton("Start");
        jStartNodeLabel = new JLabel("Start Node");
        jEndNodeLabel = new JLabel("End Node");
        jStartNodeTextField = new JTextField(16);
        jEndNodeTextField = new JTextField(16);
        jAlgorithmComboBox = new JComboBox(Constants.ALGORITHMS);

        jButtonStartAlgorithm.setEnabled(false);

        menuConsole = new JTextArea(13,1);
        menuConsole.setSize(new Dimension(Constants.PANEL_LEFT_MENU_SIZE_X,120));
        menuConsole.setEditable(false);
    }

    private void addComboBoxListeners ()
    {
        jAlgorithmComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToConsole(e.getActionCommand());
            }
        });
    }

    private void addTextFieldListeners ()
    {
        jStartNodeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateGraph(e, Constants.NODE_TYPE.NODE_START);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateGraph(e, Constants.NODE_TYPE.NODE_START);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        jEndNodeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateGraph(e, Constants.NODE_TYPE.NODE_END);

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateGraph(e, Constants.NODE_TYPE.NODE_END);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

    }

    private void updateGraph(DocumentEvent e, Constants.NODE_TYPE nodeType)
    {
        try {
            String input = e.getDocument().getText(0, e.getDocument().getLength()).toString().toUpperCase();
            if (CSVReader.nodeHashMap.containsKey(input))
            {
                Node node = CSVReader.nodeHashMap.get(input);
                node.changeNodeType(nodeType);

                switch (nodeType)
                {
                    case NODE_START:
                        startNode = node;
                        break;
                    case NODE_END:
                        endNode = node;
                        break;
                }

                writeToConsole("Select Node " + input + " as " + nodeType);

                jGraphPanel.repaint();
            }
            else
            {
                switch (nodeType)
                {
                    case NODE_START:
                        startNode.changeNodeType(Constants.NODE_TYPE.NODE_NORMAL);
                        jGraphPanel.repaint();
                        break;
                    case NODE_END:
                        endNode.changeNodeType(Constants.NODE_TYPE.NODE_NORMAL);
                        jGraphPanel.repaint();
                        break;
                }
            }

        }catch(Exception ee){
            ee.printStackTrace();
        }

        if (startNode.nodeType == Constants.NODE_TYPE.NODE_START
                && endNode.nodeType == Constants.NODE_TYPE.NODE_END)
            jButtonStartAlgorithm.setEnabled(true);
        else
            jButtonStartAlgorithm.setEnabled(false);

    }

}
