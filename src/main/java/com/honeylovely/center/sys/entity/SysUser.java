package com.honeylovely.center.sys.entity;



import com.honeylovely.center.common.abstracts.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sys_user")
public class SysUser extends AbstractEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String username;
	private String password;
	private Integer gender;
	private Date birthday;
	@Column(name = "credentials_salt")
	private String credentialsSalt;
	private String mobile;
	private String email;
	private String description;
	@Column(name = "is_locked")
	private Boolean isLocked;

	@Transient
	List<SysRole> roles;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public Integer getGender() {
		return gender;
	}

	public String getGenderStr() {
		return Gender.getGender(this.gender == null ? 0 : gender).getStr();
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}

	public String getCredentialsSalt() {
		return this.credentialsSalt;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsLocked() {
		return this.isLocked;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public enum Gender {
		UNKNOWN(0, "未知"), MALE(1, "男"), FEMALE(2, "女");
		private int val;
		private String str;

		private Gender(int val, String str) {
			this.val = val;
			this.str = str;
		}

		public int getVal() {
			return val;
		}

		public String getStr() {
			return str;
		}

		public static Gender getGender(int val) {
			for (Gender g : Gender.values()) {
				if (g.getVal() == val)
					return g;
			}

			return UNKNOWN;
		}
	}
}