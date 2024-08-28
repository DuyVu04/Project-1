package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="building")
public class BuildingEntity {
	@Id//khoa chinh(primary key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)//tu dong tang dan auto increament
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ward")
	private String ward;
	
	@Column(name="numberofbasement")
	private Long numberOfBasement;
	
	@Column(name="street")
	private String street;
	
	@Column(name="managername")
	private String managerName;
	
	@Column(name="managerphonenumber")
	private String managerPhoneNumber;
	
	@Column(name="floorarea")
	private Long floorArea;
	
//	@Column(name="emptyarea")
//	private String emptyArea;
	
	@Column(name="rentprice")
	private Long rentPrice;
	
	@Column(name="servicefee")
	private String serviceFee;
	
	@Column(name="brokeragefee")
	private Long brokerageFee;
	
	@ManyToOne
	@JoinColumn(name= "districtid")
	private DistrictEntity district;
	
	@OneToMany(mappedBy="building",fetch = FetchType.LAZY)
	List<RentAreaEntity> rentarea =new ArrayList<RentAreaEntity>();
	
	
	public List<RentAreaEntity> getRentarea() {
		return rentarea;
	}
	public void setRentarea(List<RentAreaEntity> rentarea) {
		this.rentarea = rentarea;
	}
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}
	public Long getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
//	public String getEmptyArea() {
//		return emptyArea;
//	}
//	public void setEmptyArea(String emptyArea) {
//		this.emptyArea = emptyArea;
//	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPricel) {
		this.rentPrice = rentPricel;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Long getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
	
	
}
