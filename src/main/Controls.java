package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.*;
import java.net.URISyntaxException;

public class Controls {
    Content content;
    Timeline timeline;
    public Controls(Content content) {
        this.content = content;
    }

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private final DoubleProperty zoom = new SimpleDoubleProperty(0);

    public void initMouseControl(Group group, Camera camera, SubScene scene){
        Rotate xRotate;
        Rotate yRotate;

        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );

        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event ->{
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY - (anchorX - event.getSceneX()));
        });

        //---------  camera ---------------
        Translate zTranslate;
        camera.getTransforms().addAll(
                zTranslate = new Translate(0, 0, 0)
        );
        zTranslate.zProperty().bind(zoom);
        scene.setOnScroll(event -> zoom.set(zoom.get() + event.getDeltaY() / 20));
    }

    public void initDragAndDrop(Scene scene){
        scene.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            try {
                String path = db.getUrl().replace("file:/","");
                if (path.endsWith(".obj")) content.addForm(path);

                else if (path.endsWith(".ser")) content.loadForm(load(path));

                else content.label.setText(path);
            } catch (URISyntaxException | IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            event.setDropCompleted(db.hasFiles());
            event.consume();
        });
    }

    public void cameraRotate(Rotate yRotate){
        timeline = new Timeline(
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
    }

    public void save(SavedObject savedObject) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separatorChar + "Desktop"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER", ".ser");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Main.scene.getWindow());
        if(file == null) return;

        FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(savedObject);
        out.close();
        fileOut.close();
    }

    public static SavedObject load(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        SavedObject savedObject = (SavedObject) in.readObject();
        in.close();
        fileIn.close();
        return savedObject;
    }

}
