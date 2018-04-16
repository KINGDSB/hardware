package indi.dsb.hardware.matter.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.matter.entity.Matter;
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
    
}
