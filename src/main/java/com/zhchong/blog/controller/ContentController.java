package com.zhchong.blog.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhchong.blog.domain.ContentDO;
import com.zhchong.blog.service.ContentService;
import com.zhchong.blog.vo.ContentVO;
import com.zhchong.common.config.Constant;
import com.zhchong.common.constants.DictTypeConstant;
import com.zhchong.common.controller.BaseController;
import com.zhchong.common.domain.DictDO;
import com.zhchong.common.service.DictService;
import com.zhchong.common.utils.PageUtils;
import com.zhchong.common.utils.Query;
import com.zhchong.common.utils.R;
import com.zhchong.tags.domain.TagsDO;
import com.zhchong.tags.service.TagsService;

import cn.hutool.core.bean.BeanUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章内容
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-09 10:03:34
 */
@Controller
@RequestMapping("/blog/bContent")
public class ContentController extends BaseController {
	@Autowired
    ContentService bContentService;
	@Autowired
	private DictService dictService;
	@Autowired
	private TagsService tagsService;

	@GetMapping()
	@RequiresPermissions("blog:bContent:bContent")
	String bContent() {
		return "blog/bContent/bContent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:bContent:bContent")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentDOList = bContentService.list(query);
		List<ContentVO> bContentVOList = Lists.newArrayList();
		for(ContentDO contentDO : bContentDOList){
			ContentVO contentVO = new ContentVO();
			BeanUtil.copyProperties(contentDO, contentVO);
			String tags = contentDO.getTags();
			String[] tagsArray = tags.split(",");
			List<String> tagsName = Lists.newArrayList();
			for(int i=0; i<tagsArray.length; i++){
				tagsName.add(tagsService.get(Integer.valueOf(tagsArray[i])).getName());
			}
			contentVO.setTagsName(tagsName);
			// 分类专栏
			String categories = contentDO.getCategories();
			String[] categoriesArray = categories.split(",");
			List<String> categoriesName = Lists.newArrayList();
			for(int i=0; i<categoriesArray.length; i++){
				categoriesName.add(dictService.getName(DictTypeConstant.BLOG_TAGS_CATEGORIES, categoriesArray[i]));
			}
			contentVO.setCategoriesName(categoriesName);
			// 发布形式
			contentVO.setIssueFormName(dictService.getName(DictTypeConstant.BLOG_ISSUE_FORM, contentDO.getIssueForm()+""));
			// 文章类型
			contentVO.setTypeName(dictService.getName(DictTypeConstant.BLOG_TYPE, contentDO.getType()));
			bContentVOList.add(contentVO);
		}
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentVOList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("blog:bContent:add")
	String add(Model model) {
		// 文章标签
		List<DictDO> categories = dictService.listByType(DictTypeConstant.BLOG_TAGS_CATEGORIES);
		List<Map<String, Object>> tagsList = Lists.newArrayList();
		Map<String, Object> params = Maps.newHashMap();
		for(DictDO dict : categories){
			params.put("category", dict.getValue());
			List<TagsDO> tagsDOList = tagsService.list(params);
			Map<String, Object> map = Maps.newHashMap();
			map.put("category", dict.getName());
			map.put("tags", tagsDOList);
			tagsList.add(map);
		}
		model.addAttribute("tagsList", tagsList);
		// 分类专栏
		List<DictDO> column = dictService.listByType(DictTypeConstant.BLOG_CATEGORIES_COLUMN);
		model.addAttribute("column", column);
		// 发布形式
		List<DictDO> issueForm = dictService.listByType(DictTypeConstant.BLOG_ISSUE_FORM);
		model.addAttribute("issueForm", issueForm);
		// 发布形式
		List<DictDO> type = dictService.listByType(DictTypeConstant.BLOG_TYPE);
		model.addAttribute("type", type);
		return "blog/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("blog:bContent:edit")
	String edit(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		return "blog/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("blog:bContent:add")
	@PostMapping("/save")
	public R save(ContentDO bContent) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContent.getAllowComment() == null) {
			bContent.setAllowComment(0);
		}
		if (bContent.getAllowFeed() == null) {
			bContent.setAllowFeed(0);
		}
		if (bContent.getAllowReward()== null) {
			bContent.setAllowReward(0);
		}
		if (bContent.getAllowShare() == null) {
			bContent.setAllowShare(0);
		}
		if (bContent.getTopFlag() == null) {
			bContent.setTopFlag(0);
		}
		if(null==bContent.getType()) {
			bContent.setType("article");
		}
		bContent.setGtmCreate(new Date());
		bContent.setGtmModified(new Date());
		int count;
		if (bContent.getCid() == null || "".equals(bContent.getCid())) {
			count = bContentService.save(bContent);
		} else {
			count = bContentService.update(bContent);
		}
		if (count > 0) {
			return R.ok().put("cid", bContent.getCid());
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("blog:bContent:edit")
	@ResponseBody
	@RequestMapping("/update")
	public R update( ContentDO bContent) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContent.setGtmCreate(new Date());
		bContentService.update(bContent);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:bContent:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContentService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:bContent:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContentService.batchRemove(cids);
		return R.ok();
	}
}
