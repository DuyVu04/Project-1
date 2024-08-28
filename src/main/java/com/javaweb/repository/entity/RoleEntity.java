package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.catalina.User;

@Entity
@Table(name="role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable =false )
	private String name;
	
	
	@Column(name="code",nullable =false ,unique=true)
	private String code;
	
	
//  Cách thủ công manytomany với userEntity	
//	@OneToMany(mappedBy="role",fetch = FetchType.LAZY)
//	List<UserRoleEntity> userRoleEntities =new ArrayList<UserRoleEntity>();

	
//	public List<UserRoleEntity> getUserRoleEntities() {
//	return userRoleEntities;
//}
//
//public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//	this.userRoleEntities = userRoleEntities;
//} 

//  Cách tự động 
	@ManyToMany(mappedBy ="roles",fetch =FetchType.LAZY)
	private List<UserEntity> users =new ArrayList<UserEntity>();

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}




}
