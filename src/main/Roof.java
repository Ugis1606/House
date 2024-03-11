package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class Roof {

    public MeshView createRoof(){
        TriangleMesh mesh = new TriangleMesh();
        float h = 5;    // Height (Y)
        float w = 10;    // Width (X)
        float d = 5;    // Depth (Z)

        mesh.getPoints().addAll(
                -w / 2, -h / 2,   0,        // 0
                       -w / 2,  h / 2,  -d / 2,    // 1
                        w / 2,  h / 2,  -d / 2,   // 2
                        w / 2, -h / 2,   0,       // 3
                        w / 2,  h / 2,   d / 2     // 4
                       -w / 2,  h / 2,   d / 2     // 5
        );

        mesh.getTexCoords().addAll(
                0.504f, 0.524f,     // 0
                        0.701f, 0,          // 1
                        0.126f, 0,          // 2
                        0,      0.364f,     // 3
                        0,      0.608f,     // 4
                        0.165f, 1,          // 5
                        0.606f, 1,          // 6
                        0.575f, 0.420f,     // 7
                        0.575f, 0.643f,     // 8
                        0.740f, 0.643f,     // 9
                        0.740f, 0.420f      // 10
        );

        mesh.getFaces().addAll(
                   0, 0, 1, 0, 2, 0, // Front face
                        0, 0, 2, 0, 3, 0, // Front face
                    3, 0, 2, 0, 4, 0, // Right face
                        3, 0, 4, 0, 5, 0, // Back face
                        3, 0, 5, 0, 0, 0, // Back face
                    0, 0, 5, 0, 1, 0, // Left face
                        2, 0, 4, 0, 5, 0, // Bottom face
                        2, 0, 5, 0, 1, 0 // Bottom face
        );


        MeshView roof = new MeshView(mesh);
        roof.setDrawMode(DrawMode.FILL);
        roof.setMaterial(new PhongMaterial(Color.BROWN));

        return roof;
    }


}
