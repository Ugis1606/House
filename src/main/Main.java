package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;


public class Main extends Application{

    private Group createContent() {
        Controls controls = new Controls();
        Sphere moon = new Sphere(1);
        moon.setMaterial(new PhongMaterial(Color.YELLOW));
        moon.getTransforms().add(new Translate(0, -6, 40));

        PointLight pointLight = new PointLight();
        pointLight.setColor(Color.WHITE);
        pointLight.getTransforms().add(new Translate(0, -20, 40));

        Box house = new Box(5, 5, 5);
        Rectangle ground = new Rectangle(20, 20);
        Cylinder tree1 = new Cylinder(1, 4);
        Cylinder tree2 = new Cylinder(1.2, 6);

        tree1.getTransforms().add(new Translate(7, (house.getHeight() - tree1.getHeight())/2 - 0.4, 6));
        tree2.getTransforms().add(new Translate(-7, (house.getHeight() - tree2.getHeight())/2 - 0.4, 2));

        house.setMaterial(new PhongMaterial(Color.FIREBRICK));
        tree1.setMaterial(new PhongMaterial(Color.FORESTGREEN));
        tree2.setMaterial(new PhongMaterial(Color.FORESTGREEN));
        ground.getTransforms().addAll(  new Rotate(-90, Rotate.X_AXIS),
                                        new Translate(-ground.getWidth()/2, -ground.getHeight()/2, house.getHeight()/2));
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

        // Build the Scene Graph
        Group root = new Group();
        root.getChildren().addAll(house, ground, tree1,tree2, moon);

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

  //      controls.cameraRotate(yRotate);
        controls.initMouseControl(root, subScene);

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
