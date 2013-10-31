package com.vote.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_voter")
public class Voter {

	private int id;
	private int year;
	private String username;
	private String account;
	private String password;
	private Dept dept;
	private Set<Dept> permitDeptSet = new HashSet<Dept>();
	@Id
	@GeneratedValue
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deptid")
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
		name="t_voter_dept",
		joinColumns={@JoinColumn(name="voterId")},
		inverseJoinColumns={@JoinColumn(name="deptId")}
	)
	public Set<Dept> getPermitDeptSet() {
		return permitDeptSet;
	}

	public void setPermitDeptSet(Set<Dept> permitDeptSet) {
		this.permitDeptSet = permitDeptSet;
	}
}
