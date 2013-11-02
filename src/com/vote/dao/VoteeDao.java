package com.vote.dao;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.domain.Votee;
@Component("voteeDao")
public class VoteeDao {

	private SessionFactory sf;
	/**
	 * 获得被投票人列表
	 * @param offset
	 * @param pageSize
	 * @param systemYear
	 * @param deptId
	 * @return
	 */
	public List<Votee> getVoteeList(int offset,int pageSize,int systemYear,int deptId) {
		Session session = sf.getCurrentSession();
		Query q = session.createSQLQuery("select  v.* from t_votee v,t_votee_dept vd where v.id = vd.voteeId and v.year = ? and vd.deptId = ? limit ?,?").addEntity("v", Votee.class);
		q.setInteger(0, systemYear);
		q.setInteger(1, deptId);
		q.setInteger(2, offset);
		q.setInteger(3, pageSize);
		return q.list();
	}
	/**
	 * 
	 * @param systemYear
	 * @param deptId
	 * @return
	 */
	public int countOfVoteeList(int systemYear,int deptId) {
		Session session = sf.getCurrentSession();
		Query q = session.createSQLQuery("select  count(v.id) from t_votee v,t_votee_dept vd where v.id = vd.voteeId and v.year = ? and vd.deptId = ?");
		q.setInteger(0, systemYear);
		q.setInteger(1, deptId);
		BigInteger bigInt = (BigInteger)q.uniqueResult();
		return bigInt.intValue();
	}
	/**
	 * get
	 * 
	 * @param voteeId
	 * @return
	 */
	public Votee getVoteeById(int voteeId) {
		Session session = sf.getCurrentSession();
		Votee votee = (Votee)session.get(Votee.class, voteeId);
		return votee;
	}
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
}
