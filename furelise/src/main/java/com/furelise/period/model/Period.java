package com.furelise.period.model;

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

import com.furelise.planord.model.PlanOrd;

@Entity
@Table(name = "period")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodID", updatable = false )
    private Integer periodID;
    
    @Column(name = "planPeriod")
    private Integer planPeriod;
    
    @OneToMany(mappedBy = "period", cascade = CascadeType.ALL)
    @OrderBy("periodID")
    private Set<PlanOrd> planOrds;
    
   

    public Period() {
        super();
    }

    

    public Period(Integer planPeriod, Set<PlanOrd> planOrds) {
		super();
		this.planPeriod = planPeriod;
		this.planOrds = planOrds;
	}



	public Period(Integer periodID, Integer planPeriod, Set<PlanOrd> planOrds) {
		super();
		this.periodID = periodID;
		this.planPeriod = planPeriod;
		this.planOrds = planOrds;
	}



	public Set<PlanOrd> getPlanOrds() {
		return planOrds;
	}



	public void setPlanOrds(Set<PlanOrd> planOrds) {
		this.planOrds = planOrds;
	}



	public void setPeriodID(Integer periodID) {
        this.periodID = periodID;
    }

    public Integer getPeriodID() {
        return periodID;
    }

    public void setPlanPeriod(Integer planPeriod) {
        this.planPeriod = planPeriod;
    }

    public Integer getPlanPeriod() {
        return planPeriod;
    }



	@Override
	public String toString() {
		return "Period [periodID=" + periodID + ", planPeriod=" + planPeriod + ", planOrds=" + planOrds + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(periodID, planOrds, planPeriod);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		return Objects.equals(periodID, other.periodID) && Objects.equals(planOrds, other.planOrds)
				&& Objects.equals(planPeriod, other.planPeriod);
	}

    
    
}
