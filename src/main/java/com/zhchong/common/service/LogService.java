package com.zhchong.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zhchong.common.domain.LogDO;
import com.zhchong.common.domain.PageDO;
import com.zhchong.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
