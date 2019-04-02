package indi.dsb.hardware.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.sys.dao.SysStaticDataDAO;
import indi.dsb.hardware.sys.entity.SysStaticData;
import indi.dsb.hardware.sys.service.SysStaticDataService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * Created by ryan on 2016-03-01.
 */
@Service("sysStaticDataService")
public class SysStaticDataServiceImpl extends AbstractServiceImpl<SysStaticData, Long> implements SysStaticDataService {
    @Autowired
    private SysStaticDataDAO sysStaticDataDAO;

    @Override
    public AbstractDAO<SysStaticData, Long> getAbstractDAO() {
        return sysStaticDataDAO;
    }

    @Override
    public Map<String, SysStaticData> getStaticData() {
        
        Example example = new Example(SysStaticData.class);
        example.createCriteria().andEqualTo("isDeleted", false);
        List<SysStaticData> list = selectByExample(example);
        
        Map<String, SysStaticData> staticDataMap = new HashMap<String, SysStaticData>();
        for (SysStaticData sysStaticData : list) {
            staticDataMap.put(sysStaticData.getDataKey(), sysStaticData);
        }
        
        return staticDataMap;
    }

	@Override
	public List<SysStaticData> getIndexImg() {
        Example example = new Example(SysStaticData.class);
        Criteria criteria = example.createCriteria();
        criteria.andLike("dataKey", "%img%");
        criteria.andEqualTo("isDeleted", false);
        example.setOrderByClause(" id ");
        List<SysStaticData> list = selectByExample(example);
		return list;
	}

}