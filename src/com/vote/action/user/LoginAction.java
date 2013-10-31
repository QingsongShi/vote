package com.vote.action.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.domain.Voter;
import com.vote.service.UserService;
/**
 * validate user login action 
 * @author qingsong
 *
 */
@Component("userLoginAction") 
@Scope("prototype")
public class LoginAction extends ActionSupport implements SessionAware {

	private String account;
	private String password;
	private UserService userService;
	private Map<String,Object> session;
	/**
	 * validate user login
	 */
	@Override
	public String execute() throws Exception {
		
		int systemYear = userService.getVoteSystem().getSystemYear();
		if(userService.getAllowLogin()) {
			//System allow user login
			Voter user = userService.validateUserLogin(account, password);
			
			if(user!=null && systemYear==user.getYear()) {
				//登陆成功
				session.put("onlineUser", user);
				return SUCCESS;
			}else {
				//登陆失败
				this.addFieldError("loginFailMsg", "用户名或密码错误");
				return INPUT;
			}
		}else {
			//System forbid user login
			return INPUT;
		}
		
	}
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
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
