package com.example.visitorservice.repository;

import com.example.visitorservice.model.VisitorDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitorRepository extends CrudRepository<VisitorDetails,Integer> {

    List<VisitorDetails> findAllByDate(LocalDate date);

    List<VisitorDetails> findAllByFlatNumber(String flatNumber);
}
