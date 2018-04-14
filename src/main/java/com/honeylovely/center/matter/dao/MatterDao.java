package com.honeylovely.center.matter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.matter.entity.Matter;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface MatterDao extends AbstractDAO<Matter,Long> {
    Page<Matter> listPage(PageRequest pageRequest, Example example);

    /**
     * @Title statisticsData 
     * @Description 统计首页数据
     * @param userId
     * @return
     */
    List<Map> statisticsData(Long userId);
	
	/**
	 * @Title findList 
	 * @Description 公用列表查询方法
	 * @param projectId
	 * @param processStatus
	 * @param type
	 * @param grade
	 * @param createdDate
	 * @param feedbackUid
	 * @param keyword
	 * @param userId
	 * @param sort
	 * @param startNo
	 * @param pageSize
	 * @return
	 */
	List<Matter> findList(
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
		Integer pageSize
	);

	/**
	 * @Title findListCount 
	 * @Description 公用列表条数查询方法
	 * @param projectId
	 * @param processStatus
	 * @param type
	 * @param grade
	 * @param createdDate
	 * @param feedbackUid
	 * @param keyword
	 * @param userId
	 * @return
	 */
	int findListCount(
		Long projectId,
		Integer processStatus,
		Integer type,
		Integer grade,
		Date createdDate,
		Long feedbackUid,
		String keyword,
		Long userId
	);
	
    /**
     * @Title getGrade4Matters 
     * @Description 获取需要推送的致命问题
     * @param matter
     * @return
     */
    List<Matter> getGrade4Matters();
    
    /**
     * @Title getGrade4Matters 
     * @Description 非致命问题每日统计提示
     * @param matter
     * @return
     */
    List<Map> getMatterStatistics();
}
