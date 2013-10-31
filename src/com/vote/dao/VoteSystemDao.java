package com.vote.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.domain.VoteSystem;

@Component("voteSystemDao")
public class VoteSystemDao {

	private SessionFactory sf;
	
	/**
	 * 得到系统信息配置的对象
	 * @return
	 */
	public VoteSystem getVoteSystem() {
		Session session = sf.getCurrentSession();
		VoteSystem voteSystem = (VoteSystem)session.load(VoteSystem.class, 1);	
		return voteSystem;
	}
	/**
	 * 修改系统信息
	 * @param voteSystem
	 */
	public void updateVoteSystem(VoteSystem voteSystem) {
		Session session = sf.getCurrentSession();
		session.update(voteSystem);
	}
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
}
