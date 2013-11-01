package com.vote.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.vote.dao.AdminDao;
import com.vote.dao.DeptDao;
import com.vote.dao.VoteSystemDao;
import com.vote.dao.VoterDao;
import com.vote.domain.Admin;
import com.vote.domain.Dept;
import com.vote.domain.Voter;
import com.vote.domain.VoteSystem;
import com.vote.dto.PageModel;
import com.vote.dto.SaveOrUpdateUserDto;
import com.vote.util.GenerateAccountOrPassword;

@Component("adminService")
public class AdminService {

	private AdminDao adminDao;
	private VoteSystemDao voteSystemDao;
	private DeptDao deptDao;
	private VoterDao voterDao;
	
	/**
	 * ��֤����Ա��½
	 * @param account
	 * @param password
	 * @return
	 */
	public Admin validateAdminLogin(String account,String password) {
		Admin admin = adminDao.getAdminByAccount(account);
		if(admin!=null && admin.getPassword().equals(password)) {
			return admin;
		}else {
			return null;
		}
	}
	
	/**
	 * �õ�ϵͳ����Ϣ
	 * @return
	 */
	public VoteSystem getVoteSystem() {
		return voteSystemDao.getVoteSystem();
	}
	
	/**
	 * �޸�ϵͳ��Ϣ
	 * @param vSystem
	 */
	public void updateVoteSystem(VoteSystem voteSystem) {
		voteSystemDao.updateVoteSystem(voteSystem);
	}
	/**
	 * ��ò�����Ϣ�ķ�ҳģ��
	 * @param offset
	 * @param pageSize
	 * @return 
	 */
	public PageModel<Dept> getDeptList(int offset,int pageSize) {
		PageModel<Dept> deptPM = new PageModel<Dept>();
		int systemYear = this.getVoteSystem().getSystemYear();
		List<Dept> deptList = deptDao.getDeptListBySystemYear(offset, pageSize, systemYear);
		deptPM.setList(deptList);
		deptPM.setPageSize(pageSize);
		deptPM.setTotalRecords(deptDao.countOfDeptByYear(systemYear));
		return deptPM;
	}
	/**
	 * ���ݲ���idɾ������
	 * @param deptId
	 * @return
	 */
	public boolean deleteDeptById(int deptId) {
		try {
			deptDao.deleteDeptById(deptId);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * ���ݲ���id��ò�����Ϣ
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		Dept dept = deptDao.getDeptById(deptId);
		return dept;
	}
	/**
	 * ���沿����Ϣ
	 * @param dept
	 */
	public void addDept(String deptName) {
		Dept dept = new Dept();
		dept.setYear(voteSystemDao.getVoteSystem().getSystemYear());
		dept.setDeptName(deptName);
		deptDao.addDept(dept);
	}
	/**
	 * �õ��û�����Ϣ�б���ҳ��
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public PageModel<Voter> getUserPMByYear(int offset,int pageSize) {
		PageModel<Voter> voterPM = new PageModel<Voter>();
		int systemYear = voteSystemDao.getVoteSystem().getSystemYear();
		voterPM.setList(voterDao.getVoterListBySystemYear(offset, pageSize, systemYear));
		voterPM.setPageSize(pageSize);
		voterPM.setTotalRecords(voterDao.countOfVoterListBySystemYear(systemYear));
		return voterPM;
	}
	/**
	 * ����idɾ���û�
	 * @param userId
	 * @return
	 */
	public boolean deleteUserById(int voterId) {
		try {
			voterDao.deleteVoterById(voterId);
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * �����û�id����û���Ϣ
	 * @param userId
	 * @return
	 */
	public Voter getVoterById(int voterId) {
		Voter voter = voterDao.getVoterById(voterId);
		return voter;
	}
	/**
	 * ����ϵͳ��ݻ�ò����б�
	 * @param systemYear
	 * @return
	 */
	public List<Dept> getDeptListBySystemYear(int systemYear) {
		return deptDao.getDeptListBySystemYear(systemYear);
	}
	/**
	 * �޸��û�
	 * @param saveOrUpdateUserDto
	 */
	public void updateUser(SaveOrUpdateUserDto saveOrUpdateUserDto) {
		Voter user = new Voter();
		user.setId(saveOrUpdateUserDto.getId());
		user.setUsername(saveOrUpdateUserDto.getUsername());
		user.setPassword(saveOrUpdateUserDto.getPassword());
		user.setAccount(saveOrUpdateUserDto.getAccount());
		// ���������Ĳ���
		Dept dept = new Dept();
		dept.setId(saveOrUpdateUserDto.getDeptId());
		user.setDept(dept);
		// ������ͶƱȨ�޵Ĳ���
		int[] permitDeptIds = saveOrUpdateUserDto.getPermitDeptIds();
		Set<Dept> permitDeptSet = new HashSet<Dept>();
		for(int i=0; i<permitDeptIds.length; i++) {
//			Dept permitDept = new Dept();
//			permitDept.setId(permitDeptIds[i]);
//			permitDeptSet.add(permitDept);
			permitDeptSet.add(deptDao.getDeptById(permitDeptIds[i]));
		}
//		user.setDeptSet(permitDeptSet);
//		userDao.updateUser(user);
//		
	}
	/**
	 * ����û�
	 * @param saveOrUpdateUserDto
	 */
	public boolean addUser(SaveOrUpdateUserDto saveOrUpdateUserDto) {
		Voter u = new Voter();
		u.setUsername(saveOrUpdateUserDto.getUsername());
	
		if(saveOrUpdateUserDto.getAccount()==null || "".equals(saveOrUpdateUserDto.getAccount().trim())) {
			// �Զ������˺�
			u.setAccount(GenerateAccountOrPassword.generateAccount());
		}else {
			u.setAccount(saveOrUpdateUserDto.getAccount());
		}
		
		if(saveOrUpdateUserDto.getPassword()==null || "".equals(saveOrUpdateUserDto.getPassword())) {
			// �Զ���������
			u.setPassword(GenerateAccountOrPassword.generatePassword());
		}else {
			u.setPassword(saveOrUpdateUserDto.getPassword());
		}
		// �ڶ������ж��˺��Ƿ��ظ�
//		if(userDao.getUserByAccount(u.getAccount()) != null) {
//			return false;
//		}
	
		
		
//		// ����������ֵ������û�
//		u.setYear(systemDao.getVoteSystemConfig().getYear());
//		Dept dept = new Dept();
//		dept.setId(saveOrUpdateUserDto.getDeptId());
//		u.setDept(dept);
//		int[] permitDeptIds = saveOrUpdateUserDto.getPermitDeptIds();
//		Set<Dept> permitDeptSet = new HashSet<Dept>();
//		for(int i=0; i<permitDeptIds.length; i++) {
//			permitDeptSet.add(deptDao.getDeptById(permitDeptIds[i]));
//		}
//		u.setDeptSet(permitDeptSet);
//		
//		userDao.addUser(u);
		return true;
	}
	/**
	 * �û���Ϣ������Excel
	 * @param path
	 * @param userList
	 */
	public void userInfoToExcel(String path,String fileName) {
		int systemYear = voteSystemDao.getVoteSystem().getSystemYear();
		List<Voter> voterList = voterDao.getVoterListBySystemYear(systemYear);
		// ��������������
		HSSFWorkbook workBook = new HSSFWorkbook();
		// �������������
		HSSFSheet sheet = workBook.createSheet(); 
		// ����һ��
		HSSFRow row = sheet.createRow(0);
		// ���ñ�ͷ
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("��");
		cell = row.createCell(1);
		cell.setCellValue("����");
		cell = row.createCell(2);
		cell.setCellValue("�˺�");
		cell = row.createCell(3);
		cell.setCellValue("����");
		cell = row.createCell(4);
		cell.setCellValue("����");
		
		int rowNum = 1;
		Iterator<Voter> it = voterList.iterator();
		while(it.hasNext()) {
			Voter u = it.next();
			// ����һ��
			row = sheet.createRow(rowNum);
			// ��ֵ
			cell = row.createCell(0);
			cell.setCellValue(u.getYear());
			cell = row.createCell(1);
			cell.setCellValue(u.getUsername());
			cell = row.createCell(2);
			cell.setCellValue(u.getAccount());
			cell = row.createCell(3);
			cell.setCellValue(u.getPassword());
			cell = row.createCell(4);
			cell.setCellValue(u.getDept().getDeptName());
			// ������һ
			rowNum++;
		}
			// �����ɵ�Excelд��Ӳ����
			try {
				FileOutputStream fos = new FileOutputStream(path+"\\"+fileName);
				workBook.write(fos);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	
	
	
	
	
	@Resource(name="adminDao")
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	@Resource(name="deptDao")
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	@Resource(name="voteSystemDao")
	public void setVoteSystemDao(VoteSystemDao voteSystemDao) {
		this.voteSystemDao = voteSystemDao;
	}
	@Resource(name="voterDao")
	public void setVoterDao(VoterDao voterDao) {
		this.voterDao = voterDao;
	}
	
}
