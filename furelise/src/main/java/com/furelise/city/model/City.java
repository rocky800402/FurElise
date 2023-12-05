package com.furelise.city.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

//import com.furelise.emp.model.Emp;
//import com.furelise.planord.model.PlanOrd;

import lombok.Data;


@Entity
@Table(name = "city")
@Data
public class City implements Serializable{
    @Id
    @Column(name = "cityCode", columnDefinition = "char")
    private String cityCode;

    @Column(name = "cityName")
    private String cityName;
    
//    @OneToMany(mappedBy="city", cascade = CascadeType.ALL)
//    @OrderBy("planOrdID asc")
//    private Set<PlanOrd> planOrds;
    
//    @OneToMany(mappedBy="city1", cascade = CascadeType.ALL)
//    @OrderBy("empID asc")
//    private Set<Emp> emp1s;
//    @OneToMany(mappedBy="city2", cascade = CascadeType.ALL)
//    @OrderBy("empID asc")
//    private Set<Emp> emp2s;
//    @OneToMany(mappedBy="city3", cascade = CascadeType.ALL)
//    @OrderBy("empID asc")
//    private Set<Emp> emp3s;

    public City() {
        super();
    }
    

    public City(String cityCode, String cityName){
        this.cityCode = cityCode;
        this.cityName = cityName;
    }


	
    
}
