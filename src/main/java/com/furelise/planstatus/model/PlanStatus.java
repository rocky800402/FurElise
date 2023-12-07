package com.furelise.planstatus.model;

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

import com.furelise.planord.model.PlanOrd;

import lombok.Data;

@Entity
@Table(name = "planstatus")
@Data
public class PlanStatus implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planStatusID", updatable = false)
    private Integer planStatusID;

    @Column(name = "planStatus")
    private String planStatus;
    
//    @OneToMany(mappedBy = "planStatus", cascade = CascadeType.ALL)
//    @OrderBy("planStatusID")
//    private Set<PlanOrd> planOrds;

    public PlanStatus() {
        super();
    }

}
