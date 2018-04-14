package com.honeylovely.center.matter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.matter.dao.ReplyDao;
import com.honeylovely.center.matter.entity.Reply;
import com.honeylovely.center.matter.service.ReplyService;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("replyService")
public class ReplyServiceImpl extends AbstractServiceImpl<Reply, Long> implements ReplyService {

    private static Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

    @Autowired
    private ReplyDao replyDao;

    @Override
    public AbstractDAO<Reply, Long> getAbstractDAO() {
        return replyDao;
    }

    @Override
    public List<Reply> findList(Long matterId) {
        return replyDao.findList(matterId);
    }

}
