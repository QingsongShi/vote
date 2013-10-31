package com.vote.action.user;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.vote.domain.Ballot;
import com.vote.domain.Dept;
import com.vote.domain.Log;
import com.vote.domain.Voter;
import com.vote.dto.Pager;
import com.vote.service.UserService;

@Component("userBallotAction")
@Scope("prototype")
public class BallotAction extends ActionSupport implements SessionAware {

	private Ballot ballot;
	private UserService userService;
	private int userId;
	private Map<String, Object> session;
	private Voter votee;
	private int deptId;
	private Pager pager;
	/**
	 * 接受被投票人的id，并显示被投票人的信息。
	 * 需要对投票权限进行检查
	 */
	@Override
	public String execute() throws Exception {
		Voter user = (Voter)session.get("onlineUser");
		Voter voter = userService.loadUser(user.getId());
		votee = userService.loadUser(userId);
		// 权限进行检查
		Set<Dept> permitDeptSet = voter.getDeptSet();
		boolean result = false;
		Iterator<Dept> it = permitDeptSet.iterator();
		while(it.hasNext()) {
			Dept permitDept = it.next();
			if(permitDept.getId() == votee.getDept().getId()) {
				result = true;
				break;
			}
		}
		if(result) {
			return SUCCESS;
		} else {
			return ERROR;
		}
		
	}
	/**
	 * 添加投票结果
	 * @return
	 */
	public String addBallot() {
		Log log = new Log();
		log.setVoter((Voter)session.get("onlineUser"));
		log.setVotee(ballot.getVotee());
		log.setCreateTime(new Date());
		boolean result = userService.addBallot(ballot, log);
		if(result) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		

	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Ballot getBallot() {
		return ballot;
	}

	public void setBallot(Ballot ballot) {
		this.ballot = ballot;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Voter getVotee() {
		return votee;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
}
