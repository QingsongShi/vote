package com.vote.action.admin;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.vote.domain.Dept;
import com.vote.domain.Voter;
import com.vote.dto.PageModel;
import com.vote.dto.Pager;
import com.vote.dto.SaveOrUpdateUserDto;
import com.vote.service.AdminService;

@Component("adminUserAction")
@Scope(value="prototype")
public class UserAction extends ActionSupport implements ModelDriven<SaveOrUpdateUserDto>{

	private PageModel userPM;
	private AdminService adminService;
	private Pager pager;
	private int systemYear;
	private int userId;
	private Voter user;
	private List<Dept> deptList;
	SaveOrUpdateUserDto saveOrUpdateUserDto = new SaveOrUpdateUserDto();
	private final String FILE_NAME = "用户信息.xls"; 
	/**
	 * 得到用户信息列表（分页）
	 * @return
	 */
	public String getUserList() {
		userPM = adminService.getUserPMByYear(this.getPager().getOffset(), this.getPageSize());
		systemYear = adminService.getVoteSystemConfig().getYear();
		return SUCCESS; 
	}
	/**
	 * 删除用户
	 * @return
	 */
	public String deleteUserById() {
		boolean result = adminService.deleteUserById(userId);
		if(result) {
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}
	/**
	 * 修改用户的输入页面
	 * @return
	 */
	public String updateUserInput() {
		user = adminService.loadUser(userId);
		deptList = adminService.getAllDeptBySystemtYear(user.getYear());
		return SUCCESS;
	}
	/**
	 * 添加用户输入页面
	 * @return
	 */
	public String addUserInput() {
		systemYear = adminService.getVoteSystemConfig().getYear();
		deptList = adminService.getAllDeptBySystemtYear(systemYear);
		return SUCCESS;
	}
	/**
	 * 修改用户
	 * @return
	 */
	public String updateUser() {
		adminService.updateUser(saveOrUpdateUserDto);
		return SUCCESS;
	}
	/**
	 * 添加用户
	 * @return
	 */
	public String addUser() {
		if(adminService.addUser(saveOrUpdateUserDto)) {
			return SUCCESS;
		}else {
			return ERROR;
		}
	}
	/**
	 * 将用户信息导出到Excel中	
	 * @return
	 */
	public String exportUserInfoToExcel() {
		ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String path = sc.getRealPath("/export");
		adminService.userInfoToExcel(path,FILE_NAME);
		return SUCCESS;
	}
	
	public InputStream getInputStream() throws Exception {  
    	return ServletActionContext.getServletContext().getResourceAsStream("export/"+FILE_NAME) ;  
    }  
	
	public String getFileName() {
		String fileName = null;
		try {
			fileName = new String(FILE_NAME.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
	public Voter getUser() {
		return user;
	}
	public void setUser(Voter user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public PageModel getUserPM() {
		return userPM;
	}
	@Resource(name="adminService")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
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
	public int getSystemYear() {
		return systemYear;
	}
	public void setSystemYear(int systemYear) {
		this.systemYear = systemYear;
	}
	@Override
	public SaveOrUpdateUserDto getModel() {
		return saveOrUpdateUserDto;
	}
	public SaveOrUpdateUserDto getSaveOrUpdateUserDto() {
		return saveOrUpdateUserDto;
	}
	public void setSaveOrUpdateUserDto(SaveOrUpdateUserDto saveOrUpdateUserDto) {
		this.saveOrUpdateUserDto = saveOrUpdateUserDto;
	}
	
}
