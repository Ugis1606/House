package main;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.io.FileNotFoundException;

public class Content {
    Group root;
    Label label;

    public Group createContent() {
        Controls controls = new Controls(this);
        Camera camera = setUpCamera();
        root = new Group();
        label = new Label();

        SubScene subScene = new SubScene(
                root,
                1200,800,
                true,
                SceneAntialiasing.BALANCED
        );
        subScene.setFill(Color.ALICEBLUE);
        subScene.setCamera(camera);

        Group content = new Group();
        content.getChildren().add(subScene);
        content.getChildren().add(createLabelandColorPicker());

        controls.initMouseControl(root, camera, subScene);
        return content;
    }

    private HBox createLabelandColorPicker() {
        HBox box = new HBox();
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setStyle("-fx-color-label-visible: false ;");
        label.setFont(Font.font("Courier", 14));
        label.setAlignment(Pos.CENTER_LEFT);
        box.getTransforms().add(new Translate(20, 10, 0));
        box.setSpacing(5);
        box.getChildren().addAll(colorPicker, label);
        return box;
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
