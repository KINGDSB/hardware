package indi.dsb.hardware.product.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.product.dao.ReplyDao;
import indi.dsb.hardware.product.entity.Reply;
import indi.dsb.hardware.product.mapper.ReplyMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class ReplyDaoImpl extends AbstractDAOImpl<Reply, Long> implements ReplyDao {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public Mapper<Reply> getMapper() {
        return replyMapper;
    }

    @Override
    public List<Reply> findList(Long matterId) {
        return replyMapper.findList(matterId);
    }

}
