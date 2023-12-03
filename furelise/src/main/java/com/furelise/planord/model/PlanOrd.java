package com.furelise.planord.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.furelise.city.model.City;
import com.furelise.mem.model.Mem;
import com.furelise.plan.model.Plan;
import com.furelise.period.model.Period;
import com.furelise.pickuptime.model.PickupTime;
import com.furelise.pickupway.model.PickupWay;
import com.furelise.planstatus.model.PlanStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "planord")
public class PlanOrd {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planOrdID", updatable = false)
    private Integer planOrdID;

    @ManyToOne
    @JoinColumn(name = "planID", referencedColumnName = "planID")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "timeID", referencedColumnName = "timeID")
    private PickupTime pickupTime;
    
    @ManyToOne
    @JoinColumn(name = "periodID", referencedColumnName = "periodID")
    private Period period;

    @Column(name = "`day`")
    private String day;

    @ManyToOne
    @JoinColumn(name = "wayID", referencedColumnName = "wayID")
    private PickupWay pickupWay;

    
    @ManyToOne
    @JoinColumn(name = "memID", referencedColumnName = "memID")
    private Mem mem;
//    @Column(name = "memID")
//    private Integer memID;

    @Column(name = "planStart")
    private Date planStart;

    @Column(name = "planEnd")
    private Date planEnd;
    
    @ManyToOne
    @JoinColumn(name = "cityCode", referencedColumnName = "cityCode")
    private City city;

    @Column(name = "floor")
    private String floor;

    @Column(name = "pickupStop")
    private String pickupStop;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "planOrdDate", updatable = false, insertable = false)
    private Timestamp planOrdDate;

    @Column(name = "amendLog")
    private Integer amendLog;

    @ManyToOne
    @JoinColumn(name = "planStatusID", referencedColumnName = "planStatusID")
    private PlanStatus planStatus;

    @Column(name = "contact")
    private String contact;

    @Column(name = "contactTel")
    private String contactTel;

    public PlanOrd() {
        super();
    }
    

    public PlanOrd(Plan plan, PickupTime pickupTime, Period period, String day, PickupWay pickupWay, Mem mem,
			Date planStart, Date planEnd, City city, String floor, String pickupStop, BigDecimal total,
			Timestamp planOrdDate, Integer amendLog, PlanStatus planStatus, String contact, String contactTel) {
		super();
		this.plan = plan;
		this.pickupTime = pickupTime;
		this.period = period;
		this.day = day;
		this.pickupWay = pickupWay;
		this.mem = mem;
		this.planStart = planStart;
		this.planEnd = planEnd;
		this.city = city;
		this.floor = floor;
		this.pickupStop = pickupStop;
		this.total = total;
		this.planOrdDate = planOrdDate;
		this.amendLog = amendLog;
		this.planStatus = planStatus;
		this.contact = contact;
		this.contactTel = contactTel;
	}


	public PlanOrd(Integer planOrdID, Plan plan, PickupTime pickupTime, Period period, String day, PickupWay pickupWay,
			Mem mem, Date planStart, Date planEnd, City city, String floor, String pickupStop, BigDecimal total,
			Timestamp planOrdDate, Integer amendLog, PlanStatus planStatus, String contact, String contactTel) {
		super();
		this.planOrdID = planOrdID;
		this.plan = plan;
		this.pickupTime = pickupTime;
		this.period = period;
		this.day = day;
		this.pickupWay = pickupWay;
		this.mem = mem;
		this.planStart = planStart;
		this.planEnd = planEnd;
		this.city = city;
		this.floor = floor;
		this.pickupStop = pickupStop;
		this.total = total;
		this.planOrdDate = planOrdDate;
		this.amendLog = amendLog;
		this.planStatus = planStatus;
		this.contact = contact;
		this.contactTel = contactTel;
	}


	public Mem getMem() {
		return mem;
	}


	public void setMem(Mem mem) {
		this.mem = mem;
	}


	public Integer getPlanOrdID() {
        return planOrdID;
    }

    public void setPlanOrdID(Integer planOrdID) {
        this.planOrdID = planOrdID;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

//    public Integer getMemID() {
//        return memID;
//    }
//
//    public void setMemID(Integer memID) {
//        this.memID = memID;
//    }

    public Date getPlanStart() {
        return planStart;
    }

    public void setPlanStart(Date planStart) {
        this.planStart = planStart;
    }

    public Date getPlanEnd() {
        return planEnd;
    }


	public void setPlanEnd(Date planEnd) {
        this.planEnd = planEnd;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getPickupStop() {
        return pickupStop;
    }

    public void setPickupStop(String pickupStop) {
        this.pickupStop = pickupStop;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Timestamp getPlanOrdDate() {
        return planOrdDate;
    }

    public void setPlanOrdDate(Timestamp planOrdDate) {
        this.planOrdDate = planOrdDate;
    }

    public Integer getAmendLog() {
        return amendLog;
    }

    public void setAmendLog(Integer amendLog) {
        this.amendLog = amendLog;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

	public PickupTime getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(PickupTime pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public PickupWay getPickupWay() {
		return pickupWay;
	}

	public void setPickupWay(PickupWay pickupWay) {
		this.pickupWay = pickupWay;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public PlanStatus getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(PlanStatus planStatus) {
		this.planStatus = planStatus;
	}


	@Override
	public String toString() {
		return "PlanOrd [planOrdID=" + planOrdID + ", plan=" + plan + ", pickupTime=" + pickupTime + ", period="
				+ period + ", day=" + day + ", pickupWay=" + pickupWay + ", mem=" + mem + ", planStart=" + planStart
				+ ", planEnd=" + planEnd + ", city=" + city + ", floor=" + floor + ", pickupStop=" + pickupStop
				+ ", total=" + total + ", planOrdDate=" + planOrdDate + ", amendLog=" + amendLog + ", planStatus="
				+ planStatus + ", contact=" + contact + ", contactTel=" + contactTel + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(amendLog, city, contact, contactTel, day, floor, mem, period, pickupStop, pickupTime,
				pickupWay, plan, planEnd, planOrdDate, planOrdID, planStart, planStatus, total);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanOrd other = (PlanOrd) obj;
		return Objects.equals(amendLog, other.amendLog) && Objects.equals(city, other.city)
				&& Objects.equals(contact, other.contact) && Objects.equals(contactTel, other.contactTel)
				&& Objects.equals(day, other.day) && Objects.equals(floor, other.floor)
				&& Objects.equals(mem, other.mem) && Objects.equals(period, other.period)
				&& Objects.equals(pickupStop, other.pickupStop) && Objects.equals(pickupTime, other.pickupTime)
				&& Objects.equals(pickupWay, other.pickupWay) && Objects.equals(plan, other.plan)
				&& Objects.equals(planEnd, other.planEnd) && Objects.equals(planOrdDate, other.planOrdDate)
				&& Objects.equals(planOrdID, other.planOrdID) && Objects.equals(planStart, other.planStart)
				&& Objects.equals(planStatus, other.planStatus) && Objects.equals(total, other.total);
	}

	
	
 
    

}