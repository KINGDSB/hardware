package com.honeylovely.center.matter.service;

import java.util.List;

import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.matter.entity.Reply;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface ReplyService extends AbstractService<Reply, Long> {
    
    /**
     * @Title findList 
     * @Description 查询所有
     * @param matterId
     * @return
     */
    List<Reply> findList(Long matterId);
}
