package com.secured.apps.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoanController{

    @GetMapping("/loans")
    public String getAllLoans(){
        return "No Loans";
    }

}