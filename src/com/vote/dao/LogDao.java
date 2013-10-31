package com.vote.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.domain.Log;
@Component("logDao") 
public class LogDao {
	private SessionFactory sf;
	
	/**
	 * ±£¥Ê»’÷æ
	 * @param Log
	 */
	public void addLog(Log log){
		Session session =sf.getCurrentSession();
		session.save(log);
	}

	@Resource(name ="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}	
}
