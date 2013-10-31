package com.vote.action.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.domain.Admin;
import com.vote.service.AdminService;

@Component("adminLoginAction")
@Scope(value="prototype")
public class LoginAction extends ActionSupport implements SessionAware {

	private String account;
	private String password;
	private AdminService adminService;
	private Map<String, Object> session;
	
	@Override
	public String execute() throws Exception {
		Admin admin = adminService.validateAdminLogin(account, password);
		if(admin==null) {
			this.addFieldError("loginFailMsg", "用户名或密码错误");
			return INPUT;
		}else {
			//登录成功，把登录的管理员信息存到session中
			session.put("onlineAdmin", admin);
			return SUCCESS;
		}
	}
	/**
	 * 输入校验方法
	 */
	@Override
	public void validate() {
		if(account==null || "".equals(account)) {
			this.addFieldError("loginFailMsg", "用户名不能为空");
		}
		if(password==null || "".equals(password)) {
			this.addFieldError("loginFailMsg", "密码不能为空");
		}
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Resource(name="adminService")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
}
