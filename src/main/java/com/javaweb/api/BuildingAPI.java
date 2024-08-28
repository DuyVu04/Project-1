package com.javaweb.api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.model.ErrorResponeDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

import customexception.FieldRequiredException;

@RestController
@Transactional
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@GetMapping(value="/api/building/" )
	public List<BuildingDTO> getBuilding(@RequestParam Map<String,Object> params,
										@RequestParam(name="typeCode",required =false) List<String> typeCode){
		List<BuildingDTO> result =buildingService.findAll(params,typeCode);
		return result;
	}
	
	@PostMapping(value="/api/building/" )
	public void crateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity builEntity = new BuildingEntity();
		builEntity.setName(buildingRequestDTO.getName());
		builEntity.setWard(buildingRequestDTO.getWard());
		builEntity.setNumberOfBasement(buildingRequestDTO.getNumberOfBasement());
		builEntity.setStreet(buildingRequestDTO.getStreet());
		DistrictEntity districtEntity =new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		builEntity.setDistrict(districtEntity);
		builEntity.setRentPrice(buildingRequestDTO.getRentPrice());
		entityManager.persist(builEntity);
	}
	
	@PutMapping(value="/api/building/")
	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		BuildingEntity builEntity = new BuildingEntity();
		builEntity.setId(1L);
		builEntity.setName(buildingRequestDTO.getName());
		builEntity.setWard(buildingRequestDTO.getWard());
		builEntity.setNumberOfBasement(buildingRequestDTO.getNumberOfBasement());
		builEntity.setStreet(buildingRequestDTO.getStreet());
		DistrictEntity districtEntity =new DistrictEntity();
		districtEntity.setId(buildingRequestDTO.getDistrictId());
		builEntity.setDistrict(districtEntity);
		builEntity.setRentPrice(buildingRequestDTO.getRentPrice());
		entityManager.merge(builEntity);
	}
	
	
	@DeleteMapping(value="/api/building/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		BuildingEntity buildingEntity =entityManager.find(BuildingEntity.class,id);
		entityManager.remove(buildingEntity);
	}
	
	
	
	
}
