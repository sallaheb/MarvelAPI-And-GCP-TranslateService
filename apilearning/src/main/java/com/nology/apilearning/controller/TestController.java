package com.nology.apilearning.controller;

import com.nology.apilearning.Repository.IDs;
import com.nology.apilearning.models.MarvelCharacter;
import com.nology.apilearning.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    CharacterService characterService;


    @GetMapping("/characters")
    private ResponseEntity<IDs> getAllIDs() throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllIDs());
    }


    @GetMapping("/characters/{id}")
    public ResponseEntity<MarvelCharacter> getTranslated(@PathVariable String id, @RequestParam (required = false, defaultValue = "en") String languageCode ) throws IOException, InterruptedException {
        System.out.println("CONTROLLER HERE");


        return ResponseEntity.status(HttpStatus.OK).body(characterService.toTranslate(Integer.parseInt(id), languageCode));
    }




}
