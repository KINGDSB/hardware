package indi.dsb.hardware.sys.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.sys.dao.SysStaticDataDAO;
import indi.dsb.hardware.sys.entity.SysStaticData;
import indi.dsb.hardware.sys.mapper.SysStaticDataMapper;
import tk.mybatis.mapper.common.Mapper;

@Repository
public class SysStaticDataDAOImpl extends AbstractDAOImpl<SysStaticData, Long> implements SysStaticDataDAO {
    @Autowired
    private SysStaticDataMapper sysStaticDataMapper;

    @Override
    public Mapper<SysStaticData> getMapper() {
        return sysStaticDataMapper;
    }

}
