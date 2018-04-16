package com.honeylovely.center.matter.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeylovely.center.common.abstracts.AbstractDAOImpl;
import com.honeylovely.center.matter.dao.ReplyDao;
import com.honeylovely.center.matter.entity.Reply;
import com.honeylovely.center.matter.mapper.ReplyMapper;

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
