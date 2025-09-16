package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;



    @GetMapping("/") 
    public String display(){
        return "weather";
    }

    @GetMapping("weather")
    public String showWeatherDetails(@RequestParam String city,Model model){
        WeatherRespose weatherRespose = weatherService.getWeather(city);
        model.addAttribute("weather", weatherRespose); 
        
        return "weather";
    }
}
