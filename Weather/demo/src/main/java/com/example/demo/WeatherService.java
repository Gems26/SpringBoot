package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    
    @Value("${weather.api.key}")
    private String apikey;
    private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apikey}&units=metric";
    public WeatherRespose getWeather(String city){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl,WeatherRespose.class,city,apikey);
    }
    
    
    
    
}
