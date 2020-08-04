package com.zhchong.blog.dao;

import com.zhchong.blog.domain.ContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @description 博客DAO
 * @projectname blog
 * @packagename com.zhchong.blog.dao
 * @typename ContentDao
 * @filename ContentDao.java
 * @author zhchong
 * @date 2020年5月17日 下午2:50:09
 */
@Mapper
public interface ContentDao {

	ContentDO get(Long cid);
	
	List<ContentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContentDO content);
	
	int update(ContentDO content);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
	
	/**
	 * 
	 * @description 查询年份，根据创建时间倒序排列
	 * @projectname blog
	 * @packagename com.zhchong.blog.dao
	 * @filename ContentDao.java
	 * @return List<Map<String,Integer>>
	 * @author zhchong
	 * @date 2020年5月17日 下午2:49:47
	 */
	List<Map<String, String>> findGmtCreateYears();
	
	/**
	 * 
	 * @description 根据年份查询当年各月份中博客数量
	 * @param gtmCreateYear 年份
	 * @projectname blog
	 * @packagename com.zhchong.blog.dao
	 * @filename ContentDao.java
	 * @return List<Map<String,Integer>>
	 * @author zhchong
	 * @date 2020年5月17日 下午3:18:15
	 
	 */
	List<Map<String, Integer>> findDiffMonthBlogCountByYear(String gtmCreateYear);
}
