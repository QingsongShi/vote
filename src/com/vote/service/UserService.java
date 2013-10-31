package com.vote.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.vote.dao.BallotDao;
import com.vote.dao.DeptDao;
import com.vote.dao.LogDao;
import com.vote.dao.VoteSystemDao;
import com.vote.dao.VoterDao;
import com.vote.domain.Ballot;
import com.vote.domain.Log;
import com.vote.domain.Voter;
import com.vote.domain.VoteSystem;
import com.vote.dto.PageModel;
/**
 * 封装用户的业务逻辑
 * @author qingsong
 * 
 */
@Component("userService")
public class UserService {
	private LogDao logDao;
	private VoterDao voterDao;
	private VoteSystemDao voteSystemDao;
	private DeptDao deptDao;
	/**
	 * 验证用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	public Voter validateUserLogin(String account,String password) {
		Voter user = userDao.getUserByAccount(account);
		if(user!=null&&user.getPassword().equals(password)) {
			return user;
		}else {
			return null;
		}
	}
	/**
	 * 得到系统信息
	 * @return
	 */
	public VoteSystem getVoteSystem() {
		
		return voteSystemDao.getVoteSystem();
	}
	
	
	/**
	 * 得到部门内部投票名单的分页模型
	 * @param pageNo
	 * @param pageSize
	 * @param deptId
	 * @return
	 */
	public PageModel<Voter> getPermitDeptNamePM(int offset,int pageSize,int deptId) {
		PageModel<Voter> userPM = new PageModel<Voter>();
		int systemYear = systemDao.getVoteSystemConfig().getYear();
		userPM.setList(userDao.getDeptUserList(offset, pageSize, deptId,systemYear));
		userPM.setPageSize(pageSize);
		userPM.setTotalRecords(userDao.countOfDeptUserList(deptId,systemYear));
		return userPM;
	}
	/**
	 * 根据用户id获得用户的所有信息
	 * @param userId
	 * @return
	 */
	public Voter loadUser(int userId) {
		Voter user = userDao.loadUser(userId);
		return user;
	}
	/**
	 * 添加投票
	 * @param ballot
	 * @param log
	 */
	public boolean addBallot(Ballot ballot,Log log) {
		int systemYear = systemDao.getVoteSystemConfig().getYear();
		List<Log> logList = logDao.getLogListByVoterId(log.getVoter().getId(), systemYear);
		Iterator<Log> it = logList.iterator();
		while(it.hasNext()) {
			Log voterLog = it.next();
			if(voterLog.getVotee().getId()==log.getVotee().getId()) {
				return false;
			}
		}
		ballot.setYear(systemYear);
		log.setYear(systemYear);
	  	ballotDao.save(ballot);
	  	logDao.saveLog(log);
	  	return true;
	}
	/**
	 * 查询选民已经投过的人
	 * @param voterId
	 * @return
	 */
	public List<Log> getLogListByVoterId(int voterId) {
		int systemYear = systemDao.getVoteSystemConfig().getYear();
		List<Log> list = logDao.getLogListByVoterId(voterId, systemYear);
		return list;
	}
	@Resource(name="logDao")
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	@Resource(name="voterDao")
	public void setVoterDao(VoterDao voterDao) {
		this.voterDao = voterDao;
	}
	@Resource(name="voteSystemDao")
	public void setVoteSystemDao(VoteSystemDao voteSystemDao) {
		this.voteSystemDao = voteSystemDao;
	}
	@Resource(name="deptDao")
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	
	
	
}





















