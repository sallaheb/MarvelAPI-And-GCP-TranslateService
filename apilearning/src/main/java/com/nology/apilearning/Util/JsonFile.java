package com.nology.apilearning.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.apilearning.Repository.IDs;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;


public class JsonFile {

    public static ObjectMapper objectMapper = getObjectMapper();

    public static ObjectMapper getObjectMapper() {

        return new ObjectMapper();
    }

    public static void JsonWriter(JSONArray JSONArray) throws IOException {
        FileWriter file = new FileWriter("MarvelIDs.json");
        file.write(JSONArray.toJSONString());
        file.close();
        System.out.println("File Created");
    }

    public static IDs fileReader(IDs results) throws IOException {

        String path = "C:\\Users\\709887M2A\\nology\\Apilearning\\MarvelIDs.json";
        results = null;

        try {
            JsonNode myKeys = objectMapper.readValue(Paths.get(path).toFile(), JsonNode.class);
            JSONObject myObj = new JSONObject();
            myObj.put("characterIds", myKeys);

            String data = myObj.toString();

            results = objectMapper.readValue(data, IDs.class);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return results;
    }

}
