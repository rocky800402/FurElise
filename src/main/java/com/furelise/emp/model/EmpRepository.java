package com.furelise.emp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer>{
	Emp findByEmpMail(String empMail);
	List<Emp> findByWorkSum (Integer workSum);
}
