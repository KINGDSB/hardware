package indi.dsb.hardware.sys.service;

import java.util.Map;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.sys.entity.SysStaticData;

/**
 * Created by ryan on 2016-03-01.
 */
public interface SysStaticDataService extends AbstractService<SysStaticData, Long> {
    
    Map<String, SysStaticData> getStaticData();
    
}