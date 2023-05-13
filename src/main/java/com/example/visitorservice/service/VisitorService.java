package com.example.visitorservice.service;

import com.example.visitorservice.constants.VisitorStatus;
import com.example.visitorservice.dto.VisitorDto;
import com.example.visitorservice.model.VisitorDetails;

import java.time.LocalDate;
import java.util.List;

public interface VisitorService {

  void addVisitorDetails(VisitorDto visitorDto);

  void updateStatus(Integer id, VisitorStatus status);

  List<VisitorDetails> getAllVisitor(LocalDate date);

  List<VisitorDetails> getAllVisitorByFlatNumber(String flatNumber);
}
