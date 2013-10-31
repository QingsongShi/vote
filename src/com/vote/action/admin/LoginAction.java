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
			this.addFieldError("loginFailMsg", "�û������������");
			return INPUT;
		}else {
			//��¼�ɹ����ѵ�¼�Ĺ���Ա��Ϣ�浽session��
			session.put("onlineAdmin", admin);
			return SUCCESS;
		}
	}
	/**
	 * ����У�鷽��
	 */
	@Override
	public void validate() {
		if(account==null || "".equals(account)) {
			this.addFieldError("loginFailMsg", "�û�������Ϊ��");
		}
		if(password==null || "".equals(password)) {
			this.addFieldError("loginFailMsg", "���벻��Ϊ��");
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
