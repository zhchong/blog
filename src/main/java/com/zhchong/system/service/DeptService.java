package com.zhchong.system.service;

import java.util.List;
import java.util.Map;

import com.zhchong.common.domain.Tree;
import com.zhchong.system.domain.DeptDO;

/**
 * 
 * @description 部门管理接口
 * @projectname blog
 * @packagename com.zhchong.system.service
 * @typename DeptService
 * @filename DeptService.java
 * @author zhchong
 * @date 2020年5月7日 下午10:39:04
 
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);

	List<Long> listChildrenIds(Long parentId);
}
