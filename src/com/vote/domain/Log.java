package com.vote.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_log")
public class Log {

	private String id;
	private int year;
	// 选举人，投票人，选民
	private Voter voter; 
	// 被投票人
	private Voter votee;
	private Date createTime;

	@Id
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="voterId")
	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="voteeId")
	public Voter getVotee() {
		return votee;
	}

	public void setVotee(Voter votee) {
		this.votee = votee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
