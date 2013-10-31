package com.vote.action.admin;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("adminLogoutAction")
@Scope(value="prototype")
public class LogoutAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	
	@Override
	public String execute() throws Exception {
		session.remove("onlineAdmin");
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
