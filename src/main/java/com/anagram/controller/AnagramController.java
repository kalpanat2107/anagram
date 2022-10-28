package com.anagram.controller;

import com.anagram.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/action/checkAnagram")
public class AnagramController {

    @Autowired
    AnagramService anagramService;

    @PostMapping(path = "/upload")
    public ResponseEntity checkAnagram(@RequestParam("file") MultipartFile file) throws IOException {
        // Get available slots for charger booking
        anagramService.checkAnagram(file);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
