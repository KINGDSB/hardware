package indi.dsb.hardware.matter.service;

import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.matter.entity.Reply;

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
