package com.honeylovely.center.matter.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.abstracts.AbstractDAOImpl;
import com.honeylovely.center.matter.dao.MatterDao;
import com.honeylovely.center.matter.entity.Matter;
import com.honeylovely.center.matter.mapper.MatterMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;


/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class MatterDaoImpl extends AbstractDAOImpl<Matter,Long> implements MatterDao {

    @Autowired
    private MatterMapper matterMapper;

    @Override
    public Mapper<Matter> getMapper() {
        return matterMapper;
    }

    @Override
    public Page<Matter> listPage(PageRequest pageRequest, Example example) {
        PageHelper.startPage(pageRequest.getPageNumber() + 1, pageRequest.getPageSize());
        PageHelper.orderBy("id desc");
        List<Matter> list = matterMapper.selectByExample(example);
        int count = matterMapper.selectCountByExample(example);
        Page<Matter> pageList = new Page<Matter>(0, count, count, list);
        return pageList;
    }

	@Override
	public List<Map> statisticsData(Long userId) {
		return matterMapper.statisticsData(userId);
	}

	@Override
	public List<Matter> findList(		
			Long projectId,
			Integer processStatus,
			Integer type,
			Integer grade,
			Date createdDate,
			Long feedbackUid,
			String keyword,
			Long userId,
			String sort,
			Integer startNo,
			Integer pageSize) {
		return matterMapper.findList(projectId, processStatus, type, grade, createdDate, feedbackUid, keyword, userId, sort, startNo, pageSize);
	}

	@Override
	public int findListCount(Long projectId, Integer processStatus, Integer type, Integer grade,
			Date createdDate, Long feedbackUid, String keyword, Long userId) {
		return matterMapper.findListCount(projectId, processStatus, type, grade, createdDate, feedbackUid, keyword, userId);
	}

    @Override
    public List<Matter> getGrade4Matters() {
        return matterMapper.getGrade4Matters();
    }

    @Override
    public List<Map> getMatterStatistics() {
        return matterMapper.getMatterStatistics();
    }
	
}
