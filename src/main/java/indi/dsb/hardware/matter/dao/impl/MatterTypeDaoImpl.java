package indi.dsb.hardware.matter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.matter.dao.MatterTypeDao;
import indi.dsb.hardware.matter.entity.MatterType;
import indi.dsb.hardware.matter.mapper.MatterTypeMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class MatterTypeDaoImpl extends AbstractDAOImpl<MatterType, Long> implements MatterTypeDao {

    @Autowired
    private MatterTypeMapper matterTypeMapper;

    @Override
    public Mapper<MatterType> getMapper() {
        return matterTypeMapper;
    }

}
