package com.furelise.emp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

//import com.furelise.city.model.City;
//import com.furelise.complaint.model.Complaint;
//import com.furelise.orddetail.model.OrdDetail;
//import com.furelise.pickuptime.model.PickupTime;

@Entity
@Table(name = "emp")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Emp implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empID")
    private Integer empID;

    @Column(name = "empName")
    private String empName;

    @Column(name = "empMail")
    private String empMail;

    @Column(name = "empTel")
    private String empTel;

    @Column(name = "empBirth")
    private Date empBirth;

    @Column(name = "empPass")
    private String empPass;

    @Column(name = "IDNumber")
    private String IDNumber;

    @Column(name = "empLicense")
    private boolean empLicense;

    @Column(name = "empArea1")
    private String empArea1;

    @Column(name = "empArea2")
    private String empArea2;

    @Column(name = "empArea3")
    private String empArea3;

    @Column(name = "timeID")
    private Integer timeID;

    @Column(name = "IDF")
    private byte[] IDF;

    @Column(name = "IDB")
    private byte[] IDB;

    @Column(name = "licenseF")
    private byte[] licenseF;

    @Column(name = "bankCode")
    private String bankCode;

    @Column(name = "accountNo")
    private String accountNo;

    @Column(name = "bankPic")
    private byte[] bankPic;

    @Column(name = "workSum")
    private Integer workSum;

    @CreatedDate()
    @Column(name = "empRegiDate")
    private Timestamp empRegiDate;

    @Column(name = "empStatus", columnDefinition = "INT DEFAULT 2")
    private Integer empStatus = 2;

    @Column(name = "empIsSuspended", columnDefinition = "BIT(1) DEFAULT 0")
    private boolean empIsSuspended = false;

//    @ManyToOne
//    @JoinColumn(name = "timeID", referencedColumnName = "timeID", insertable = false, updatable = false)
//    private PickupTime pickupTime;

//    @ManyToOne
//    @JoinColumn(name = "empArea1", referencedColumnName = "cityCode", insertable = false, updatable = false)
//    private City city1;
//
//    @ManyToOne
//    @JoinColumn(name = "empArea2", referencedColumnName = "cityCode", insertable = false, updatable = false)
//    private City city2;
//
//    @ManyToOne
//    @JoinColumn(name = "empArea3", referencedColumnName = "cityCode", insertable = false, updatable = false)
//    private City city3;
    
//    @OneToMany( mappedBy = "emp", cascade= CascadeType.ALL)
//	private Set<Complaint> complaints;

    // Getter and Setter methods
    public Emp() {
		super();
	}

	
}