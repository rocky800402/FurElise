package com.furelise.orddetail.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdDetailRepository extends JpaRepository<OrdDetail,OrdDetailPK>{
	List<OrdDetail> findByOrdID(Integer ordID);
}
