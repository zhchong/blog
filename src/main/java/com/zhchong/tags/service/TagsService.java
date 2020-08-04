package com.zhchong.tags.service;

import com.zhchong.tags.domain.TagsDO;

import java.util.List;
import java.util.Map;

/**
 * 博客标签
 * 
 * @author zhchong
 * @email boyzhangchong@163.com
 * @date 2020-07-25 21:34:25
 */
public interface TagsService {
	
	TagsDO get(Integer id);
	
	List<TagsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TagsDO tags);
	
	int update(TagsDO tags);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
