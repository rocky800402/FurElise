package com.furelise.ord.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
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
import javax.persistence.Table;

import com.furelise.estabcase.model.Emp;
import com.furelise.mem.model.Mem;
import com.furelise.orddetail.model.OrdDetail;
import com.furelise.sale.model.Sale;

@Entity
@Table(name = "Ord")
public class Ord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordID", updatable = false)
	private Integer ordID;
	
	@Column(name = "ordDate")
	private Timestamp ordDate;
	
//	@Column(name = "memID")
//	private Integer memID;
	@ManyToOne
	@JoinColumn(name = "memID", referencedColumnName = "memID")
	private Mem mem;
	
	@Column(name = "payment")
	private Integer payment;
	
	@Column(name = "deliver")
	private Integer deliver;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "cityCode", columnDefinition = "CHAR")
	private String cityCode;
	
	@Column(name = "deliverDate")
	private Date deliverDate;
	
	@Column(name = "sum")
	private BigDecimal sum;
	
	@Column(name = "shipping")
	private BigDecimal shipping;
	
	@Column(name = "total")
	private BigDecimal total;
	
//	@Column(name = "saleID")
//	private Integer saleID;
	@ManyToOne
	@JoinColumn(name = "saleID", referencedColumnName = "saleID")
	private Sale sale;
	
	@Column(name = "ordStatus")
	private Integer ordStatus;
	
	@Column(name = "arrival")
	private Date arrival;
	
	@OneToMany(mappedBy = "ord", cascade = CascadeType.ALL)
	private Set<OrdDetail> ordDetails;

	public Set<OrdDetail> getOrdDetails() {
		return ordDetails;
	}

	public void setOrdDetails(Set<OrdDetail> ordDetails) {
		this.ordDetails = ordDetails;
	}

	public Ord() {
		super();
	}

	

	public Ord(Timestamp ordDate, Mem mem, Integer payment, Integer deliver, String address, String cityCode,
			Date deliverDate, BigDecimal sum, BigDecimal shipping, BigDecimal total, Sale sale, Integer ordStatus,
			Date arrival, Set<OrdDetail> ordDetails) {
		super();
		this.ordDate = ordDate;
		this.mem = mem;
		this.payment = payment;
		this.deliver = deliver;
		this.address = address;
		this.cityCode = cityCode;
		this.deliverDate = deliverDate;
		this.sum = sum;
		this.shipping = shipping;
		this.total = total;
		this.sale = sale;
		this.ordStatus = ordStatus;
		this.arrival = arrival;
		this.ordDetails = ordDetails;
	}

	public Ord(Integer ordID, Timestamp ordDate, Mem mem, Integer payment, Integer deliver, String address,
			String cityCode, Date deliverDate, BigDecimal sum, BigDecimal shipping, BigDecimal total, Sale sale,
			Integer ordStatus, Date arrival, Set<OrdDetail> ordDetails) {
		super();
		this.ordID = ordID;
		this.ordDate = ordDate;
		this.mem = mem;
		this.payment = payment;
		this.deliver = deliver;
		this.address = address;
		this.cityCode = cityCode;
		this.deliverDate = deliverDate;
		this.sum = sum;
		this.shipping = shipping;
		this.total = total;
		this.sale = sale;
		this.ordStatus = ordStatus;
		this.arrival = arrival;
		this.ordDetails = ordDetails;
	}

	public Mem getMem() {
		return mem;
	}

	public void setMem(Mem mem) {
		this.mem = mem;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Integer getOrdID() {
		return ordID;
	}

	public void setOrdID(Integer ordID) {
		this.ordID = ordID;
	}

	public Timestamp getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Timestamp ordDate) {
		this.ordDate = ordDate;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getDeliver() {
		return deliver;
	}

	public void setDeliver(Integer deliver) {
		this.deliver = deliver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Date getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public BigDecimal getShipping() {
		return shipping;
	}

	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Integer getSaleID() {
		return saleID;
	}

	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}

	public Integer getOrdStatus() {
		return ordStatus;
	}

	public void setOrdStatus(Integer ordStatus) {
		this.ordStatus = ordStatus;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	@Override
	public String toString() {
		return "Ord [ordID=" + ordID + ", ordDate=" + ordDate + ", mem=" + mem + ", payment=" + payment + ", deliver="
				+ deliver + ", address=" + address + ", cityCode=" + cityCode + ", deliverDate=" + deliverDate
				+ ", sum=" + sum + ", shipping=" + shipping + ", total=" + total + ", sale=" + sale + ", ordStatus="
				+ ordStatus + ", arrival=" + arrival + ", ordDetails=" + ordDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, arrival, cityCode, deliver, deliverDate, mem, ordDate, ordDetails, ordID,
				ordStatus, payment, sale, shipping, sum, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ord other = (Ord) obj;
		return Objects.equals(address, other.address) && Objects.equals(arrival, other.arrival)
				&& Objects.equals(cityCode, other.cityCode) && Objects.equals(deliver, other.deliver)
				&& Objects.equals(deliverDate, other.deliverDate) && Objects.equals(mem, other.mem)
				&& Objects.equals(ordDate, other.ordDate) && Objects.equals(ordDetails, other.ordDetails)
				&& Objects.equals(ordID, other.ordID) && Objects.equals(ordStatus, other.ordStatus)
				&& Objects.equals(payment, other.payment) && Objects.equals(sale, other.sale)
				&& Objects.equals(shipping, other.shipping) && Objects.equals(sum, other.sum)
				&& Objects.equals(total, other.total);
	}

	


}