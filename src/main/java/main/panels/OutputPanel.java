package main.panels;

import main.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OutputPanel extends JPanel {
    final MainFrame frame;

    BufferedImage image; //the offscreen image
    Graphics2D graphics;

    private int W;
    private int H;

    public OutputPanel(MainFrame frame) {
        this.frame = frame;
        W = frame.getWidth();
        H = frame.getHeight();
        createOffscreenImage();
        init();
    }

    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init(){
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }


}
