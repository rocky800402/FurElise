package com.furelise.estabcase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "estabcase")
@Data
public class EstabCase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estabCaseID", updatable = false)
    private Integer estabCaseID;

    @Column(name = "empID")
    private Integer empID;

//	@ManyToOne
//	@JoinColumn(name = "empID", referencedColumnName = "empID")
//	private Emp emp;

    @Column(name = "planOrdID")
    private Integer planOrdID;

//	@ManyToOne
//	@JoinColumn(name = "planOrdID", referencedColumnName = "planOrdID")
//	private PlanOrd planOrd;

    @Column(name = "estabCaseDate")
    private Date estabCaseDate;

    //	@CreationTimestamp
    @UpdateTimestamp//案件最後的修改時間
    @Column(name = "estabCaseStart")
    private Timestamp estabCaseStart;

    @Column(name = "estabCaseEnd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp estabCaseEnd;

    @Column(name = "planPricePerCase")
    private BigDecimal planPricePerCase;

    @Column(name = "estabCasePic", columnDefinition = "longblob")
    private byte[] estabCasePic;

    @Column(name = "takeStatus", nullable = true)
    private Boolean takeStatus;

    @Column(name = "estabCaseStatus", nullable = true)
    private Integer estabCaseStatus;

    @Column(name = "estabCaseLevel", nullable = true)
    private Integer estabCaseLevel;

    @Column(name = "estabCaseFeedback", nullable = true)
    private String estabCaseFeedback;

    @Column(name = "estabCaseFBTime", nullable = true)
    private Timestamp estabCaseFBTime;

//	@OneToMany(mappedBy = "estabCase", cascade = CascadeType.ALL)
//	@OrderBy("estabCaseID asc")
//	private Set<Complaint> complaints;

    public EstabCase() {

    }


}
	



	