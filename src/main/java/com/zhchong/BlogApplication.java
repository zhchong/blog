package com.zhchong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @description 博客系统启动类
 * @projectname blog
 * @packagename com.zhchong
 * @typename BlogApplication
 * @filename BlogApplication.java
 * @author zhchong
 * @date 2020年5月7日 下午10:00:48
 */
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.zhchong.*.dao")
@SpringBootApplication
@EnableCaching
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    博客启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
    }
}
