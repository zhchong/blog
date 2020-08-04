package com.zhchong.tags.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.zhchong.common.enums.DelFlagEnum;
import com.zhchong.common.utils.DateUtils;
import com.zhchong.common.utils.ShiroUtils;
import com.zhchong.tags.dao.TagsDao;
import com.zhchong.tags.domain.TagsDO;
import com.zhchong.tags.service.TagsService;



@Service
public class TagsServiceImpl implements TagsService {
	@Autowired
	private TagsDao tagsDao;
	
	@Override
	public TagsDO get(Integer id){
		return tagsDao.get(id);
	}
	
	@Override
	public List<TagsDO> list(Map<String, Object> map){
		return tagsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tagsDao.count(map);
	}
	
	@Override
	public int save(TagsDO tags){
		tags.setDelFlag(DelFlagEnum.NO.getValue());
		tags.setCreateBy(ShiroUtils.getUserId());
		tags.setCreateTime(DateUtils.getDateObj());
		return tagsDao.save(tags);
	}
	
	@Override
	public int update(TagsDO tags){
		tags.setUpdateBy(ShiroUtils.getUserId());
		tags.setUpdateTime(DateUtils.getDateObj());
		return tagsDao.update(tags);
	}
	
	@Override
	public int remove(Integer id){
		return tagsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return tagsDao.batchRemove(ids);
	}
	
}
