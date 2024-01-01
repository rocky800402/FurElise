package com.furelise.estabcase.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.furelise.estabcase.empcasemanage.IncomeSummaryDTO;
import com.furelise.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import javax.transaction.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface EstabCaseRepository extends JpaRepository<EstabCase, Integer> {

    //	@Query("SELECT * FROM estabCase WHERE planOrdID = ?1 ORDER BY estabCaseDate DESC;")
    List<EstabCase> findByPlanOrdIDOrderByEstabCaseDateDesc(Integer planOrdID);

    Integer countByPlanOrdIDAndEstabCaseDateGreaterThanEqual(Integer planOrdID, Date estabCaseDate);

    List<EstabCase> findByPlanOrdIDAndEstabCaseDateGreaterThanEqual(Integer planOrdID, Date estabCaseDate);

    @Modifying
    @Query("UPDATE EstabCase e SET e.estabCaseStatus = 3 WHERE e.planOrdID =:planOrdID AND e.estabCaseDate >=:estabCaseDate")
    void updateEstabCaseStatus(@Param("planOrdID") Integer planOrdID, @Param("estabCaseDate") Date estabCaseDate);

    List<EstabCase> findByEmpIDAndEstabCaseStatus(
            Integer empID,
            Integer estabCaseStatus
    );

    List<EstabCase> findByPlanOrdID(Integer planOrdID);


    @Query("SELECT e FROM EstabCase e WHERE e.estabCaseDate >= CURRENT_DATE AND e.estabCaseDate <= CURRENT_DATE + 2 AND e.takeStatus = 0")
    List<EstabCase> findEstabCasesWithinLastTwoDaysAndTakeStatusZero();
    Page<EstabCase> findAllByTakeStatus(boolean takeStatus, Pageable pageable);

    List<EstabCase> findByEmpIDAndEstabCaseStatusOrderByEstabCaseEndDesc(Integer empID, Integer estabCaseStatus);

    Integer countByEmpIDAndEstabCaseStatus(Integer empID, Integer estabCaseStatus);

    @Query("SELECT SUM(e.planPricePerCase) FROM EstabCase e WHERE e.empID = :empID AND e.estabCaseStatus = :estabCaseStatus")
    Double sumPlanPricePerCaseByEmpIDAndStatus(@Param("empID") Integer empID, @Param("estabCaseStatus") Integer estabCaseStatus);

    @Query("SELECT e FROM EstabCase e WHERE e.estabCaseEnd >= :startTimestamp AND e.estabCaseEnd <= :endTimestamp")
    List<EstabCase> findByEstabCaseEndBetween(@Param("startTimestamp") Timestamp startTimestamp, @Param("endTimestamp") Timestamp endTimestamp);

    @Query("SELECT e FROM EstabCase e WHERE e.estabCaseEnd >= :startTimestamp AND e.estabCaseEnd <= :endTimestamp AND e.estabCaseStatus = 1 AND e.takeStatus IS TRUE")
    List<EstabCase> findByEstabCaseEndBetweenAndStatus(@Param("startTimestamp") Timestamp startTimestamp, @Param("endTimestamp") Timestamp endTimestamp);//查詢當月的已完成案件

    @Query("SELECT SUM(e.planPricePerCase) " +
            "FROM EstabCase e " +
            "WHERE e.estabCaseEnd >= :startTimestamp " +
            "AND e.estabCaseEnd <= :endTimestamp " +
            "AND e.estabCaseStatus = 1 " +
            "AND e.takeStatus IS TRUE " +
            "AND e.empID = :empID")
    BigDecimal findTotalPlanPriceByEmpIDAndStatus(
            @Param("empID") Integer empID,
            @Param("startTimestamp") Timestamp startTimestamp,
            @Param("endTimestamp") Timestamp endTimestamp);






    @Query("SELECT NEW com.furelise.estabcase.empcasemanage.IncomeSummaryDTO(" +
            "YEAR(e.estabCaseEnd), MONTH(e.estabCaseEnd), SUM(e.planPricePerCase)) " +
            "FROM EstabCase e " +
            "WHERE e.estabCaseStatus = 1 " +
            "AND e.takeStatus = TRUE " +
            "AND e.empID = :empID " +
            "GROUP BY YEAR(e.estabCaseEnd), MONTH(e.estabCaseEnd)")
    List<com.furelise.estabcase.empcasemanage.IncomeSummaryDTO> findTotalPlanPriceByEmpIDAndStatusGroupByMonth(
            @Param("empID") Integer empID);
}