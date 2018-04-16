package com.honeylovely.center.sys.entity;



import com.honeylovely.center.common.abstracts.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sys_role")
public class SysRole extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 4044049662267939336L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "role_key")
	private String roleKey;
	private String description;
	private Byte status;

	@Transient
	List<SysResource> resources;

	@Transient
	List<SysUser> users;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getRoleKey() {
		return this.roleKey;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public List<SysResource> getResources() {
		return resources;
	}

	public void setResources(List<SysResource> resources) {
		this.resources = resources;
	}

	public List<SysUser> getUsers() {
		return users;
	}

	public void setUsers(List<SysUser> users) {
		this.users = users;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}