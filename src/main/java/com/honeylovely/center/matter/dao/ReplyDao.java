package com.honeylovely.center.matter.dao;

import java.util.List;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.matter.entity.Reply;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface ReplyDao extends AbstractDAO<Reply, Long> {
    
    /**
     * @Title findList 
     * @Description 查询所有
     * @param matterId
     * @return
     */
    List<Reply> findList(Long matterId);
}
