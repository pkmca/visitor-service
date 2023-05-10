package com.example.visitorservice.repository;

import com.example.visitorservice.model.VisitorDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<VisitorDetails,Integer> {
}
