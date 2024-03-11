package main;

import javafx.collections.ObservableFloatArray;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

import java.io.FileNotFoundException;

public class Other {

    public MeshView createOther() throws FileNotFoundException {
        OBJloader loader = new OBJloader();
        TriangleMesh mesh = new TriangleMesh();
        float h = 5;    // Height (Y)
        float w = 10;    // Width (X)
        float d = 5;    // Depth (Z)

        loader.listVertex.forEach(el -> mesh.getPoints().addAll(el));
        loader.listTexCord.forEach(el -> mesh.getTexCoords().addAll(el));
        loader.listFaces.forEach(el -> mesh.getFaces().addAll(el));

        MeshView other = new MeshView(mesh);
        other.setDrawMode(DrawMode.FILL);
        other.setMaterial(new PhongMaterial(Color.BROWN));

        return other;
    }


}
