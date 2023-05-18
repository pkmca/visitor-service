package com.example.visitorservice.service;

import com.example.visitorservice.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserIntegService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @CircuitBreaker(name = "UserDetails", fallbackMethod = "fallBack")
    public UserDto getUserDetailsByFlatNumber(String flatNumber){
        String url = userServiceUrl + "/user/flat/" + flatNumber;
        UserDto response = restTemplate.getForObject(url, UserDto.class);
        return response;
    }

    public UserDto fallBack(String flatNumber, Throwable t){
        System.out.println("I'm in fallback method");
        return new UserDto();
    }
}
