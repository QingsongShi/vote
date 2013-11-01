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
	 * 验证管理员登陆
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
	 * 得到系统的信息
	 * @return
	 */
	public VoteSystem getVoteSystem() {
		return voteSystemDao.getVoteSystem();
	}
	
	/**
	 * 修改系统信息
	 * @param vSystem
	 */
	public void updateVoteSystem(VoteSystem voteSystem) {
		voteSystemDao.updateVoteSystem(voteSystem);
	}
	/**
	 * 获得部门信息的分页模型
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
	 * 根据部门id删除部门
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
	 * 根据部门id获得部门信息
	 * @param deptId
	 * @return
	 */
	public Dept getDeptById(int deptId) {
		Dept dept = deptDao.getDeptById(deptId);
		return dept;
	}
	/**
	 * 保存部门信息
	 * @param dept
	 */
	public void addDept(String deptName) {
		Dept dept = new Dept();
		dept.setYear(voteSystemDao.getVoteSystem().getSystemYear());
		dept.setDeptName(deptName);
		deptDao.addDept(dept);
	}
	/**
	 * 得到用户的信息列表（分页）
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
	 * 根据id删除用户
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
	 * 根据用户id获得用户信息
	 * @param userId
	 * @return
	 */
	public Voter getVoterById(int voterId) {
		Voter voter = voterDao.getVoterById(voterId);
		return voter;
	}
	/**
	 * 根据系统年份获得部门列表
	 * @param systemYear
	 * @return
	 */
	public List<Dept> getDeptListBySystemYear(int systemYear) {
		return deptDao.getDeptListBySystemYear(systemYear);
	}
	/**
	 * 修改用户
	 * @param saveOrUpdateUserDto
	 */
	public void updateUser(SaveOrUpdateUserDto saveOrUpdateUserDto) {
		Voter user = new Voter();
		user.setId(saveOrUpdateUserDto.getId());
		user.setUsername(saveOrUpdateUserDto.getUsername());
		user.setPassword(saveOrUpdateUserDto.getPassword());
		user.setAccount(saveOrUpdateUserDto.getAccount());
		// 设置所属的部门
		Dept dept = new Dept();
		dept.setId(saveOrUpdateUserDto.getDeptId());
		user.setDept(dept);
		// 设置有投票权限的部门
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
	 * 添加用户
	 * @param saveOrUpdateUserDto
	 */
	public boolean addUser(SaveOrUpdateUserDto saveOrUpdateUserDto) {
		Voter u = new Voter();
		u.setUsername(saveOrUpdateUserDto.getUsername());
	
		if(saveOrUpdateUserDto.getAccount()==null || "".equals(saveOrUpdateUserDto.getAccount().trim())) {
			// 自动生成账号
			u.setAccount(GenerateAccountOrPassword.generateAccount());
		}else {
			u.setAccount(saveOrUpdateUserDto.getAccount());
		}
		
		if(saveOrUpdateUserDto.getPassword()==null || "".equals(saveOrUpdateUserDto.getPassword())) {
			// 自动生成密码
			u.setPassword(GenerateAccountOrPassword.generatePassword());
		}else {
			u.setPassword(saveOrUpdateUserDto.getPassword());
		}
		// 第二步：判断账号是否重复
//		if(userDao.getUserByAccount(u.getAccount()) != null) {
//			return false;
//		}
	
		
		
//		// 第三步：设值，添加用户
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
	 * 用户信息导出到Excel
	 * @param path
	 * @param userList
	 */
	public void userInfoToExcel(String path,String fileName) {
		int systemYear = voteSystemDao.getVoteSystem().getSystemYear();
		List<Voter> voterList = voterDao.getVoterListBySystemYear(systemYear);
		// 产生工作簿对象
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 产生工作表对象
		HSSFSheet sheet = workBook.createSheet(); 
		// 产生一行
		HSSFRow row = sheet.createRow(0);
		// 设置表头
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("年");
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell = row.createCell(2);
		cell.setCellValue("账号");
		cell = row.createCell(3);
		cell.setCellValue("密码");
		cell = row.createCell(4);
		cell.setCellValue("部门");
		
		int rowNum = 1;
		Iterator<Voter> it = voterList.iterator();
		while(it.hasNext()) {
			Voter u = it.next();
			// 创建一行
			row = sheet.createRow(rowNum);
			// 填值
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
			// 行数加一
			rowNum++;
		}
			// 将生成的Excel写到硬盘上
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
