package com.nology.apilearning.Util;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class JsonFile {

    public static void JsonWriter(JSONArray JSONArray) throws IOException {
        FileWriter file = new FileWriter("MarvelIDs.json");
        file.write(JSONArray.toJSONString());
        file.close();
        System.out.println("File Created");
    }


}
