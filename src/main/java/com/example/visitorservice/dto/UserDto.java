package com.example.visitorservice.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;

    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

    private String contactNumber;
}
