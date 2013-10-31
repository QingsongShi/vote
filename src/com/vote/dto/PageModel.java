package com.vote.dto;

import java.util.List;

/**
 * ��װ��ҳ��Ϣ
 * @author qingsong
 *
 */
public class PageModel<E> {
	
	private List<E> list;  //�����
	
	private int totalRecords;  //�ܹ���¼��
	
	private int pageSize;  //ÿҳ��ʾ��¼��

	/**
	 * �����ҳ��
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
