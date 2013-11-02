package com.vote.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.domain.Ballot;
@Component("ballotDao")
public class BallotDao {

	private SessionFactory sf;

	public void addBallot(Ballot ballot) {
		Session session = sf.getCurrentSession();
		session.save(ballot);
	}
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
}
