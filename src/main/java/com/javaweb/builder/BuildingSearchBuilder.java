package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder { //dùng để lưu trữ thông tin client đưa ra thành 1 đối tượng để tìm kiếm
	private String name;
	private Long floorArea;
	private String ward;
	private String street;
	private Long districtId;
	private Integer numberOfBasement;
	private List<String> typeCode =new ArrayList<String>();
	private String managerName;
	private String managerPhoneNumber;
	private Long rentPriceFrom;
	private Long rentPriceTo;
	private Long areaTo;
	private Long areaFrom;
	private Long staffId;
	private String emptyArea;
	
	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.districtId = builder.districtId;
		this.numberOfBasement = builder.numberOfBasement;
		this.typeCode = builder.typeCode;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.areaTo = builder.areaTo;
		this.areaFrom = builder.areaFrom;
		this.staffId = builder.staffId;
		this.emptyArea=builder.emptyArea;
	}
	public String getEmptyArea() {
		return emptyArea;
	}
	public String getName() {
		return name;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public List<String> getTypeCode() {
		return typeCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Long getRentPriceTo() {
		return rentPriceTo;
	}
	public Long getAreaTo() {
		return areaTo;
	}
	public Long getAreaFrom() {
		return areaFrom;
	}
	public Long getStaffId() {
		return staffId;
	}
	public static class Builder{
		private String name;
		private Long floorArea;
		private String ward;
		private String street;
		private Long districtId;
		private Integer numberOfBasement;
		private List<String> typeCode =new ArrayList<String>();
		private String managerName;
		private String managerPhoneNumber;
		private Long rentPriceFrom;
		private Long rentPriceTo;
		private Long areaTo;
		private Long areaFrom;
		private Long staffId;
		private String emptyArea;
		
		
		public Builder setEmptyArea(String emptyArea) {
			this.emptyArea=emptyArea;
			return this;
		}
		public Builder setStreet(String street) {
			this.street=street;
			return this;
		}
		public Builder setName(String name) {
			this.name=name;
			return this;
		}
		public Builder setFloorArea(Long floorArea) {
			this.floorArea=floorArea;
			return this;
		}
		public Builder setWard(String ward) {
			this.ward=ward;
			return this;
		}
		public Builder setDistrictId(Long districtId) {
			this.districtId=districtId;
			return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement=numberOfBasement;
			return this;
		}
		public Builder setTypeCode(List<String> typeCode) {
			this.typeCode=typeCode;
			return this;
		}
		public Builder setManagerName(String managerName) {
			this.managerName=managerName;
			return this;
		}
		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber=managerPhoneNumber;
			return this;
		}
		public Builder setRentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom=rentPriceFrom;
			return this;
		}
		public Builder setRentPriceTo(Long rentPriceTo) {
			this.rentPriceTo=rentPriceTo;
			return this;
		}
		public Builder setAreaTo(Long areaTo) {
			this.areaTo=areaTo;
			return this;
		}
		public Builder setAreaFrom(Long areaFrom) {
			this.areaFrom=areaFrom;
			return this;
		}
		public Builder setStaffId(Long staffId) {
			this.staffId=staffId;
			return this;
		}
		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
