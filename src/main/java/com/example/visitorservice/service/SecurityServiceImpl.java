package com.example.visitorservice.service;

import com.example.visitorservice.constants.VisitorStatus;
import com.example.visitorservice.dto.UserDto;
import com.example.visitorservice.dto.VisitorDto;
import com.example.visitorservice.exception.DataNotFoundException;
import com.example.visitorservice.model.VisitorDetails;
import com.example.visitorservice.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecurityServiceImpl implements SecurityService{

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private VisitorRepository visitorRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void addVisitorDetails(VisitorDto visitorDto) {
        String url = userServiceUrl + "/user/flat/" + visitorDto.getFlatNumber();
        UserDto response = restTemplate.getForObject(url, UserDto.class);
        VisitorDetails visitorDetails = new VisitorDetails();
        BeanUtils.copyProperties(visitorDto,visitorDetails);
        visitorDetails.setOwnerEmail(response.getEmail());
        visitorDetails.setOwnerContactNumber(response.getContactNumber());
        visitorRepository.save(visitorDetails);
        publishToKafka(visitorDetails);
    }

    @Override
    public void updateStatus(Integer id, VisitorStatus visitorStatus) {
        VisitorDetails visitorDetails = visitorRepository.findById(id).orElseThrow( () ->
                new DataNotFoundException("User is not exists by id " + id));
        visitorDetails.setVisitorStatus(visitorStatus);
        visitorRepository.save(visitorDetails);
        kafkaTemplate.send("visitorStatus", visitorDetails.toString());
    }

    public void publishToKafka(VisitorDetails visitorDetails){
        kafkaTemplate.send("visitor", visitorDetails.toString());
        log.info("visitor details saved and sent notifications");
    }
}
