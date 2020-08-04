package com.zhchong.system.service;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;

import com.zhchong.system.domain.UserDO;
import com.zhchong.system.domain.UserOnline;

public interface SessionService {
	List<UserOnline> list();

	List<UserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
