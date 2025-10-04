package com.springboot.vaccinationservice.model;

import java.util.List;

import com.springboot.vaccinationservice.entity.VaccinationCenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequireResponse {

    private VaccinationCenter center;
    private List<Citizen> citizens;

}
