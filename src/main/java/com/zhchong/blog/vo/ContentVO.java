package com.zhchong.blog.vo;

import java.util.List;

import com.zhchong.blog.domain.ContentDO;

public class ContentVO extends ContentDO {

	/**
	 * @description 
	 * @author zhchong
	 * @date 2020年7月26日 下午3:07:54
	 */
	private static final long serialVersionUID = 1319211131793359480L;

	//文章标签
	private List<String> tagsName;
	//分类专栏
	private List<String> categoriesName;
	//发布形式
	private String issueFormName;
	//类型
	private String typeName;
	public List<String> getTagsName() {
		return tagsName;
	}
	public void setTagsName(List<String> tagsName) {
		this.tagsName = tagsName;
	}
	public List<String> getCategoriesName() {
		return categoriesName;
	}
	public void setCategoriesName(List<String> categoriesName) {
		this.categoriesName = categoriesName;
	}
	public String getIssueFormName() {
		return issueFormName;
	}
	public void setIssueFormName(String issueFormName) {
		this.issueFormName = issueFormName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
