package com.example.visitorservice.service;

import com.example.visitorservice.constants.VisitorStatus;
import com.example.visitorservice.dto.VisitorDto;

public interface SecurityService {

  void addVisitorDetails(VisitorDto visitorDto);

  void updateStatus(Integer id, VisitorStatus status);
}
