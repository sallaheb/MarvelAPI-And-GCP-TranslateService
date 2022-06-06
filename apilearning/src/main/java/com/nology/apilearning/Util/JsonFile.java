package com.nology.apilearning.Util;

import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFile {

    public static void JsonWriter (JSONArray JSONArray) throws IOException {
        FileWriter file = new FileWriter("MarvelIDs.json");
        file.write(JSONArray.toJSONString());
        file.close();
        System.out.println("File Created");
    }
}
