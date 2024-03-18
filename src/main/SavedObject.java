package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SavedObject implements Serializable {
    public List<List<Float>> vertex = new ArrayList<>();
    public List<List<Float>> texCord = new ArrayList<>();
    public List<List<Integer>> faces = new ArrayList<>();


}
