package com.example.visitorservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

  public static ResponseEntity<Map<String,Object>> generateResponse(HttpStatus status,
                                                        boolean success, Object response){
      Map<String, Object> result = new HashMap<>();
      result.put("isSuccess", success);
      result.put("data", response);
      result.put("status", status.value());
      result.put("message", status.value());
      result.put("dateTime", new Date());
      return new ResponseEntity<>(result, status);
  }
}
