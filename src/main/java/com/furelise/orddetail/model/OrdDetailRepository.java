package com.furelise.orddetail.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdDetailRepository extends JpaRepository<OrdDetail,OrdDetailPK>{
	List<OrdDetail> findByOrdID(Integer ordID);
	@Query("SELECT o FROM OrdDetail o WHERE o.ordID = :ordID AND o.pID = :pID")
	OrdDetail findByOrdIDAndPID(@Param("ordID") Integer ordID, @Param("pID") Integer pID);
}
