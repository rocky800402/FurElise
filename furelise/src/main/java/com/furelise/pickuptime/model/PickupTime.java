package com.furelise.pickuptime.model;

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
@Table (name = "pickuptime")
public class PickupTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeID", updatable = false)
    private Integer timeID;
    
    @Column(name = "timeRange")
    private String timeRange;
    
    @OneToMany(mappedBy = "pickuptime", cascade = CascadeType.ALL)
    @OrderBy("timeID")
    private Set<PlanOrd> planOrds;
    
    @OneToMany(mappedBy = "period", cascade = CascadeType.ALL)
    private Set<Emp> emps;

    public PickupTime(){
        super();
    }
    
   

    public PickupTime(String timeRange, Set<PlanOrd> planOrds, Set<Emp> emps) {
		super();
		this.timeRange = timeRange;
		this.planOrds = planOrds;
		this.emps = emps;
	}



	public PickupTime(Integer timeID, String timeRange, Set<PlanOrd> planOrds, Set<Emp> emps) {
		super();
		this.timeID = timeID;
		this.timeRange = timeRange;
		this.planOrds = planOrds;
		this.emps = emps;
	}



	public void setTimeID(Integer timeID) {
        this.timeID = timeID;
    }

    public Integer getTimeID() {
        return timeID;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getTimeRange() {
        return timeRange;
    }


	@Override
	public String toString() {
		return "PickupTime [timeID=" + timeID + ", timeRange=" + timeRange + ", planOrds=" + planOrds + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(planOrds, timeID, timeRange);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PickupTime other = (PickupTime) obj;
		return Objects.equals(planOrds, other.planOrds) && Objects.equals(timeID, other.timeID)
				&& Objects.equals(timeRange, other.timeRange);
	}


   
    

}
