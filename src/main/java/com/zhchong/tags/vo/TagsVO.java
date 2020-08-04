package com.zhchong.tags.vo;

import com.zhchong.tags.domain.TagsDO;

public class TagsVO extends TagsDO {

	/**
	 * @description 
	 * @author zhchong
	 * @date 2020年7月26日 上午12:08:05
	 */
	private static final long serialVersionUID = 3128374227357855149L;

	//标签类别
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
