package com.vote.action.user;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.service.UserService;
/**
 * �û���¼ʱ���ϵͳ�ĵ�¼״̬
 * @author qingsong
 *
 */
@Component("userLoginInputAction")
@Scope(value="prototype")
public class LoginInputAction extends ActionSupport {
	
	private UserService userService;
	private String forbidLoginMessage;
	
	@Override
	public String execute() throws Exception {
		if(userService.getVoteSystem().getAllowLogin()==0) {
			//forbid login
			this.forbidLoginMessage = "ͶƱ��������ɣ�����ϵͳ��ֹ�û���¼";
		}
		return SUCCESS;
	}
	
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getForbidLoginMessage() {
		return forbidLoginMessage;
	}
}
