package com.furelise.orddetail.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdDetailRepository extends JpaRepository<OrdDetail,OrdDetailPK>{

}
