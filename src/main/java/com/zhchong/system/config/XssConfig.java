package com.zhchong.system.config;

import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhchong.system.filter.XssFilter;
import com.google.common.collect.Maps;

/**
 * 
 * @description 防止xss攻击
 * @projectname blog
 * @packagename com.zhchong.system.config
 * @typename XssConfig
 * @filename XssConfig.java
 * @author zhchong
 * @date 2020年5月7日 下午10:21:09
 
 */
@Configuration
public class XssConfig {

	/**
	 * xss过滤拦截器
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(false);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}
}
