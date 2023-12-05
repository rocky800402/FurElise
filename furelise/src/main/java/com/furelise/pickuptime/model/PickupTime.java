package com.furelise.pickuptime.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



import lombok.Data;



@Entity
@Table (name = "pickuptime")
@Data
public class PickupTime implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeID", updatable = false)
    private Integer timeID;
    
    @Column(name = "timeRange")
    private String timeRange;
    
//    @OneToMany(mappedBy = "pickupTime", cascade = CascadeType.ALL)
//    @OrderBy("timeID")
//    private Set<PlanOrd> planOrds;
//    
//    @OneToMany(mappedBy = "pickupTime", cascade = CascadeType.ALL)
//    private Set<Emp> emps;

    public PickupTime(){
        super();
    }  

}
