package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private Parent createContent() {
        Box box = new Box(5, 5, 5);
        Rectangle ground = new Rectangle(20, 20);
        box.setMaterial(new PhongMaterial(Color.FIREBRICK));
        ground.getTransforms().addAll(  new Rotate(-90, Rotate.X_AXIS),
                                        new Translate(-ground.getWidth()/2, -ground.getHeight()/2, box.getHeight()/2));
        ground.setFill(Color.FORESTGREEN);

        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);

        // Create and position camera
        Camera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50)
        );

        // animate the camera position.
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(yRotate.angleProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(10),
                        new KeyValue(yRotate.angleProperty(), -360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Build the Scene Graph
        Group root = new Group();
        root.getChildren().add(camera);
        root.getChildren().add(box);
        root.getChildren().add(ground);

        // Use a SubScene
        SubScene subScene = new SubScene(
                root,
                800,600,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);

        return group;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("House");
        stage.setResizable(false);
        Scene scene = new Scene(createContent());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
