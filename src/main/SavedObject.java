package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SavedObject implements Serializable {
    public List<Float> vertex = new ArrayList<>();
    public List<Float> texCord = new ArrayList<>();
    public List<Integer> faces = new ArrayList<>();


}
