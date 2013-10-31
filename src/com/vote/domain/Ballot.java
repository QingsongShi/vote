package com.vote.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_ballot")
public class Ballot { 

	private int id;
	private int year;
	private Votee votee;
	//综合评价
	private int overallMerit;
	//思想品德
	private int ideology;
	//工作能力
	private int workingAbility;
	//敬业精神
	private int professionalEthics;
	//廉洁自律
	private int clean;
	//创新能力
	private int innovationAbility;
	//定量考核(工作目标完成情况)
	private int completionOfJobs;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="voteeId")
	public Votee getVotee() {
		return votee;
	}
	public void setVotee(Votee votee) {
		this.votee = votee;
	}
	public int getOverallMerit() {
		return overallMerit;
	}
	public void setOverallMerit(int overallMerit) {
		this.overallMerit = overallMerit;
	}
	public int getIdeology() {
		return ideology;
	}
	public void setIdeology(int ideology) {
		this.ideology = ideology;
	}
	public int getWorkingAbility() {
		return workingAbility;
	}
	public void setWorkingAbility(int workingAbility) {
		this.workingAbility = workingAbility;
	}
	public int getProfessionalEthics() {
		return professionalEthics;
	}
	public void setProfessionalEthics(int professionalEthics) {
		this.professionalEthics = professionalEthics;
	}
	public int getClean() {
		return clean;
	}
	public void setClean(int clean) {
		this.clean = clean;
	}
	public int getInnovationAbility() {
		return innovationAbility;
	}
	public void setInnovationAbility(int innovationAbility) {
		this.innovationAbility = innovationAbility;
	}
	public int getCompletionOfJobs() {
		return completionOfJobs;
	}
	public void setCompletionOfJobs(int completionOfJobs) {
		this.completionOfJobs = completionOfJobs;
	}
	
}