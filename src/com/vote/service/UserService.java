package com.vote.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.vote.dao.BallotDao;
import com.vote.dao.DeptDao;
import com.vote.dao.LogDao;
import com.vote.dao.VoteSystemDao;
import com.vote.dao.VoteeDao;
import com.vote.dao.VoterDao;
import com.vote.domain.Ballot;
import com.vote.domain.Dept;
import com.vote.domain.Log;
import com.vote.domain.VoteSystem;
import com.vote.domain.Votee;
import com.vote.domain.Voter;
import com.vote.dto.PageModel;
/**
 * ��װ�û���ҵ���߼�
 * @author qingsong
 * 
 */
@Component("userService")
public class UserService {
	private LogDao logDao;
	private VoterDao voterDao;
	private VoteSystemDao voteSystemDao;
	private DeptDao deptDao;
	private VoteeDao voteeDao;
	private BallotDao ballotDao; 
	/**
	 * ��֤�û���¼
	 * @param account
	 * @param password
	 * @return
	 */
	public Voter validateUserLogin(String account,String password) {
		Voter voter = voterDao.getVoterByAccount(account);
		if(voter!=null&&voter.getPassword().equals(password)) {
			return voter;
		}else {
			return null;
		}
	}
	/**
	 * �õ�ϵͳ��Ϣ
	 * @return
	 */
	public VoteSystem getVoteSystem() {
		
		return voteSystemDao.getVoteSystem();
	}
	
	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param deptId
	 * @return
	 */
	public PageModel<Votee> getPermitDeptNamePM(int offset,int pageSize,int deptId) {
		PageModel<Votee> voteePM = new PageModel<Votee>();
		int systemYear = voteSystemDao.getVoteSystem().getSystemYear();
		voteePM.setList(voteeDao.getVoteeList(offset, pageSize, systemYear, deptId));
		voteePM.setPageSize(pageSize);
		voteePM.setTotalRecords(voteeDao.countOfVoteeList(systemYear, deptId));
		return voteePM;
	}
	/**
	 * �����û�id����û���������Ϣ
	 * @param userId
	 * @return
	 */
	public Voter getVoterById(int voterId) {
		Voter voter = voterDao.getVoterById(voterId);
		return voter;
	}
	/**
	 * ����id��ò�����Ϣ
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		return deptDao.getDeptById(deptId);
	}
	/**
	 * ����ID��ñ�ͶƱ�˵���Ϣ
	 * @param voteeId
	 * @return
	 */
	public Votee getVoteeById(int voteeId) {
		return voteeDao.getVoteeById(voteeId);
	}
	/**
	 * ���ͶƱ
	 * @param ballot
	 * 
	 */
	public boolean addBallot(Ballot ballot,Log log) {
		int systemYear = voteSystemDao.getVoteSystem().getSystemYear();
		List<Log> logList = logDao.getLogList(systemYear, log.getVoter().getId());
		Iterator<Log> it = logList.iterator();
		while(it.hasNext()) {
			Log voterLog = it.next();
			if(voterLog.getVotee().getId()==log.getVotee().getId()) {
				return false;
			}
		}
	  	ballotDao.addBallot(ballot);
	  	logDao.addLog(log);
	  	return true;
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
	@Resource(name="voteeDao")
	public void setVoteeDao(VoteeDao voteeDao) {
		this.voteeDao = voteeDao;
	}
	@Resource(name="ballotDao")
	public void setBallotDao(BallotDao ballotDao) {
		this.ballotDao = ballotDao;
	}
	
	
	
}





















