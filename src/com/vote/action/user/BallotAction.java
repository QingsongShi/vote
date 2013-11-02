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
import com.vote.domain.Votee;
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
	private Votee votee;
	private int deptId;
	private Pager pager;
	/**
	 * 接受被投票人的id，并显示被投票人的信息。
	 * 需要对投票权限进行检查
	 */
	@Override
	public String execute() throws Exception {
		Voter voter = (Voter)session.get("onlineUser");
		// 投票人
		voter = userService.getVoterById(voter.getId());
		votee = userService.getVoteeById(userId);
		if(votee==null) {
			return ERROR;
		}
		// 权限进行检查
		Set<Dept> permitDeptSet = voter.getPermitDeptSet();
		Iterator<Dept> voterDeptIt = permitDeptSet.iterator();
		while(voterDeptIt.hasNext()) {
			Dept permitDept = voterDeptIt.next();
			
			Iterator<Dept> voteeDeptIt = votee.getBelongDeptSet().iterator();
			
			while(voteeDeptIt.hasNext()) {
				Dept belongDept = voteeDeptIt.next();
				if(permitDept.getId() == belongDept.getId()) {
					return SUCCESS;
				}
			}
				
			
		}
		return ERROR;
		
	}
	/**
	 * 添加投票结果
	 * @return
	 */
	public String addBallot() { 
		Voter voter = (Voter)session.get("onlineUser");
		// 投票人
		voter = userService.getVoterById(voter.getId());
		votee = userService.getVoteeById(ballot.getVotee().getId());
		if(votee==null) {
			return ERROR;
		}
		if(voter.getYear()!=votee.getYear()) {
			return ERROR;
		}
		// 权限进行检查
		Set<Dept> permitDeptSet = voter.getPermitDeptSet();
		boolean result = false;
		Iterator<Dept> voterDeptIt = permitDeptSet.iterator();
		while(voterDeptIt.hasNext()) {
			Dept permitDept = voterDeptIt.next();
			
			Iterator<Dept> voteeDeptIt = votee.getBelongDeptSet().iterator();
			
			while(voteeDeptIt.hasNext()) {
				Dept belongDept = voteeDeptIt.next();
				if(permitDept.getId() == belongDept.getId()) {
					// 具有投票权限
					result = true;
				}
			}
			// 当已经检查到具有权限时，跳出循环。
			if(result) {
				break;
			}
		}
		if(!result) {
			return ERROR;
		}
		Log log = new Log();
		int systemYear = userService.getVoteSystem().getSystemYear();
		log.setYear(systemYear);
		log.setCreateTime(new Date());
		log.setVotee(votee);
		log.setVoter(voter);
		ballot.setYear(systemYear);
		ballot.setVotee(votee);
		if(userService.addBallot(ballot,log)) {
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

	public Votee getVotee() {
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
