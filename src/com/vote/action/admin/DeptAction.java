package com.vote.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.domain.Dept;
import com.vote.dto.PageModel;
import com.vote.dto.Pager;
import com.vote.service.AdminService;

@Component("adminDeptAction")
@Scope("prototype")
public class DeptAction extends ActionSupport {

	private AdminService adminService;
	private Pager pager;
	private PageModel<Dept> deptPM;
	private int systemYear;
	private int deptId;
	private Dept deleteFailedDept;
	private String deptName;
	/**
	 * 获得部门的分页信息
	 * @return
	 */
	public String getDeptList() {
		deptPM = adminService.getDeptList(this.getPager().getOffset(), this.getPageSize());
		systemYear = adminService.getVoteSystemConfig().getYear();
		return SUCCESS;
	}
	/**
	 * 根据部门id删除部门信息
	 * @return
	 */
	public String deleteDeptById() {
		boolean result = adminService.deleteDeptById(deptId);
		if(result) {
			return SUCCESS;
		}else {
			deleteFailedDept = adminService.getDeptById(deptId);
			return ERROR;
		}
	}
	/**
	 * 
	 * @return
	 */
	public String addDept() {
		adminService.addDept(deptName);
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		systemYear = adminService.getVoteSystemConfig().getYear();
		return SUCCESS;
	}
	
	
	
	
	
	
	@Resource(name="adminService")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public int getPageSize() {
		return Integer.parseInt(ServletActionContext
			.getServletContext().getInitParameter("pageSize"));
	}
	public Pager getPager() {
		if(this.pager==null) {
			Pager p = new Pager();
			p.setOffset(0);
			this.pager = p;
		}
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public PageModel<Dept> getDeptPM() {
		return deptPM;
	}

	public void setDeptPM(PageModel<Dept> deptPM) {
		this.deptPM = deptPM;
	}

	public int getSystemYear() {
		return systemYear;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public Dept getDeleteFailedDept() {
		return deleteFailedDept;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
