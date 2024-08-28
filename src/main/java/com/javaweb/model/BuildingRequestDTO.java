package com.javaweb.model;



public class BuildingRequestDTO {
	
	private String name;
	
	
	private String ward;
	
	
	private Long numberOfBasement;
	
	
	private String street;
	
	private Long districtId;
	
	private Long rentPrice;


	public Long getRentPrice() {
		return rentPrice;
	}


	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}


	public Long getDistrictId() {
		return districtId;
	}


	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getWard() {
		return ward;
	}


	public void setWard(String ward) {
		this.ward = ward;
	}


	public Long getNumberOfBasement() {
		return numberOfBasement;
	}


	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}
	
	
}
