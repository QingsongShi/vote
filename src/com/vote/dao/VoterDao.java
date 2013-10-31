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
	 * �����˺ŵõ��û�����Ϣ 
	 * @param account
	 * @return
	 */
	public Voter getVoterByAccount(String account) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from voter v where v.account = ?");
		q.setString(0, account);
		Voter user = (Voter)q.uniqueResult();
		return user;
	}
	/**
	 * ����id����û���������Ϣ
	 * @param voterId
	 * @return
	 */
	public Voter getVoterById(int voterId) {
		Session session = sf.getCurrentSession();
		Voter user = (Voter)session.load(Voter.class,voterId);
		return user;
	}
	/**
	 * �õ��û���������Ϣ����ҳ��
	 * @param offset
	 * @param pageSize
	 * @param systemYear
	 * @return
	 */
	public List<Voter> getUserList(int offset,int pageSize,int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from User u left join fetch u.dept where u.year = ?");
		q.setInteger(0, systemYear);
		q.setFirstResult(offset);
		q.setMaxResults(pageSize);
		List<Voter> list = q.list();
		return list;
	}
	/**
	 * �õ��û��ļ�¼������ҳ��
	 * @param systemYear
	 * @return
	 */
	public int countOfUserList(int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("select count(u.id) from User u where u.year = ?");
		q.setInteger(0, systemYear);
		Long count = (Long)q.uniqueResult();
		return count.intValue();
	}
	/**
	 * ɾ���û�
	 * @param userId
	 */
	public void deleteUserById(int userId) {
		Session session = sf.getCurrentSession();
//		Query q = session.createQuery("delete from User u where u.id = ?");
//		q.setInteger(0, userId);
//		q.executeUpdate();
		Voter u = new Voter();
		u.setId(userId);
		session.delete(u);
	}
	/**
	 * �����û�
	 * @param user
	 */
	public void addUser(Voter user) {
		Session session = sf.getCurrentSession();
		session.save(user);
	}
	/**
	 * �޸��û�
	 * @param user
	 */
	public void updateUser(Voter user) {
		Session session = sf.getCurrentSession();
		Voter u = (Voter)session.get(Voter.class, user.getId());
		u.setUsername(user.getUsername());
		u.setAccount(user.getAccount());
		u.setPassword(user.getPassword());
		u.setDept(user.getDept());
		session.update(u);
	}
	/**
	 * �õ�ĳ��������û���Ϣ
	 * @param systemYear
	 * @return
	 */
	public List<Voter> getUserListBySystemYear(int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from User u left join fetch u.dept where u.year = ?");
		q.setInteger(0, systemYear);
		return q.list();
	}
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
