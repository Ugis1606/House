package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    Content content = new Content();
    Controls controls = new Controls(content);

    @Override
    public void start(Stage stage) {
        stage.setTitle("House");
        stage.setResizable(false);
        Scene scene = new Scene(content.createContent());
        stage.setScene(scene);
        stage.show();

        controls.initDragAndDrop(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
