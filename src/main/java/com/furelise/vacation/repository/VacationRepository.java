package com.furelise.vacation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.furelise.vacation.model.entity.Vacation;

@Repository
public interface VacationRepository extends JpaRepository<Vacation,Integer>{

	List<Vacation> findByEmpID(Integer empID);
	
	@Query("SELECT v FROM Vacation v WHERE v.empID = :empID ORDER BY v.vaID DESC")
    List<Vacation> findByEmpIDOrderByVaIDDesc(Integer empID);
	
}
