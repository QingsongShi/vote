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
	 * 获得部门列表（分页）
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
	 * 得到部门的总记录数
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
	 * 根据id删除部门表的信息
	 * @param deptId
	 */
	public void deleteDeptById(int deptId) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("delete Dept d where d.id = ?");
		q.setInteger(0, deptId);
		q.executeUpdate();
	}
	/**
	 * 根据部门id获得部门的信息
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		Session session = sf.getCurrentSession();
		Dept dept = (Dept)session.load(Dept.class, deptId);
		return dept;
	}
	/**
	 * 添加部门信息
	 * @param dept
	 */
	public void addDept(Dept dept) {
		Session session = sf.getCurrentSession();
		session.save(dept);
	} 
	/**
	 * 获得部门列表
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
