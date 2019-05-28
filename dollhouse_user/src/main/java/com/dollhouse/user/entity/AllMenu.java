package com.dollhouse.user.entity;

import java.util.List;

public class AllMenu {
	private List<Menu> topMenu;
	private List<Menu> leftMenu;
	private List<Menu> lunBo;
	private List<Menu> starMenu;
	public List<Menu> getTopMenu() {
		return topMenu;
	}
	public void setTopMenu(List<Menu> topMenu) {
		this.topMenu = topMenu;
	}
	public List<Menu> getLeftMenu() {
		return leftMenu;
	}
	public void setLeftMenu(List<Menu> leftMenu) {
		this.leftMenu = leftMenu;
	}
	public List<Menu> getLunBo() {
		return lunBo;
	}
	public void setLunBo(List<Menu> lunBo) {
		this.lunBo = lunBo;
	}
	public List<Menu> getStarMenu() {
		return starMenu;
	}
	public void setStarMenu(List<Menu> starMenu) {
		this.starMenu = starMenu;
	}
}
