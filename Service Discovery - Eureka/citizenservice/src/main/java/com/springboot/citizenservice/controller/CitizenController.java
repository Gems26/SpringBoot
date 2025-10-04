package com.springboot.citizenservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.citizenservice.entity.Citizen;
import com.springboot.citizenservice.repository.CitizenRepository;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    private CitizenRepository citizenRepository;

    @RequestMapping(path ="/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("hello",HttpStatus.OK);
    }

    @RequestMapping(path ="/id/{id}")
    public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id){

        List<Citizen> citizens = citizenRepository.findByVaccinationCenterId(id);
        return new ResponseEntity<>(citizens,HttpStatus.OK);
    }

    @PostMapping(path ="/add")
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen){

        Citizen newCitizen = citizenRepository.save(citizen);
        return new ResponseEntity<>(newCitizen,HttpStatus.OK);
    }


}
