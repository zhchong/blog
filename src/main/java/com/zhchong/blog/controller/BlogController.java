package com.zhchong.blog.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhchong.blog.domain.ContentDO;
import com.zhchong.blog.service.ContentService;
import com.zhchong.common.annotation.Log;
import com.zhchong.common.utils.DateUtils;
import com.zhchong.common.utils.MD5Utils;
import com.zhchong.common.utils.PageUtils;
import com.zhchong.common.utils.Query;
import com.zhchong.common.utils.R;
import com.zhchong.common.utils.RandomValidateCodeUtil;
import com.zhchong.common.utils.ShiroUtils;
import com.zhchong.common.utils.StringUtils;
import com.zhchong.system.domain.UserDO;
import com.zhchong.system.service.UserService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @description 博客首页controller
 * @projectname blog
 * @packagename com.zhchong.blog.controller
 * @typename BlogController
 * @filename BlogController.java
 * @author zhchong
 * @date 2020年5月10日 下午8:05:13
 
 */
@RequestMapping("/blog")
@Controller
public class BlogController {
	
	@Autowired
    ContentService bContentService;
	@Autowired
	UserService userService;

	/**
	 * 
	 * @description 进入微博首页
	 * @param model
	 * @projectname blog
	 * @packagename com.zhchong.blog.controller
	 * @filename BlogController.java
	 * @return String
	 * @author zhchong
	 * @date 2020年5月14日 下午11:10:01
	 */
	@GetMapping()
	String blog(Model model) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("orderBy", "gtm_modified");
		params.put("order", "desc");
		params.put("offset", 0);
		params.put("limit", 5);// 只显示5条
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		if(CollectionUtils.isNotEmpty(bContentList)){
			for(int i=1; i<=bContentList.size(); i++){
				model.addAttribute("bContent"+i, bContentList.get(i-1));
				model.addAttribute("href"+i, "/blog/open/post/"+bContentList.get(i-1).getCid());
			}
		}
		UserDO user = ShiroUtils.getUser();
		if(null != user){
			System.out.println("已经登录了");
			model.addAttribute("isLogin", 1);
			if(StringUtils.isNotBlank(user.getName())){
				model.addAttribute("name", user.getName());
			}else if(StringUtils.isNotBlank(user.getUsername())){
				model.addAttribute("name", user.getUsername());
			}
		}else{
			System.out.println("没有登录");
			model.addAttribute("isLogin", 0);
		}
		// 将来用户数据需要存入LocalStorage
		return "blog/index/main";
	}

	/**
	 * 
	 * @description 博客首页博客分页
	 * @param params 请求参数
	 * @projectname blog
	 * @packagename com.zhchong.blog.controller
	 * @filename BlogController.java
	 * @return PageUtils
	 * @author zhchong
	 * @date 2020年5月10日 下午8:02:20
	 */
	@Log("博客首页分页展示博客数据")
	@ResponseBody
	@GetMapping("/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		// 分页每页数量
		int limit = Integer.valueOf(String.valueOf(params.get("limit")));
		// 当前页数，从0开始
		int currentPage = Integer.valueOf(String.valueOf(params.get("currentPage")));
		// 排序
		int order = Integer.valueOf(String.valueOf(params.get("order")));
		if(order == 1){
			params.put("sort", "gtm_modified");
			params.put("order", "desc");
		}
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		pageUtils.setAllPage(total%limit==0?total/limit:total/limit+1);
		pageUtils.setCurrentPage(currentPage);
		return pageUtils;
	}

	@GetMapping("/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
		return "blog/index/post";
	}
	@GetMapping("/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}
	
	/**
	 * 
	 * @description 博客首页获取归档信息
	 * @projectname blog
	 * @packagename com.zhchong.blog.controller
	 * @filename BlogController.java
	 * @return R
	 * @author zhchong
	 * @date 2020年5月17日 下午3:32:03
	 */
	@Log("博客首页获取归档信息")
	@ResponseBody
	@GetMapping("/open/archiveInfo")
	public R findArchiveInfo() {
		return R.ok().put("archiveInfo", bContentService.findArchiveInfo());
	}
	
    /**
     * 博客首页访问我要登录
     * @return
     */
    @GetMapping("/login")
    String login() {
    	return "blog/login";
    }
    
    /**
     * 博客首页访问我要注册
     * @return
     */
    @GetMapping("/register")
    String register() {
    	return "blog/register";
    }
    
    /**
     * 
     * @description 博客首页用户登录
     * @param 
     * @projectname blog
     * @packagename com.zhchong.blog.controller
     * @filename BlogController.java
     * @return R
     * @author zhchong
     * @date 2020年7月12日 下午8:42:28
     */
    @Log("博客首页用户登录")
    @PostMapping("/login")
    @ResponseBody
    public R ajaxLogin(String username, String password,String verify,HttpServletRequest request) {
        try {
            //从session中获取随机数
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
            if (StringUtils.isBlank(verify)) {
                return R.error("请输入验证码");
            }
            if (!random.equals(verify)) {
                return R.error("请输入正确的验证码");
            }
        } catch (Exception e) {
            return R.error("验证码校验失败");
        }
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }
    
    @Log("博客首页用户注册")
    @PostMapping("/register")
    @ResponseBody
    public R register(UserDO user) {
    	Map<String, Object> map = Maps.newHashMap();
    	map.put("username", user.getUsername());
    	if(userService.exit(map)){
    		return R.error("用户名已存在");
    	}
    	user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
    	// 默认普通用户
    	List<Long> roleIds = Lists.newArrayList();
    	roleIds.add(2L);
    	user.setRoleIds(roleIds);
    	user.setStatus(1);
    	user.setGmtCreate(new Date());
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
    }
}
