package com.vote.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.domain.Admin;

@Component("adminDao")
public class AdminDao {

	private SessionFactory sf;
	/**
	 * 根据管理员账号得到管理员的所有信息
	 * @param account
	 * @return
	 */
	public Admin getAdminByAccount(String account) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from Admin a where a.account = ?");
		q.setString(0, account);
		Admin admin = (Admin)q.uniqueResult();
		return admin;
	}
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
