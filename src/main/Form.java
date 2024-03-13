package main;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import java.io.FileNotFoundException;

public class Form {

    public MeshView createForm(String path) throws FileNotFoundException {
        OBJloader loader = new OBJloader(path);
        TriangleMesh mesh = new TriangleMesh();

        loader.listVertex.forEach(el -> mesh.getPoints().addAll(el));
        loader.listTexCord.forEach(el -> mesh.getTexCoords().addAll(el));
        loader.listFaces.forEach(el -> mesh.getFaces().addAll(el));

        MeshView form = new MeshView(mesh);
        form.setDrawMode(DrawMode.FILL);

        form.setMaterial(new PhongMaterial(Color.AQUAMARINE));

        return form;
    }


}
