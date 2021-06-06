package com.devigroups.datacompare.controllers;

import com.devigroups.datacompare.entities.GeneroData;
import com.devigroups.datacompare.entities.JavaData;
import com.devigroups.datacompare.repository.GeneroDataRepository;
import com.devigroups.datacompare.repository.JavaDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/entity")
public class EntityController {

    @Autowired
    private GeneroDataRepository generoDataRepository;

    @Autowired
    private JavaDataRepository javaDataRepository;

    // TODO: Move repo call from controller class to service class for both the entities
    @GetMapping("/genero")
    private List<GeneroData> getAllGeneroRecords(){
        return generoDataRepository.findAll();
    }

    @GetMapping("/java")
    private List<JavaData> getAllJavaRecords(){
        return javaDataRepository.findAll();
    }

}
