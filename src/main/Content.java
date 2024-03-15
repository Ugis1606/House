package main;

import javafx.animation.Animation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Content {
    Group root;
    Controls controls = new Controls(this);
    Rotate yRotate;
    Label label;
    String colourValue;

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
        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            try {
                controls.save(new SavedObject());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        Button rotateBtn = new Button("Rotate");
        rotateBtn.setOnAction(e -> {
            if (controls.timeline == null) controls.cameraRotate(yRotate);
            if (!controls.timeline.statusProperty().getValue().equals(Animation.Status.RUNNING)) controls.timeline.play();
            else controls.timeline.pause();
        });

        Button undoBtn = new Button("Undo");
        undoBtn.setOnAction(e -> {
            int index = root.getChildren().size()-1;
            if (index > -1) root.getChildren().remove(root.getChildren().size()-1);
        });

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setStyle("-fx-color-label-visible: false ;");
        colorPicker.setOnAction(e -> {
            label.setText("");
            colourValue = colorPicker.getValue().toString();
        });

        HBox box = new HBox();
        label.setFont(Font.font("Courier", 14));
        label.setAlignment(Pos.CENTER_LEFT);
        box.getTransforms().add(new Translate(20, 10, 0));
        box.setSpacing(5);
        box.getChildren().addAll(saveBtn, rotateBtn, undoBtn, colorPicker, label);
        return box;
    }


    private Camera setUpCamera(){
        Translate pivot = new Translate();
        yRotate = new Rotate(0, Rotate.Y_AXIS);
        Camera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(-20, Rotate.X_AXIS),
                new Translate(0, 0, -50)
        );

        return camera;
    }

    public void addForm(String path) throws FileNotFoundException, MalformedURLException, URISyntaxException {
        Form form = new Form();
        root.getChildren().add(form.createForm(path, colourValue, label.getText()));
    }

}
