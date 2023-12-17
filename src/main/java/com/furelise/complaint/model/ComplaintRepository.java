package com.furelise.complaint.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
    List<Complaint> findByEstabCaseID(Integer estabCaseID);
    Complaint  findByEstabCaseIDAndMemID(Integer estabCaseID, Integer memID);
}
