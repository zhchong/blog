package com.zhchong.tags.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zhchong.common.constants.DictTypeConstant;
import com.zhchong.common.domain.DictDO;
import com.zhchong.common.service.DictService;
import com.zhchong.common.utils.PageUtils;
import com.zhchong.common.utils.Query;
import com.zhchong.common.utils.R;
import com.zhchong.tags.domain.TagsDO;
import com.zhchong.tags.service.TagsService;
import com.zhchong.tags.vo.TagsVO;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

/**
 * 博客标签
 * 
 * @author zhchong
 * @email boyzhangchong@163.com
 * @date 2020-07-25 21:34:25
 */
 
@Controller
@RequestMapping("/tags/tags")
public class TagsController {
	@Autowired
	private TagsService tagsService;
	@Autowired
	private DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("tags:tags:tags")
	String Tags(Model model){
		List<DictDO> categories = dictService.listByType(DictTypeConstant.BLOG_TAGS_CATEGORIES);
		model.addAttribute("categories", categories);
		return "tags/tags/tags";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("tags:tags:tags")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TagsDO> tagsList = tagsService.list(query);
		int total = tagsService.count(query);
		List<TagsVO> tagsVOList = Lists.newArrayList();
		if(CollUtil.isNotEmpty(tagsList)){
			for(TagsDO tagsDO : tagsList){
				TagsVO tagsVO = new TagsVO();
				BeanUtil.copyProperties(tagsDO, tagsVO);
				tagsVO.setCategoryName(dictService.getName(DictTypeConstant.BLOG_TAGS_CATEGORIES, tagsDO.getCategory()));
				tagsVOList.add(tagsVO);
			}
		}
		PageUtils pageUtils = new PageUtils(tagsVOList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("tags:tags:add")
	String add(Model model){
		List<DictDO> categories = dictService.listByType(DictTypeConstant.BLOG_TAGS_CATEGORIES);
		model.addAttribute("categories", categories);
		return "tags/tags/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("tags:tags:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TagsDO tags = tagsService.get(id);
		model.addAttribute("tags", tags);
		List<DictDO> categories = dictService.listByType(DictTypeConstant.BLOG_TAGS_CATEGORIES);
		model.addAttribute("categories", categories);
	    return "tags/tags/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("tags:tags:add")
	public R save( TagsDO tags){
		if(tagsService.save(tags)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tags:tags:edit")
	public R update( TagsDO tags){
		tagsService.update(tags);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("tags:tags:remove")
	public R remove( Integer id){
		if(tagsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("tags:tags:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tagsService.batchRemove(ids);
		return R.ok();
	}
	
}
