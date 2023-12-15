package com.furelise.mem.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemRepository extends JpaRepository<Mem, Integer>{

	Mem findByMemName(String memName);
	Mem findByMemMail(String memMail);
	
}
