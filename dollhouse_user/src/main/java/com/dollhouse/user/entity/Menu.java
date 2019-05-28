package com.dollhouse.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Menu implements Serializable{

	private Long id;
	
	private Long pid;
	
	private String name;
	
	private String menuType;
	
	private BigDecimal menuPrice;
	
	private String menuImgUrl;
	
	private String menuLocation;
	
	private String menuLabel;
	
	private List<Menu> subMenu;
	
	private Long linkId;
	
	private boolean active;

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

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public BigDecimal getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(BigDecimal menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuImgUrl() {
		return menuImgUrl;
	}

	public void setMenuImgUrl(String menuImgUrl) {
		this.menuImgUrl = menuImgUrl;
	}

	public String getMenuLocation() {
		return menuLocation;
	}

	public void setMenuLocation(String menuLocation) {
		this.menuLocation = menuLocation;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	
	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", name=" + name + ","
				+ " menuLocation=" + menuLocation + ", menuLabel=" + menuLabel
				+ ", subMenu=" + subMenu + "]";
	}
	
}
