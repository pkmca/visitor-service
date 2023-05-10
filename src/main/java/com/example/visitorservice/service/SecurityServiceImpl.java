package com.example.visitorservice.service;

import com.example.visitorservice.dto.UserDto;
import com.example.visitorservice.dto.VisitorDto;
import com.example.visitorservice.model.VisitorDetails;
import com.example.visitorservice.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecurityServiceImpl implements SecurityService{

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private VisitorRepository visitorRepository;

    @Value("user.service.url")
    private String userServiceUrl;

    @Autowired
    private KafkaTemplate<String, VisitorDetails> kafkaTemplate;

    @Override
    public void addVisitorDetails(VisitorDto visitorDto) {
        VisitorDetails visitorDetails = new VisitorDetails();
        BeanUtils.copyProperties(visitorDto,visitorDetails);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<UserDto> request = new HttpEntity<>(new UserDto());
        String url = userServiceUrl + "/user/flat/" + visitorDto.getFlatNumber();
        UserDto response = restTemplate
                .exchange(url, HttpMethod.GET, request, UserDto.class).getBody();
        visitorDetails.setOwnerEmail(response.getEmail());
        visitorDetails.setOwnerContactNumber(response.getContactNumber());
        visitorRepository.save(visitorDetails);
        publishToKafka(visitorDetails);
    }

    public void publishToKafka(VisitorDetails visitorDetails){
        kafkaTemplate.send(visitorDetails.getId().toString(),visitorDetails);
    }
}
