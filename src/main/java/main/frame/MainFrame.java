package main.frame;

import main.panels.ConfigurationPanel;
import main.panels.OutputPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public ConfigurationPanel configPanel;
    public OutputPanel canvas;
    public String continentText = "";
    public String countryText = "";

    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int height = screenSize.height;
    public static final int width = screenSize.width;

    public MainFrame() throws HeadlessException {
        super("SnakeCLEF");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(900, 600));

        this.setSize(width, height); //for setting the object's size

        int xCoordinate = (screenSize.width - this.getWidth()) / 2; //in the centre of the screen
        int yCoordinate = (screenSize.height - this.getHeight()) / 2; //in the centre of the screen

        //Set the new frame's location
        this.setLocation(xCoordinate, yCoordinate);

        configPanel = new ConfigurationPanel(this);
        canvas = new OutputPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        pack();
    }
}
