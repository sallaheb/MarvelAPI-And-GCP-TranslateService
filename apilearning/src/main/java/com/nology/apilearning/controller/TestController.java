package com.nology.apilearning.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nology.apilearning.models.MarvelCharacter;
import com.nology.apilearning.services.CharacterService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;

@RestController
public class TestController {

    @Autowired
    CharacterService characterService;


    @GetMapping("/test")
    private String test() {
        return "hello";
    }

    @GetMapping("/character")
    private ResponseEntity<MarvelCharacter> getCharacter(){

        return ResponseEntity.status(HttpStatus.OK).body(characterService.getSingleCharacter());
    }

    @GetMapping("/characters")
    private ResponseEntity<JSONArray> getAllCharacters() throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharacter());
    }

}
