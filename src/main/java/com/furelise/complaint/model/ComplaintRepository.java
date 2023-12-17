package com.furelise.complaint.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
    List<Complaint> findByEstabCaseID(Integer estabCaseID);
}
