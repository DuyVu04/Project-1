package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.Utils.ConnectJDBCUtil;
import com.javaweb.Utils.NumberUtil;
import com.javaweb.Utils.StringUtil;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	public static void joinTable(Map<String,Object> params,List<String> typeCode ,StringBuilder sql) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			sql.append("inner join assignmentbuilding a on b.id=a.buildingid ");		
		}
		if(typeCode!=null && typeCode.size()!=0) {
			sql.append("inner join buildingrenttype br on b.id=br.buildingid ");
			sql.append("inner join renttype rt on br.renttypeid =rt.id ");
		}
		String rentAreaFrom =(String)params.get("areaFrom");
		String rentAreaTo =(String)params.get("areaTo");
		if(StringUtil.checkString(rentAreaFrom) ||StringUtil.checkString(rentAreaTo) ) {
			sql.append(" inner join rentarea ra on b.id=ra.buildingid");
		}
	}
	public static void queryNormal(Map<String,Object> params,StringBuilder where) {
		for(Map.Entry<String,Object> it:params.entrySet()) {
			if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") && !it.getKey().startsWith("area")&&!it.getKey().startsWith("rentPrice")) {
				String value =it.getValue().toString();
				if(NumberUtil.isNumber(value)) {
					where.append(" AND b."+it.getKey()+"="+value+" ");
				}
				else {
					where.append(" AND b."+it.getKey()+" LIKE '%"+value+"%' ");
				}
			}
		}
		
	}
	public static void querySpecial(Map<String,Object> params,List<String> typeCode,StringBuilder where) {
		String staffId = (String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			where.append(" AND a.staffId ="+staffId+" ");
		}
		String rentAreaFrom =(String)params.get("areaFrom");
		String rentAreaTo =(String)params.get("areaTo");
		if(StringUtil.checkString(rentAreaFrom) ||StringUtil.checkString(rentAreaTo)){
			if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND ra.value >="+rentAreaFrom+" ");
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND ra.value <="+rentAreaTo+" ");
			}
		}
		String rentPriceFrom =(String)params.get("rentPriceFrom");
		String rentPriceTo =(String)params.get("rentPriceTo");
		if(StringUtil.checkString(rentPriceFrom) ||StringUtil.checkString(rentPriceTo)){
			if(StringUtil.checkString(rentPriceFrom)) {
				where.append(" AND b.rentprice >="+rentPriceFrom+" ");
			}
			if(StringUtil.checkString(rentPriceTo)) {
				where.append(" AND b.rentprice <="+rentPriceTo+" ");
			}
		}
		if(typeCode!=null && typeCode.size()!=0) {
			List<String> code =new ArrayList<String>();
			for(String item:typeCode) {
				code.add(" '"+item+"' ");
			}
			where.append(" AND rt.code IN ("+String.join(",", code)+") ");
		}
	}
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params,List<String> typeCode) {
		StringBuilder sql= new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee From building b "); //Tim toa nha co tu xuat hien 
		joinTable( params, typeCode , sql);
		StringBuilder where =new StringBuilder(" Where 1=1 ");
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		sql.append(where);
		sql.append(" GROUP BY b.id ");
		List<BuildingEntity> result =new ArrayList<>();
		try(Connection conn= ConnectJDBCUtil.getConnection();
				Statement stmt =conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());){
				while(rs.next()) {
					BuildingEntity buildingEntity =new BuildingEntity();
					buildingEntity.setId(rs.getLong("b.id"));
					buildingEntity.setName(rs.getString("b.name"));
					buildingEntity.setWard(rs.getString("b.ward"));
					buildingEntity.setNumberOfBasement(rs.getLong("b.numberofbasement"));
					buildingEntity.setDistrictid(rs.getLong("b.districtid"));
					buildingEntity.setStreet(rs.getString("b.street"));
					buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
					buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
					buildingEntity.setServiceFee(rs.getString("b.servicefee"));
					buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
					buildingEntity.setManagerName(rs.getString("b.managername"));
					buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
					result.add(buildingEntity);			
				}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}


