package com.furelise.pickupway.model;

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
@Table(name = "pickupway")
public class PickupWay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wayID", updatable = false)
    private Integer wayID;

    @Column(name = "wayName")
    private String wayName;
    
    @OneToMany(mappedBy = "pickupway", cascade = CascadeType.ALL)
    @OrderBy("wayID")
    private Set<PlanOrd> planOrds;

    public PickupWay() {
        super();
    }

    

    public PickupWay(String wayName, Set<PlanOrd> planOrds) {
		super();
		this.wayName = wayName;
		this.planOrds = planOrds;
	}



	public PickupWay(Integer wayID, String wayName, Set<PlanOrd> planOrds) {
		super();
		this.wayID = wayID;
		this.wayName = wayName;
		this.planOrds = planOrds;
	}



	public Set<PlanOrd> getPlanOrds() {
		return planOrds;
	}



	public void setPlanOrds(Set<PlanOrd> planOrds) {
		this.planOrds = planOrds;
	}



	public void setWayID(Integer wayID) {
        this.wayID = wayID;
    }

    public Integer getWayID() {
        return wayID;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getWayName() {
        return wayName;
    }



	@Override
	public String toString() {
		return "PickupWay [wayID=" + wayID + ", wayName=" + wayName + ", planOrds=" + planOrds + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(planOrds, wayID, wayName);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PickupWay other = (PickupWay) obj;
		return Objects.equals(planOrds, other.planOrds) && Objects.equals(wayID, other.wayID)
				&& Objects.equals(wayName, other.wayName);
	}

   

}
