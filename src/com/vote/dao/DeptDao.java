package com.vote.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.vote.domain.Dept;

@Component("deptDao")
public class DeptDao {

	private SessionFactory sf;
	/**
	 * ��ò����б���ҳ��
	 * @param offset
	 * @param pageSize
	 * @param systemYear
	 * @return
	 */
	public List<Dept> getDeptListBySystemYear(int offset,int pageSize,int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from Dept d where d.year = ?");
		q.setInteger(0, systemYear);
		q.setFirstResult(offset);
		q.setMaxResults(pageSize);
		return q.list();
	}
	
	/**
	 * �õ����ŵ��ܼ�¼��
	 * @param systemYear
	 * @return
	 */
	public int countOfDeptByYear(int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("select count(d.id) from Dept d where d.year = ?");
		q.setInteger(0, systemYear);
		Long count = (Long)q.uniqueResult();
		return count.intValue();
	}
	
	/**
	 * ����idɾ�����ű����Ϣ
	 * @param deptId
	 */
	public void deleteDeptById(int deptId) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("delete Dept d where d.id = ?");
		q.setInteger(0, deptId);
		q.executeUpdate();
	}
	/**
	 * ���ݲ���id��ò��ŵ���Ϣ
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		Session session = sf.getCurrentSession();
		Dept dept = (Dept)session.load(Dept.class, deptId);
		return dept;
	}
	/**
	 * ��Ӳ�����Ϣ
	 * @param dept
	 */
	public void addDept(Dept dept) {
		Session session = sf.getCurrentSession();
		session.save(dept);
	} 
	/**
	 * ��ò����б�
	 * @param systemYear
	 * @return
	 */
	public List<Dept> getDeptListBySystemYear(int systemYear) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("from Dept d where d.year = ?");
		q.setInteger(0, systemYear);
		List<Dept> deptList = q.list();
		return deptList;
	}
	
	
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
}
