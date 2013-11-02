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
	
	public List<Log> getLogList(int systemYear,int voterId) {
		Session session =sf.getCurrentSession();
		Query q = session.createQuery("from Log l where l.year = ? and l.voter.id = ?");
		q.setInteger(0, systemYear);
		q.setInteger(1, voterId);
		return q.list();
	}
	
	@Resource(name ="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}	
}
