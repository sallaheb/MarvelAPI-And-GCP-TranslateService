package com.nology.apilearning.models;

import java.util.HashMap;
import java.util.Map;

public class MarvelCharacter {
    private int ID;
    private String name;
    private String description;
    private String thumbnail;

    public MarvelCharacter(int ID, String name, String description, String thumbnail) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
