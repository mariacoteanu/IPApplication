package main.panels;

import javafx.application.Application;
import main.MainApp;
import main.frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Paths;

public class ConfigurationPanel extends JPanel {
    final MainFrame frame;
    JButton importBtn = new JButton("Import");
    JTextField continent = new JTextField();
    JTextField country = new JTextField();
    JFileChooser fileChooser;
    JButton refresh = new JButton("Refresh");
    JButton submit = new JButton("Submit");

    public ConfigurationPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        fileChooser = new JFileChooser();
        importBtn.setBackground(Color.GREEN);
        refresh.setBackground(Color.GREEN);
        submit.setBackground(Color.GREEN);
        JLabel label1 = new JLabel("Continent:",SwingConstants.CENTER);
        JLabel label2 = new JLabel("Country:",SwingConstants.CENTER);
        add(importBtn);
        add(label1);
        add(continent);
        add(label2);
        add(country);
        add(refresh);
        add(submit);

        importBtn.addActionListener(this::load);
        continent.addActionListener(this::conti);
        country.addActionListener(this::tara);
        refresh.addActionListener(this::clear);
        submit.addActionListener(this::goNext);
    }

    private void load(ActionEvent action){
        try {
        fileChooser.setDialogTitle("Specify a file to load");
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            frame.canvas.image = ImageIO.read(fileChooser.getSelectedFile());
            MainApp.PathPoza=fileChooser.getSelectedFile().getAbsolutePath();
            frame.canvas.repaint();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goNext(ActionEvent action){

        MainApp.PathOUT= Paths.get("", "src\\main\\resources\\out.csv").toString();
        frame.setVisible(false);
        Application.launch(MainApp.class);

    }

    private void conti(ActionEvent action){
        continent.setBounds(50,50, 150,20);
        frame.continentText = continent.getText();
        System.out.println("Continent: " + frame.continentText);
    }

    private void tara(ActionEvent action){
        country.setBounds(50,50, 150,20);
        frame.countryText = country.getText();
        System.out.println("Country: " + frame.countryText);
    }

    private void clear(ActionEvent action){
        continent.addActionListener(this::conti);
        country.addActionListener(this::tara);
        frame.canvas.createOffscreenImage();
        frame.canvas.repaint();
    }

}
