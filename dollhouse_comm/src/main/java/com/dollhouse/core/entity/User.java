package com.dollhouse.core.entity;

import java.io.Serializable;

public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1288119096954955249L;

	private Long id;
	
	private Integer type;
	
	private String userName;
	
	private String password;
	
	private Long phone;
	
	private Long qq;
	
	private String siteName;
	
	private String siteLogo;
	
	private String copyRight;
	
	private String nickname;
	
	private String remoteIp;
	
	private String lastLoginTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteLogo() {
		return siteLogo;
	}

	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public String getCopyRight() {
		return copyRight;
	}
	
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
}