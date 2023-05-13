package com.example.visitorservice.controller;

import com.example.visitorservice.ResponseHandler;
import com.example.visitorservice.constants.VisitorStatus;
import com.example.visitorservice.dto.VisitorDto;
import com.example.visitorservice.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(value = "/visitor")
public class VisitorController {

  @Autowired
  private VisitorService visitorService;

  @PostMapping(value = "/addVisitor")
  public ResponseEntity<Map<String,Object>> addVisitor(@RequestBody VisitorDto visitorDto){
    visitorService.addVisitorDetails(visitorDto);
    return ResponseHandler.generateResponse(HttpStatus.OK, true, true);
  }

  @GetMapping(value = "/all")
  public ResponseEntity<Map<String,Object>> getAllVisitor(@RequestParam LocalDate date){
    return ResponseHandler.generateResponse(HttpStatus.OK, true, visitorService.getAllVisitor(date));
  }

  @GetMapping(value = "/flat/{flatNumber}")
  public ResponseEntity<Map<String,Object>> getAllVisitorByFlatNumber(@PathVariable String flatNumber){
    return ResponseHandler.generateResponse(HttpStatus.OK, true, visitorService.getAllVisitorByFlatNumber(flatNumber));
  }

  @PatchMapping(value = "/approve/{id}")
  public ResponseEntity<Map<String,Object>> updateStatus(@PathVariable Integer id, @RequestParam VisitorStatus visitorStatus){
    visitorService.updateStatus(id, visitorStatus);
    return ResponseHandler.generateResponse(HttpStatus.OK, true, true);
  }
}
