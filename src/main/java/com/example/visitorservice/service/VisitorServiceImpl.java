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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private static final Logger log = LoggerFactory.getLogger(VisitorServiceImpl.class);

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private UserIntegService userIntegService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void addVisitorDetails(VisitorDto visitorDto) {
        UserDto response = userIntegService.getUserDetailsByFlatNumber(visitorDto.getFlatNumber());
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

    @Override
    public List<VisitorDetails> getAllVisitor(LocalDate date) {
       return visitorRepository.findAllByDate(date);
    }

    @Override
    public List<VisitorDetails> getAllVisitorByFlatNumber(String flatNumber) {
        return visitorRepository.findAllByFlatNumber(flatNumber);
    }

    public void publishToKafka(VisitorDetails visitorDetails){
        kafkaTemplate.send("visitor", visitorDetails.toString());
        log.info("visitor details saved and sent notifications");
    }
}
