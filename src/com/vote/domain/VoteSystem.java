package com.vote.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_vote_system")
public class VoteSystem {

	private int id;
	private int systemYear;
	private int allowLogin;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSystemYear() {
		return systemYear;
	}

	public void setSystemYear(int systemYear) {
		this.systemYear = systemYear;
	}

	public int getAllowLogin() {
		return allowLogin;
	}

	public void setAllowLogin(int allowLogin) {
		this.allowLogin = allowLogin;
	}

	
}
