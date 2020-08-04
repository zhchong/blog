package com.zhchong.blog.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhchong.blog.dao.ContentDao;
import com.zhchong.blog.domain.ContentDO;
import com.zhchong.blog.service.ContentService;
import com.zhchong.common.enums.DelFlagEnum;


/**
 * 
 * @description 博客service
 * @projectname blog
 * @packagename com.zhchong.blog.service.impl
 * @typename ContentServiceImpl
 * @filename ContentServiceImpl.java
 * @author zhchong
 * @date 2020年5月17日 下午2:52:10
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentDao bContentMapper;
	
	@Override
	public ContentDO get(Long cid){
		return bContentMapper.get(cid);
	}
	
	@Override
	public List<ContentDO> list(Map<String, Object> map){
		return bContentMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bContentMapper.count(map);
	}
	
	@Override
	public int save(ContentDO bContent){
		bContent.setDelFlag(Integer.valueOf(DelFlagEnum.NO.getValue()));
		return bContentMapper.save(bContent);
	}
	
	@Override
	public int update(ContentDO bContent){
		return bContentMapper.update(bContent);
	}
	
	@Override
	public int remove(Long cid){
		return bContentMapper.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return bContentMapper.batchRemove(cids);
	}

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
	@Override
	public List<Map<String, Object>> findArchiveInfo() {
		List<Map<String, Object>> result = Lists.newArrayList();
		List<Map<String, String>> years = bContentMapper.findGmtCreateYears();
		if(CollectionUtils.isNotEmpty(years)){
			for(Map<String, String> map : years){
				Map<String, Object> temp = Maps.newHashMap();
				temp.put("year", map.get("gtmCreateYear"));
				temp.put("monthCountList", bContentMapper.findDiffMonthBlogCountByYear(map.get("gtmCreateYear")));
				result.add(temp);
			}
		}
		return result;
	}
	
}
