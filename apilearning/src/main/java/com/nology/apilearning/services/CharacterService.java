package com.nology.apilearning.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.apilearning.Util.JsonFile;
import com.nology.apilearning.models.MarvelCharacter;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.time.Instant;

@Service
public class CharacterService {

    private final String publickey = "abfcf82a889a553cc4f8b732b85d8607";
    private final String privatekey = "1e9bd463ba73afb1bdca51cfa2f37a2bad36d2fc";
    private Long timestamp = Instant.now().getEpochSecond();
    private final String Hash = DigestUtils.md5Hex(timestamp+privatekey+publickey);



    public MarvelCharacter getSingleCharacter() {
      return  new MarvelCharacter(1,"IronMan", "Clever Guy", "path");
      //request // service for API and link it to your controller
      // call this URL
      // Get Marvel Chracter ID , descrption etc.
    }

    public JSONArray getAllCharacter() throws IOException {

        JSONArray JsonData = new JSONArray();

        int Offset = 0;

        String URL = "https://gateway.marvel.com:443/v1/public/characters?ts="+timestamp+"&apikey="+publickey+"&hash="+Hash +"&limit=100"+"&offset="+Offset;
        RestTemplate template = new RestTemplate(); //http request

        do {
            JsonNode result = template.getForEntity(URL + (Offset * 99), JsonNode.class).getBody();
            if (result != null) {
                JsonData.addAll(result.get("data").get("results").findValues("id"));
            } else {
                System.out.println("Data = null");
            }
            Offset++;
        } while (Offset < 16);

        System.out.println(JsonData.size());

        JsonFile.JsonWriter(JsonData);

        return JsonData;
    }



    public JSONArray getCharacterById(int id) throws IOException {

        JSONArray JsonData = new JSONArray();

        String URL = "https://gateway.marvel.com:443/v1/public/characters/"+id+"?"+"&ts="+timestamp+"&apikey="+publickey+"&hash="+Hash;

        RestTemplate template = new RestTemplate(); //http request

            JsonNode result = template.getForEntity(URL , JsonNode.class).getBody();
            if (result != null) {
                JsonData.add(result.get("data").get("results").findValues("id"));
//                JsonData.add(result.get("data").get("results").findValues("name"));
                JsonData.add(result.get("data").get("results").findValues("name").get(0));
                JsonData.add(result.get("data").get("results").findValues("description"));
                JsonData.add(result.get("data").get("results").findValues("thumbnail"));

            } else {
                System.out.println("Data = null");
            }

//        ObjectMapper mapper = new ObjectMapper();
//        MarvelCharacter character = mapper.readTree();
//        System.out.println(character);

        return JsonData;
    }





}
