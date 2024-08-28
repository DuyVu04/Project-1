package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component//giup danhs daus class nay la 1 cai Bean laf 1 class ko co ham khoi tao
public class BuildingDTOConverter {//Dùng model mapper để lấy dữ liệu của các tòa nhà sau khi tìm được dưới DB gán vào đối tượng khởi tạo 
	
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class) ;
		building.setAddress(item.getStreet()+" "+ item.getWard()+" "+item.getDistrict().getName());
		List<RentAreaEntity> rentAreas =item.getRentarea();
		String areaResult =rentAreas.stream().map(it->it.getValue().toString()).collect(Collectors.joining(" , "));
		building.setRentArea(areaResult);
		List<RentAreaEntity> emptyArea = item.getRentarea();//tai vi dien tich con trong la dien tich thue ma nguoi ta chua thue
		String emptyAreaResult =emptyArea.stream().map(it->it.getValue().toString()).collect(Collectors.joining(" , "));
		building.setEmptyArea(emptyAreaResult);
		return building;
	}
}
