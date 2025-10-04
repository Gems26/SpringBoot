package com.springboot.vaccinationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.springboot.vaccinationservice.entity.VaccinationCenter;
import com.springboot.vaccinationservice.model.Citizen;
import com.springboot.vaccinationservice.model.RequireResponse;
import com.springboot.vaccinationservice.repository.VaccinationCenterRepository;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path="/add")
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter){
        VaccinationCenter center = vaccinationCenterRepository.save(vaccinationCenter);
        return new ResponseEntity<>(center, HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<RequireResponse> getAllByVaccinationCenterId(@PathVariable Integer id){
        RequireResponse requireResponse = new RequireResponse();

        VaccinationCenter center = vaccinationCenterRepository.findById(id).orElse(null);
        requireResponse.setCenter(center);

        ResponseEntity<List<Citizen>> response = restTemplate.exchange(
            "http://CITIZEN-SERVICE/citizen/id/" + id,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Citizen>>() {}
        );

        requireResponse.setCitizens(response.getBody());
        return new ResponseEntity<>(requireResponse, HttpStatus.OK);
    }
}
