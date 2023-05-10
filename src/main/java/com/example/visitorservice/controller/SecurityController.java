package com.example.visitorservice.controller;

import com.example.visitorservice.ResponseHandler;
import com.example.visitorservice.dto.VisitorDto;
import com.example.visitorservice.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/visitor")
public class SecurityController {

  @Autowired
  private SecurityService securityService;

  @PostMapping(value = "/addVisitor")
  public ResponseEntity<Map<String,Object>> addVisitor(@RequestBody VisitorDto visitorDto){
    securityService.addVisitorDetails(visitorDto);
    return ResponseHandler.generateResponse(HttpStatus.OK, true, true);
  }
}
