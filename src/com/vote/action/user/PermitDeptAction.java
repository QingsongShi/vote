package com.vote.action.user;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.domain.Dept;
import com.vote.domain.Log;
import com.vote.domain.Voter;
import com.vote.dto.PageModel;
import com.vote.dto.Pager;
import com.vote.service.UserService;

@Component("userPermitDeptAction")
@Scope(value = "prototype")
public class PermitDeptAction extends ActionSupport implements SessionAware {

	// 从web.xml中取得页面显示最大条数
	private int pageSize;
	private Map<String, Object> session;
	private Voter onlineUser;
	private PageModel<Voter> userPM;
	private UserService userService;
	private Pager pager;	
	private int deptId;
	private List<Log> logList;
	/**
	 * 获得部门内部投票名单
	 * @return
	 */
	public String getPermitDeptNamePM() {
		onlineUser = (Voter)this.session.get("onlineUser");
		Voter voter = userService.loadUser(onlineUser.getId());
		Set<Dept> permitDepts = voter.getDeptSet();
		Iterator<Dept> it = permitDepts.iterator();
		// 判断该用户是否具有投票权限
		boolean result = false;
		while(it.hasNext()) {
			Dept dept = it.next();
			if(dept.getId()==deptId) {
				result = true;
				break;
			}
		}
		if(result) {
			// 具有投票权限
			userPM = userService.getPermitDeptNamePM(this.getPager().getOffset(), this.getPageSize(), deptId);
			logList = userService.getLogListByVoterId(onlineUser.getId());
			return SUCCESS;
		}else {
			// 没有投票权限
			return ERROR;
		}
		
	}
	
	public String getPermitDeptList() {
		onlineUser = (Voter)this.session.get("onlineUser");
		onlineUser = userService.loadUser(onlineUser.getId());
		
		return SUCCESS;
	}
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public Voter getOnlineUser() {
		return onlineUser;
	}

	public PageModel<Voter> getUserPM() {
		return userPM;
	}

	public void setUserPM(PageModel<Voter> userPM) {
		this.userPM = userPM;
	}

	public List<Log> getLogList() {
		return logList;
	}
	
}
