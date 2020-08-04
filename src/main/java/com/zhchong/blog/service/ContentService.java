package com.zhchong.blog.service;

import com.zhchong.blog.domain.ContentDO;

import java.util.List;
import java.util.Map;

/**
 * 文章内容
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
public interface ContentService {
	
	ContentDO get(Long cid);
	
	List<ContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContentDO bContent);
	
	int update(ContentDO bContent);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
	
	/**
	 * 
	 * @description 查询归档信息
	 * @projectname blog
	 * @packagename com.zhchong.blog.dao
	 * @filename ContentDao.java
	 * @return List<Map<String,Object>>
	 * @author zhchong
	 * @date 2020年5月17日 下午2:49:47
	 */
	List<Map<String, Object>> findArchiveInfo();
}
