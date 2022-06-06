package com.nology.apilearning.models;

import java.util.HashMap;
import java.util.Map;

public class MarvelCharacter {
    private int ID;
    private String name;
    private String description;

    public MarvelCharacter(int ID, String name, String description) {
        this.ID = ID;
        this.name = name;
        this.description = description;
    }


    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
