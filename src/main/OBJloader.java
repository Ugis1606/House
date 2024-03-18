package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OBJloader {
    File myObj;
    Scanner myReader;

    public OBJloader(String path) throws FileNotFoundException {
        myObj = new File(path);
        myReader = new Scanner(myObj);
    }

    public void readFile(SavedObject savedObject){
        List<Float> vertex = new ArrayList<>();
        List<Float> texCord = new ArrayList<>();
        List<Integer> faces = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.startsWith("v ")) vertex.addAll(Arrays.stream(data.replace("v ","").split(" ")).map(Float::parseFloat).collect(Collectors.toList()));
            else if (data.startsWith("vt ")) texCord.addAll(Arrays.stream(data.replace("vt ","").split(" ")).map(Float::parseFloat).collect(Collectors.toList()));
            else if (data.startsWith("f ")) faces.addAll(Arrays.stream(data.replace("f ","").replace("/"," ").split(" "))
                    .map(Integer::parseInt).map(i -> i-1).collect(Collectors.toList()));
        }

        savedObject.vertex.add(vertex);
        savedObject.texCord.add(texCord);
        savedObject.faces.add(faces);

        myReader.close();
    }


}
