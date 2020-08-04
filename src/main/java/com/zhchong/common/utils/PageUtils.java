package com.zhchong.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @description 分页工具类
 * @projectname blog
 * @packagename com.zhchong.common.utils
 * @typename PageUtils
 * @filename PageUtils.java
 * @author zhchong
 * @date 2020年5月10日 下午8:10:36
 
 */
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;			//总条数
	private int currentPage;	//当前页数
	private int allPage;		//总页数
	private List<?> rows;		//分页数据

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
