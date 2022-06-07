package com.nology.apilearning.controller;

import com.nology.apilearning.models.MarvelCharacter;
import com.nology.apilearning.services.CharacterService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    CharacterService characterService;


    @GetMapping("/test")
    private String test() {
        return "hello";
    }


    @GetMapping("/characters")
    private ResponseEntity<JSONArray> getAllCharacters() throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharacter());
    }

    @RequestMapping(
            value = "/characters/{id}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    private ResponseEntity<MarvelCharacter> getById(@PathVariable String id) throws IOException {


        return ResponseEntity.status(HttpStatus.OK).body(characterService.getCharacterById(Integer.parseInt(id)));
    }

}
