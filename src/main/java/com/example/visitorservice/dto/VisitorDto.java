package com.example.visitorservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VisitorDto {

    private Integer id;

    private String visitorName;

    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    private String contactNumber;

    private Date checkInTime;

    private Date checkOutTime;

    private String flatNumber;
}
