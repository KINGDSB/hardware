package indi.dsb.hardware.matter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.matter.dao.ReplyDao;
import indi.dsb.hardware.matter.entity.Reply;
import indi.dsb.hardware.matter.service.ReplyService;

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
