package com.example.visitorservice.model;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class AuditEntity {

  private Date addDate;

  private Date modDate;

  private String addUser;

  private String modUser;

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }
}
