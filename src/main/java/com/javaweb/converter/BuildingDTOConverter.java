package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component//giup danhs daus class nay la 1 cai Bean laf 1 class ko co ham khoi tao
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = new BuildingDTO();
		building.setName(item.getName());
		DistrictEntity districtEntity= districtRepository.findNameById(item.getDistrictid());
		building.setAddress(item.getStreet()+" "+ item.getWard()+" "+districtEntity.getName());
		List<RentAreaEntity> rentAreas =rentAreaRepository.getValueByBuildingId(item.getId());
		String areaResult =rentAreas.stream().map(it->it.getValue().toString()).collect(Collectors.joining(","));
		building.setManagerName(item.getManagerName());
		building.setManagerPhoneNumber(item.getManagerPhoneNumber());
		building.setRentPrice(item.getRentPrice());
		building.setFloorArea(item.getFloorArea());
		building.setBrokerageFee(item.getBrokerageFee());
		building.setNumberOfBasement(item.getNumberOfBasement());
		building.setServiceFee(item.getServiceFee());
		building.setEmptyArea(item.getEmptyArea());
		building.setRentArea(areaResult);
		return building;
	}
}
