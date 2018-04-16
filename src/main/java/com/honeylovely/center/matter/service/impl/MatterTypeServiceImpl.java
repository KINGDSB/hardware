package com.honeylovely.center.matter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeylovely.center.common.abstracts.AbstractDAO;
import com.honeylovely.center.common.abstracts.AbstractServiceImpl;
import com.honeylovely.center.matter.dao.MatterTypeDao;
import com.honeylovely.center.matter.entity.MatterType;
import com.honeylovely.center.matter.service.MatterTypeService;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("matterTypeService")
public class MatterTypeServiceImpl extends AbstractServiceImpl<MatterType, Long> implements MatterTypeService {

    private static Logger logger = LoggerFactory.getLogger(MatterTypeServiceImpl.class);

    @Autowired
    private MatterTypeDao matterType;

    @Override
    public AbstractDAO<MatterType, Long> getAbstractDAO() {
        return matterType;
    }

}
