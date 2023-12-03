package com.furelise.city.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.furelise.planord.model.PlanOrd;


@Entity
@Table(name = "city")
public class City {
    @Id
    @Column(name = "cityCode", columnDefinition = "char")
    private String cityCode;

    @Column(name = "cityName")
    private String cityName;
    
    @OneToMany(mappedBy="city", cascade = CascadeType.ALL)
    @OrderBy("planOrdID asc")
    private Set<PlanOrd> planOrds;
    
    @OneToMany(mappedBy="city", cascade = CascadeType.ALL)
    @OrderBy("empID asc")
    private Set<Emp> emps;

    public City() {
        super();
    }
    

    public City(String cityCode, String cityName){
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public Set<PlanOrd> getPlanOrds() {
		return planOrds;
	}

	public void setPlanOrds(Set<PlanOrd> planOrds) {
		this.planOrds = planOrds;
	}

	public Set<Emp> getEmps() {
		return emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

	public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
	public String toString() {
		return "City [cityCode=" + cityCode + ", cityName=" + cityName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityCode, cityName, planOrds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(cityCode, other.cityCode) && Objects.equals(cityName, other.cityName)
				&& Objects.equals(planOrds, other.planOrds);
	}

	
    
}
