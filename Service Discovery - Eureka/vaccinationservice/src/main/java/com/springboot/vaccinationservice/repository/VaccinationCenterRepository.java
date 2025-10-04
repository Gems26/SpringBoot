package com.springboot.vaccinationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.vaccinationservice.entity.VaccinationCenter;

public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter,Integer>{

}
