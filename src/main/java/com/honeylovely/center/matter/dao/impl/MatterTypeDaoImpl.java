package com.honeylovely.center.matter.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeylovely.center.common.abstracts.AbstractDAOImpl;
import com.honeylovely.center.matter.dao.MatterTypeDao;
import com.honeylovely.center.matter.entity.MatterType;
import com.honeylovely.center.matter.mapper.MatterTypeMapper;

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
