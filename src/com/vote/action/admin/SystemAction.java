package com.vote.action.admin;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.domain.VoteSystem;
import com.vote.service.AdminService;

@Component("adminSystemAction")
@Scope(value="prototype")
public class SystemAction extends ActionSupport {

	private VoteSystem vSystem;
	private AdminService adminService;
	private int systemYear;
	private int systemAllowLogin;
	
	/**
	 * 获得系统运行信息
	 * @return
	 */
	public String getVoteSystemConfig() {
		vSystem = adminService.getVoteSystemConfig();
		return SUCCESS;
	}
	/**
	 * 修改系统信息
	 * @return
	 */
	public String updateVoteSystemConfig() {
		VoteSystem vs = new VoteSystem();
		vs.setId(1);
		vs.setYear(systemYear);
		vs.setAllowLogin(systemAllowLogin);
		adminService.updateVoteSystemConfig(vs);
		return SUCCESS;
	}
	
	
	
	@Resource(name="adminService")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public VoteSystem getVSystem() {
		return vSystem;
	}

	public void setVSystem(VoteSystem vSystem) {
		this.vSystem = vSystem;
	}
	public int getSystemYear() {
		return systemYear;
	}
	public void setSystemYear(int systemYear) {
		this.systemYear = systemYear;
	}
	public int getSystemAllowLogin() {
		return systemAllowLogin;
	}
	public void setSystemAllowLogin(int systemAllowLogin) {
		this.systemAllowLogin = systemAllowLogin;
	}
	
}
