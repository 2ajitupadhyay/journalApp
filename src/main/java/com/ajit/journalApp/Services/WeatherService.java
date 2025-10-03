package com.ajit.journalApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ajit.journalApp.api.response.WeatherResponse;

@Component
public class WeatherService {
    private static final String apiKey = "54498334469647908bb182431250210";

    private static final String API = "http://api.weatherapi.com/v1/current.json?key=api_Key&q=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather (String city){
        String finalAPI = API.replace("CITY", city).replace("api_Key", apiKey);

       ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

       WeatherResponse body = response.getBody();

       return body;
    }
}
