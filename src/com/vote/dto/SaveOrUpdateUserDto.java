package com.vote.dto;

public class SaveOrUpdateUserDto {

	private int id;
	private int year;
	private String username;
	private String account;
	private String password;
	private int deptId;
	private int[] permitDeptIds;
	private String report;
	private int isLeaderShip;

	
	public int getIsLeaderShip() {
		return isLeaderShip;
	}

	public void setIsLeaderShip(int isLeaderShip) {
		this.isLeaderShip = isLeaderShip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int[] getPermitDeptIds() {
		return permitDeptIds;
	}

	public void setPermitDeptIds(int[] permitDeptIds) {
		this.permitDeptIds = permitDeptIds;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

}
