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
    List<Float> listVertex;
    List<Float> listTexCord;
    List<Integer> listFaces;

    public OBJloader() throws FileNotFoundException {
        myObj = new File("C:\\Users\\Pluto\\IdeaProjects\\House\\src\\resources\\unti9.obj");
        myReader = new Scanner(myObj);
        listTexCord = new ArrayList<>();
        listVertex = new ArrayList<>();
        listFaces = new ArrayList<>();
        readFile();
    }

    private void readFile(){
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            if (data.startsWith("v ")) listVertex.addAll(Arrays.stream(data.replace("v ","").split(" ")).map(Float::parseFloat).collect(Collectors.toList()));
            else if (data.startsWith("vt ")) listTexCord.addAll(Arrays.stream(data.replace("vt ","").split(" ")).map(Float::parseFloat).collect(Collectors.toList()));
            else if (data.startsWith("f ")) listFaces.addAll(Arrays.stream(data.replace("f ","").replace("/"," ").split(" "))
                    .map(Integer::parseInt).map(i -> i-1).collect(Collectors.toList()));
        }

        myReader.close();
    }


}
