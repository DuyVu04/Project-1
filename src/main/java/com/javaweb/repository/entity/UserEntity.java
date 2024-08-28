package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;
import javax.persistence.*;

@Entity
@Table(name="user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username",nullable =false , unique=true)
	private String username;
	
	@Column(name="password",nullable =false)
	private String password;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="email")
	private String email;
	
// Cách thủ công manytomany với role entity	
//	@OneToMany(mappedBy ="user",fetch=FetchType.LAZY)
//	List<UserRoleEntity> userRoleEntities =new ArrayList<UserRoleEntity>();
	
//	public List<UserRoleEntity> getUserRoleEntities() {
//		return userRoleEntities;
//	}
//
//	public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//		this.userRoleEntities = userRoleEntities;
//	}

//  Cách tự động 
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_role",
				joinColumns = @JoinColumn (name ="userid",nullable =false),
				inverseJoinColumns =@JoinColumn(name="roleid",nullable =false))
	private List<RoleEntity> roles =new ArrayList<RoleEntity>();
	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
