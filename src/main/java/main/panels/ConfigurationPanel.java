package main.panels;

import main.frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ConfigurationPanel extends JPanel {
    final MainFrame frame;
    JButton importBtn = new JButton("Import");
    JTextField continent = new JTextField();
    JTextField country = new JTextField();
    JFileChooser fileChooser;
    JButton refresh = new JButton("Refresh");

    public ConfigurationPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        fileChooser = new JFileChooser();
        importBtn.setBackground(Color.GREEN);
        refresh.setBackground(Color.GREEN);
        add(importBtn);
        add(continent);
        add(country);
        add(refresh);

        importBtn.addActionListener(this::load);
        continent.addActionListener(this::conti);
        country.addActionListener(this::tara);
        refresh.addActionListener(this::clear);
    }

    private void load(ActionEvent action){
        try {
        fileChooser.setDialogTitle("Specify a file to load");
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            frame.canvas.image = ImageIO.read(fileChooser.getSelectedFile());
            frame.canvas.repaint();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
