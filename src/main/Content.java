package main;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.io.FileNotFoundException;

public class Content {
    Group root;

    public Group createContent() throws FileNotFoundException {
        Controls controls = new Controls(this);
        Camera camera = setUpCamera();
        root = new Group();

        SubScene subScene = new SubScene(
                root,
                800,600,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);

        Group content = new Group();
        content.getChildren().add(subScene);
        controls.initMouseControl(root, camera, subScene);
        return content;
    }


    private Camera setUpCamera(){
        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        Camera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50)
        );

        return camera;
    }

    public void addForm(String path) throws FileNotFoundException {
        Form form = new Form();
        root.getChildren().add(form.createForm(path));
    }

}
