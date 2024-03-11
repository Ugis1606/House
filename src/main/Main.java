package main;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends Application{
    Content content = new Content();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("House");
        stage.setResizable(false);
        Scene scene = new Scene(content.createContent());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
