package com.honeylovely.center.matter.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.matter.entity.Matter;

import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface MatterService extends AbstractService<Matter,Long> {

	/**
	 * @Title listPage 
	 * @Description 分页查询方法
	 * @param pageRequest
	 * @param example
	 * @return
	 */
    Page<Matter> listPage(PageRequest pageRequest, Example example);

    /**
     * @Title statisticsData 
     * @Description 统计首页数据
     * @param userId
     * @return
     */
    Map statisticsData(Long userId);

	/**
	 * @Title findList 
	 * @Description 通用列表查询
	 * @param matter
	 * @param pageRequest
	 * @return
	 */
    Page<Matter> findList(Matter matter, PageRequest pageRequest);

    /**
     * @Title updateBySystem 
     * @Description 系统更新数据 为了解决定时任务中没有用户登录 因此获取修改人 报错
     * @param matter
     * @return
     */
    int updateBySystem(Matter matter);
    
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
