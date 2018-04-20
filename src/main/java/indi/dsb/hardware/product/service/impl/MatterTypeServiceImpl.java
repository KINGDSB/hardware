package indi.dsb.hardware.product.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.product.dao.MatterTypeDao;
import indi.dsb.hardware.product.entity.MatterType;
import indi.dsb.hardware.product.service.MatterTypeService;

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
