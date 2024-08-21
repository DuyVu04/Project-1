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

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.ErrorResponeDTO;
import com.javaweb.service.BuildingService;

import customexception.FieldRequiredException;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value="/api/building/" )
	public List<BuildingDTO> getBuilding(@RequestParam Map<String,Object> params,
										@RequestParam(name="typeCode",required =false) List<String> typeCode){
		List<BuildingDTO> result =buildingService.findAll(params,typeCode);
		return result;
	}
	
	

//	
//	public void valiDate(BuildingDTO building) throws FieldRequiredException {
//		if(building.getName()== null  || building.getName().equals("") || building.getNumberOfBasement()==null ) {
//			throw new FieldRequiredException("Name or Age is null");
//		}
//	}
	
	
	

	
	
	
	
	
	
	
	
//	@PostMapping(value="/api/building/" )
//	public Object getBuilding(@RequestBody BuildingDTO building){
//		valiDate(building);
//		return null;
//	}
//	
	
	//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
//	public void getBuilding(@RequestParam(value="Name" , required =false )  String name,
//							@RequestParam(value="Age", required= false) Integer age){
//		System.out.print(name+" "+age);
//	}
//	@RequestMapping(value="/api/building/", method = RequestMethod.GET)
	
	
	
	
	
//	@PostMapping(value="/api/building/" )
//	public Object getBuilding(@RequestBody BuildingDTO building){
//		try {
//			System.out.print(5/0);
//			valiDate(building);
//		}
//		catch(FieldRequiredException e){
//			ErrorResponeDTO errorResponseDTO=new ErrorResponeDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details =new ArrayList<>();
//			details.add("Thiếu 1 trong 2 rồi kìa");
//			details.add("Check lại đi");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//		}
//		BuildingDTO buildingDTO1 =new BuildingDTO();
//		buildingDTO1.setName("Duy Vu");
//		buildingDTO1.setAge(24);
//		return buildingDTO1;
//	}
	
	
	
	
	
	
//	@GetMapping(value="/api/building/" )
//	public List<BuildingDTO> getBuilding(@RequestParam(value="name" , required =false )  String name,
//							@RequestParam(value="age", required= false) Integer age){
//		List<BuildingDTO> buildings =new ArrayList<>();
//		BuildingDTO buildingDTO1 =new BuildingDTO();
//		buildingDTO1.setName("Duy Vu");
//		buildingDTO1.setAge(24);
//		BuildingDTO buildingDTO2 =new BuildingDTO();
//		buildingDTO2.setName("Hoang Dieu");
//		buildingDTO2.setAge(24);
//		buildings.add(buildingDTO1);
//		buildings.add(buildingDTO2);
//		return buildings;
//	}
//	
	
	
	
	
	
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)//use with Map to input value
//	public void getBuilding2(@RequestParam Map<String,String> params){
//		System.out.print(params);
//	}
	
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)//use with Map to input value and RequestBody
//	public void getBuilding3(@RequestBody Map<String,String> params){
//		System.out.print(params);
//	}
//	@RequestMapping(value="/api/building/", method = RequestMethod.POST)//use Class
//	public void getBuilding3(@RequestBody BuildingDTO buildingDTO){
//		System.out.print("Oke");
//	}	
//	@DeleteMapping(value="/api/building/{id}/{name}/")//vlaue of {id} bat buoc phai co va thuong duoc su dung trong xoa du lieu
//	public void deleteBuilding(@PathVariable Integer id ,
//								@PathVariable String name,
//								@RequestParam(value="ward", required=false ) String ward) {
//		System.out.print(name+" da xoa toa nha "+id+" duong "+ward +" roi nhe");
//	}
	
	
}
