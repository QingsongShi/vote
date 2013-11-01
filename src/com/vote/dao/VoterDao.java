package com.vote.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.vote.domain.Voter;
/**
 * 
 * @author qingsong
 *
 */
@Component("voterDao")
public class VoterDao {

	private SessionFactory sf;
	/**
	 * 根据账号得到用户的信息 
	 * @param account
	 * @return
	 */
	public Voter getVoterByAccount(String account) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from Voter v left join fetch v.dept where v.account = ?");
		q.setString(0, account);
		Voter voter = (Voter)q.uniqueResult();
		return voter;
	}
	/**
	 * 根据id获得用户的所有信息
	 * @param voterId
	 * @return
	 */
	public Voter getVoterById(int voterId) {
		Session session = sf.getCurrentSession();
		Voter voter = (Voter)session.load(Voter.class,voterId);
		return voter;
	}
	/**
	 * 得到用户的所用信息（分页）
	 * @param offset
	 * @param pageSize
	 * @param systemYear 
	 * @return
	 */
	public List<Voter> getVoterList(int offset,int pageSize,int systemYear,int deptId) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from Voter v left join fetch v.dept where v.year = ? and v.dept.id = ?");
		q.setInteger(0, systemYear);
		q.setInteger(1, deptId);
		q.setFirstResult(offset);
		q.setMaxResults(pageSize);
		List<Voter> list = q.list();
		return list;
	}
	/**
	 * 得到用户的记录数（分页）
	 * @param systemYear
	 * @return
	 */
	public int countOfVoterList(int systemYear,int deptId) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("select count(v.id) from Voter v where v.year = ? and v.dept.id = ?");
		q.setInteger(0, systemYear);
		q.setInteger(1, deptId);
		Long count = (Long)q.uniqueResult();
		return count.intValue();
	}
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deleteVoterById(int voterId) {
		Session session = sf.getCurrentSession();
//		Query q = session.createQuery("delete from User u where u.id = ?");
//		q.setInteger(0, userId);
//		q.executeUpdate();
		Voter voter = new Voter();
		voter.setId(voterId);
		session.delete(voter);
	}
	/**
	 * 保存用户
	 * @param user
	 */
	public void addVoter(Voter voter) {
		Session session = sf.getCurrentSession();
		session.save(voter);
	}
	/**
	 * 修改用户
	 * @param user
	 */
	public void updateVoter(Voter voter) {
		Session session = sf.getCurrentSession();
		Voter u = (Voter)session.get(Voter.class, voter.getId());
		u.setUsername(voter.getUsername());
		u.setAccount(voter.getAccount());
		u.setPassword(voter.getPassword());
		u.setDept(voter.getDept());
		session.update(u);
	}
	/**
	 * 得到某年的所用用户信息
	 * @param systemYear
	 * @return
	 */
	public List<Voter> getVoterListBySystemYear(int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from Voter v left join fetch v.dept where v.year = ?");
		q.setInteger(0, systemYear);
		return q.list();
	}
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
