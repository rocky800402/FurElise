package com.furelise.period.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name = "period")
@Data
public class Period implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodID", updatable = false )
    private Integer periodID;
    
    @Column(name = "planPeriod")
    private Integer planPeriod;
    
//    @OneToMany(mappedBy = "period", cascade = CascadeType.ALL)
//    @OrderBy("periodID")
//    private Set<PlanOrd> planOrds;
    
   

    public Period() {
        super();
    }

    

    

    
    
}
