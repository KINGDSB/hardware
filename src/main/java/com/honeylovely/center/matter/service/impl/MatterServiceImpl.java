package com.honeylovely.center.matter.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.common.utils.SQLUtils;
import com.honeylovely.center.matter.dao.MatterDao;
import com.honeylovely.center.matter.entity.Matter;
import com.honeylovely.center.matter.service.MatterService;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("matterService")
public class MatterServiceImpl extends AbstractServiceImpl<Matter,Long> implements MatterService {

    private static Logger logger = LoggerFactory.getLogger(MatterServiceImpl.class);

    @Autowired
    private MatterDao matterDao;

    @Override
    public AbstractDAO<Matter, Long> getAbstractDAO() {
        return matterDao;
    }

    @Override
    public Page<Matter> listPage(PageRequest pageRequest, Example example) {
        return matterDao.listPage(pageRequest,example);
    }

	@Override
	public Map statisticsData(Long userId) {
		List<Map> list = matterDao.statisticsData(userId);
		Map data = new HashMap();
		for (Map map : list) {
			data.put(map.get("process_status")+"", map.get("number"));
		}
		return data;
	}

	@Override
	public Page<Matter> findList(Matter matter, PageRequest pageRequest) {
		List<Matter> list = matterDao.findList(
			matter.getProjectId(), 
			matter.getProcessStatus(), 
			matter.getType(), 
			matter.getGrade(), 
			matter.getCreatedDate(), 
			matter.getFeedbackUid(), 
			matter.getKeyWord(), 
			matter.getQueryUserId(),
			SQLUtils.getSortString(pageRequest.getSort()),
			pageRequest.getPageNumber(), 
			pageRequest.getPageSize()
		);
		int total = matterDao.findListCount(
			matter.getProjectId(), 
			matter.getProcessStatus(), 
			matter.getType(), 
			matter.getGrade(), 
			matter.getCreatedDate(), 
			matter.getFeedbackUid(), 
			matter.getKeyWord(), 
			matter.getQueryUserId());

		if (pageRequest.getPageNumber() > total) {
			return new Page<Matter>(0, total, total, Collections.<Matter>emptyList());
		}
		return new Page<Matter>(0, total, total, list);
	}

    @Override
    public int updateBySystem(Matter matter) {
        matter.setUpdatedDate(new Date());
        matter.setUpdatedBy("0");
        return getAbstractDAO().updateByPrimaryKey(matter);
    }

    @Override
    public List<Matter> getGrade4Matters() {
        return matterDao.getGrade4Matters();
    }

    @Override
    public List<Map> getMatterStatistics() {
        return matterDao.getMatterStatistics();
    }

}
