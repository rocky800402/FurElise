package com.furelise.mem.repository;

import com.furelise.mem.model.entity.Mem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemRepository extends JpaRepository<Mem, Integer>{

	Mem findByMemName(String memName);
	Mem findByMemMail(String memMail);
	
}
