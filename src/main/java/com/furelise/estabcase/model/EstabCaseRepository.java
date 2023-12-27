package com.furelise.estabcase.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.furelise.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


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

    List<EstabCase> findByEmpID(Integer empID);

    Page<EstabCase> findAllByTakeStatus(boolean takeStatus, Pageable pageable);

    List<EstabCase> findByEmpIDAndEstabCaseStatusOrderByEstabCaseEndDesc(Integer empID, Integer estabCaseStatus);

    Integer countByEmpIDAndEstabCaseStatus(Integer empID, Integer estabCaseStatus);

    @Query("SELECT SUM(e.planPricePerCase) FROM EstabCase e WHERE e.empID = :empID AND e.estabCaseStatus = :estabCaseStatus")
    Double sumPlanPricePerCaseByEmpIDAndStatus(@Param("empID") Integer empID, @Param("estabCaseStatus") Integer estabCaseStatus);

    @Query("SELECT e FROM EstabCase e WHERE MONTH(e.estabCaseEnd) = :month AND YEAR(e.estabCaseEnd) = :year")
    List<EstabCase> findByMonthAndYear(@Param("month") Date month, @Param("year") Date year);
}