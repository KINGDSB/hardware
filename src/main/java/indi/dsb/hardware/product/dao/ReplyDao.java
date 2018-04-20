package indi.dsb.hardware.product.dao;

import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.product.entity.Reply;

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
