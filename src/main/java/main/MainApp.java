package main;

import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.frame.MainFrame;
import org.apache.commons.lang3.ArrayUtils;


import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainApp extends Application {

    public static String PathPoza;
    public static String PathOUT;
    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        this.primaryStage=primaryStage;
        Platform.setImplicitExit(false);

        //System.out.println(screenBounds);
        primaryStage.setTitle("Snake Results");
        primaryStage.setWidth(screenBounds.getWidth() / 1.4);
        primaryStage.setHeight(screenBounds.getHeight() - 100);

        //primaryStage.setFullScreen(true);
        FileInputStream input = new FileInputStream(PathPoza);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(primaryStage.getHeight()-100);
        imageView.setFitWidth(primaryStage.getWidth() / 2-100);

        HBox hbox = new HBox(imageView);
        hbox.setMaxWidth(primaryStage.getWidth() / 2);
        hbox.setMaxHeight(primaryStage.getHeight()-100);
        hbox.setLayoutX(5);
        hbox.setLayoutY(5);

        Group root = new Group();
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());

        root.getChildren().add(hbox);
        primaryStage.setScene(scene);


        Button button = new Button("Back");
        button.setPrefWidth(100);
        button.setPrefHeight(40);


        root.getChildren().add(button);


        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {

            //---------------------------------------------------------
            //System.out.println("Here" + event.getX() + " " + event.getY());
            //----------------------------------------------------
            primaryStage.hide();
            Platform.exit();
            new MainFrame().setVisible(true);

        });

        scene.setCursor(Cursor.CROSSHAIR);
        primaryStage.show();

        button.setLayoutY(hbox.getHeight() + 5);
        button.setLayoutX(hbox.getWidth() - 50);

        this.CSVtraveler(root, primaryStage.getWidth(), primaryStage.getHeight());


        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("Closing Stage");
        });

        primaryStage.setOnHiding((event) -> {
            System.out.println("Hiding Stage");
        });


    }



    public Node drawCell(double x, double y, double height, double width, Color color) {

        Rectangle r = new Rectangle(x, y - height / 2, width, height);
        r.setFill(color);

        return r;
    }

    public void CSVtraveler(Group root, double x, double y) {

        String fileName = PathOUT;

        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
            List<String[]> r = reader.readAll();


            r=this.CSVcleaner(r);
            String[] names = r.get(0);
            x = x / 2;
            double yy = 15;
            double ratio = (y-100) / names.length;
            Random rand = new Random();
            float red, green, blue;

            String[] values = r.get(1);
            for (String s : names) {


                Text t = new Text();
                t.setText(s);
                t.setLayoutX(x);
                t.setLayoutY(yy);
                yy += ratio;

                //System.out.println(yy);

                root.getChildren().add(t);

            }
            yy = 25;

            for (String v : values) {

                red = rand.nextFloat();
                green = rand.nextFloat();
                blue = rand.nextFloat();
                Color randomColor = new Color(red, green, blue, 0.7);
                double value = Double.parseDouble(v);
                root.getChildren().add(this.drawCell(x + x / 4, yy - 12.5, 25, value * x, randomColor));
                yy += ratio;
                //System.out.println(yy + " " + v);
            }


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public List<String[]> CSVcleaner(List<String[]> r){

        int i=0;
        double val;
        String[] names=r.get(0);
        String[] values=r.get(1);
        for(String v:values)
            if(Double.parseDouble(v)<0.01) {
                i= Arrays.asList(values).indexOf(v);
                values = ArrayUtils.remove(values, i);
                names=ArrayUtils.remove(names,i);
            }
        //System.out.println(Arrays.asList(names));


        r.set(0,names);
        r.set(1,values);
        return r;
    }


}

