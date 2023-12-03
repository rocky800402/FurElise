package com.furelise.planstatus.model;

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

@Entity
@Table(name = "planstatus")
public class PlanStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planStatusID", updatable = false)
    private Integer planStatusID;

    @Column(name = "planStatus")
    private String planStatus;
    
    @OneToMany(mappedBy = "planStatus", cascade = CascadeType.ALL)
    @OrderBy("planStatusID")
    private Set<PlanOrd> planOrds;

    public PlanStatus() {
        super();
    }

    
    public PlanStatus(String planStatus, Set<PlanOrd> planOrds) {
		super();
		this.planStatus = planStatus;
		this.planOrds = planOrds;
	}


	public PlanStatus(Integer planStatusID, String planStatus, Set<PlanOrd> planOrds) {
		super();
		this.planStatusID = planStatusID;
		this.planStatus = planStatus;
		this.planOrds = planOrds;
	}


	public Set<PlanOrd> getPlanOrds() {
		return planOrds;
	}


	public void setPlanOrds(Set<PlanOrd> planOrds) {
		this.planOrds = planOrds;
	}


	public void setPlanStatusID(Integer planStatusID) {
        this.planStatusID = planStatusID;
    }

    public Integer getPlanStatusID() {
        return planStatusID;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanStatus() {
        return planStatus;
    }

	@Override
	public String toString() {
		return "PlanStatus [planStatusID=" + planStatusID + ", planStatus=" + planStatus + ", planOrds=" + planOrds
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(planOrds, planStatus, planStatusID);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanStatus other = (PlanStatus) obj;
		return Objects.equals(planOrds, other.planOrds) && Objects.equals(planStatus, other.planStatus)
				&& Objects.equals(planStatusID, other.planStatusID);
	}


}
