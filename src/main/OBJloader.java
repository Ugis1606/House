package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OBJloader {
    File myObj;
    Scanner myReader;
    SavedObject savedObject;

    public OBJloader(String path, SavedObject savedObject) throws FileNotFoundException {
        this.savedObject = savedObject;
        myObj = new File(path);
        myReader = new Scanner(myObj);
        savedObject.vertex = new ArrayList<>();
        savedObject.texCord = new ArrayList<>();
        savedObject.faces = new ArrayList<>();
    }

    public void readFile(){
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.startsWith("v ")) savedObject.vertex.addAll(Arrays.stream(data.replace("v ","").split(" ")).map(Float::parseFloat).collect(Collectors.toList()));
            else if (data.startsWith("vt ")) savedObject.texCord.addAll(Arrays.stream(data.replace("vt ","").split(" ")).map(Float::parseFloat).collect(Collectors.toList()));
            else if (data.startsWith("f ")) savedObject.faces.addAll(Arrays.stream(data.replace("f ","").replace("/"," ").split(" "))
                    .map(Integer::parseInt).map(i -> i-1).collect(Collectors.toList()));
        }

        myReader.close();
    }


}
