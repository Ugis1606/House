package main;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

public class Form {

    public MeshView createForm(String colorValue, String labelText, SavedObject savedObject) throws MalformedURLException {
        TriangleMesh mesh = new TriangleMesh();

        savedObject.vertex.forEach(el -> mesh.getPoints().addAll(el));
        savedObject.texCord.forEach(el -> mesh.getTexCoords().addAll(el));
        savedObject.faces.forEach(el -> mesh.getFaces().addAll(el));

        MeshView form = new MeshView(mesh);
        form.setDrawMode(DrawMode.FILL);

        PhongMaterial material = new PhongMaterial();
        File file = new File(labelText);
        Image imageForFile = new Image(file.toURI().toURL().toExternalForm());

        if (!labelText.isEmpty())   material.setDiffuseMap(imageForFile);
        else                        material.setDiffuseColor(Color.valueOf(colorValue));
        form.setMaterial(material);

        return form;
    }



}
