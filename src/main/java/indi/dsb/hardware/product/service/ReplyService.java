package indi.dsb.hardware.product.service;

import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.product.entity.Reply;

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
