package com.nology.apilearning.services;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.nology.apilearning.Util.JsonFile;
import com.nology.apilearning.models.DataEnvelope;
import com.nology.apilearning.Repository.IDs;
import com.nology.apilearning.models.MarvelCharacter;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

import static com.nology.apilearning.Util.JsonFile.objectMapper;

@Service
public class CharacterService {
   private IDs results = null;


    public IDs getAllIDs () throws IOException {
        if (JsonFile.fileReader(results).getCharacterIds().size() != 1562) {
            getAllCharacterIds();
        }
        return JsonFile.fileReader(results);
    }



    public JSONArray getAllCharacterIds() throws IOException {
        String privateKey = System.getenv("MarvelPrivatekey");
        String publicKey = System.getenv("MarvelPublickey");
        JSONArray JsonData = new JSONArray();

        Long timestamp= Instant.now().getEpochSecond();
        String Hash = DigestUtils.md5Hex(timestamp+privateKey+publicKey);

        int Offset = 0;
        String URL = "https://gateway.marvel.com:443/v1/public/characters?ts="+timestamp+"&apikey="+publicKey+"&hash="+Hash +"&limit=100"+"&offset="+Offset;
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

        JsonFile.JsonWriter(JsonData);

        return JsonData;
    }


    public MarvelCharacter getCharacterById(int id) throws IOException, InterruptedException {
        String privateKey = System.getenv("MarvelPrivatekey");
        String publicKey = System.getenv("MarvelPublickey");
        if (privateKey == null || publicKey == null) {
            System.out.println("keys are empty");
        }
        Long timestamp= Instant.now().getEpochSecond();
        String Hash = DigestUtils.md5Hex(timestamp+privateKey+publicKey);

        String URL = "https://gateway.marvel.com:443/v1/public/characters/"+id+"?"+"&ts="+timestamp+"&apikey="+publicKey+"&hash="+Hash;
        RestTemplate template = new RestTemplate(); //http request
        JsonNode result = template.getForEntity(URL , JsonNode.class).getBody();
        String data = result.toString();
        DataEnvelope results = objectMapper.readValue(data, DataEnvelope.class);
        MarvelCharacter marvelCharacter = results.getData().getResults().get(0);


        return marvelCharacter;
    }


    public MarvelCharacter toTranslate (int id, String languageCode) throws IOException, InterruptedException {

        System.getenv("GOOGLE_API_KEY");
        System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

        MarvelCharacter marvel = getCharacterById(id);
        String textToTranslate = marvel.getDescription();

    if(Objects.equals(languageCode, " ") || Objects.equals(languageCode, "en")) {
        return marvel;
    } else {
        Translate translate = TranslateOptions.getDefaultInstance().getService(); //1
         Translation translation = translate.translate(textToTranslate,
                 Translate.TranslateOption.sourceLanguage("en"),
                 Translate.TranslateOption.targetLanguage(languageCode));
         marvel.setDescription(translation.getTranslatedText());
    }

        return  marvel;
    }







}
