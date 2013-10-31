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
	//�ۺ�����
	private int overallMerit;
	//˼��Ʒ��
	private int ideology;
	//��������
	private int workingAbility;
	//��ҵ����
	private int professionalEthics;
	//��������
	private int clean;
	//��������
	private int innovationAbility;
	//��������(����Ŀ��������)
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