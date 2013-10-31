package com.vote.dto;

import java.util.List;

/**
 * 封装分页信息
 * @author qingsong
 *
 */
public class PageModel<E> {
	
	private List<E> list;  //结果集
	
	private int totalRecords;  //总共记录数
	
	private int pageSize;  //每页显示记录数

	/**
	 * 获得总页数
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords+pageSize-1)/pageSize;
	}
	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
