package com.example.visitorservice.model;

import com.example.visitorservice.constants.VisitorStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

import static com.example.visitorservice.constants.VisitorStatus.PENDING;

@Data
@Entity
public class VisitorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String ownerEmail;

    private String ownerContactNumber;

    private VisitorStatus visitorStatus = PENDING;
}
