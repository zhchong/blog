package com.zhchong.tags.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 博客标签
 * 
 * @author zhchong
 * @email boyzhangchong@163.com
 * @date 2020-07-25 21:34:25
 */
public class TagsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//标签名
	private String name;
	//标签类别（字典表配置）
	private String category;
	//是否删除 0-否 1-是
	private String delFlag;
	//创建人
	private Long createBy;
	//创建时间
	private Date createTime;
	//修改人
	private Long updateBy;
	//修改时间
	private Date updateTime;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：标签名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：标签名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：标签类别（字典表配置）
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 获取：标签类别（字典表配置）
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * 设置：是否删除 0-否 1-是
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除 0-否 1-是
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：修改人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
