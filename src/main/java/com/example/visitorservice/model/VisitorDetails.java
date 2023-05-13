package com.example.visitorservice.model;

import com.example.visitorservice.constants.VisitorStatus;
import lombok.Data;

import javax.persistence.*;
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

    private String contactNumber;

    private Date checkInTime;

    private Date checkOutTime;

    private String flatNumber;
    
    private String ownerEmail;

    private String ownerContactNumber;

    @Enumerated(EnumType.STRING)
    private VisitorStatus visitorStatus = PENDING;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerContactNumber() {
        return ownerContactNumber;
    }

    public void setOwnerContactNumber(String ownerContactNumber) {
        this.ownerContactNumber = ownerContactNumber;
    }

    public VisitorStatus getVisitorStatus() {
        return visitorStatus;
    }

    public void setVisitorStatus(VisitorStatus visitorStatus) {
        this.visitorStatus = visitorStatus;
    }
}
