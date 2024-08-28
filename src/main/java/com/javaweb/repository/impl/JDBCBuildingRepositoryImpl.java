package com.javaweb.repository.impl;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.javaweb.Utils.ConnectJDBCUtil;
import com.javaweb.Utils.NumberUtil;
import com.javaweb.Utils.StringUtil;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
@Repository
//@Primary
public class JDBCBuildingRepositoryImpl implements BuildingRepository {
	
	@PersistenceContext 
	private EntityManager entityManager;
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder ,StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId!=null) {
			sql.append("inner join assignmentbuilding a on b.id=a.buildingid ");		
		}
		List<String> typeCode=buildingSearchBuilder.getTypeCode();
		if(typeCode!=null && typeCode.size()!=0) {
			sql.append("inner join buildingrenttype br on b.id=br.buildingid ");
			sql.append("inner join renttype rt on br.renttypeid =rt.id ");
		}
		Long rentAreaFrom =buildingSearchBuilder.getAreaFrom();
		Long rentAreaTo =buildingSearchBuilder.getAreaTo();
		if(rentAreaFrom!=null ||rentAreaTo!=null ) {
			sql.append(" inner join rentarea ra on b.id=ra.buildingid");
		}
	}
	public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder,StringBuilder where) {
		try {
			Field[] fields =BuildingSearchBuilder.class.getDeclaredFields();
			for(Field item : fields) {
				item.setAccessible(true);
				String fieldName=item.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("area")&&!fieldName.startsWith("rentPrice")) {
					Object value =item.get(buildingSearchBuilder);
					if(value !=null) {
						if(item.getType().getName().equals("java.lang.Long") ||item.getType().getName().equals("java.lang.Integer")|| item.getType().getName().equals("java.lang.Float")) {
							where.append(" AND b."+ fieldName+" = "+value+" ");
						}
						else if(item.getType().getName().equals("java.lang.String")) {
							where.append(" AND b."+fieldName+" Like '%"+value+"%' ");
						}
							
					}
						
				}
			}
		} catch(Exception ex) {
			ex.getStackTrace();
		}
		
	}
	public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder,StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId!=null) {
			where.append(" AND a.staffId ="+staffId+" ");
		}
		Long rentAreaFrom =buildingSearchBuilder.getAreaFrom();
		Long rentAreaTo =buildingSearchBuilder.getAreaTo();
		if(rentAreaFrom!=null ||rentAreaTo!=null){
			where.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE b.id =ra.buildingid ");
			if(rentAreaFrom!=null) {
				where.append(" AND ra.value >="+rentAreaFrom+" ");
			}
			if(rentAreaTo!=null) {
				where.append(" AND ra.value <="+rentAreaTo+" ");
			}
			where.append(") ");
		}
		Long rentPriceFrom =buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo =buildingSearchBuilder.getRentPriceTo();
		if(rentPriceFrom !=null ||rentPriceTo!=null){
			if(rentPriceFrom!=null) {
				where.append(" AND b.rentprice >="+rentPriceFrom+" ");
			}
			if(rentPriceTo!=null) {
				where.append(" AND b.rentprice <="+rentPriceTo+" ");
			}
		}
		List<String> typeCode =buildingSearchBuilder.getTypeCode();
		//code cux dungf java 7 h dungf java 8
		if(typeCode!=null && typeCode.size()!=0) {
			where.append(" AND(");
			String query= typeCode.stream().map(it->"rt.code Like"+"'%"+it+"%' ").collect(Collectors.joining(" OR "));
			where.append(query);
			where.append(" ) ");
		}
	}
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql= new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managername, b.managerphonenumber, b.servicefee, b.brokeragefee From building b "); //Tim toa nha co tu xuat hien 
		joinTable(buildingSearchBuilder , sql);
		StringBuilder where =new StringBuilder(" Where 1=1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		sql.append(where);
		sql.append(" GROUP BY b.id ");
		Query query =entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		
		return query.getResultList();
	}

}


