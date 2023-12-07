package com.furelise.ord.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdRepository extends JpaRepository<Ord, Integer>{

}
