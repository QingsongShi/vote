package com.vote.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_votee")
public class Votee {

	private int id;
	private int year;
	private String username;
	private int isLeaderShip;
	private String report;
	
	private Set<Dept> belongDeptSet = new HashSet<Dept>();
	private Set<Log> logSet = new HashSet<Log>();
	private List<Ballot> BallotSet = new ArrayList<Ballot>();
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
	public int getIsLeaderShip() {
		return isLeaderShip;
	}
	public void setIsLeaderShip(int isLeaderShip) {
		this.isLeaderShip = isLeaderShip;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(
			name="t_votee_dept",
			joinColumns={@JoinColumn(name="voteeId")},
			inverseJoinColumns={@JoinColumn(name="deptId")}
		)
	public Set<Dept> getBelongDeptSet() {
		return belongDeptSet;
	}
	public void setBelongDeptSet(Set<Dept> belongDeptSet) {
		this.belongDeptSet = belongDeptSet;
	}
	@OneToMany(mappedBy="votee",fetch=FetchType.LAZY)
	public Set<Log> getLogSet() {
		return logSet;
	}
	public void setLogSet(Set<Log> logSet) {
		this.logSet = logSet;
	}
	@OneToMany(mappedBy="votee",fetch=FetchType.LAZY)
	public List<Ballot> getBallotSet() {
		return BallotSet;
	}
	public void setBallotSet(List<Ballot> ballotSet) {
		BallotSet = ballotSet;
	}
	
}
