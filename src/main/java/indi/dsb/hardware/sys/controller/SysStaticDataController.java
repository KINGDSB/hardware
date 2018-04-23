package indi.dsb.hardware.sys.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.Log;
import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.abstracts.AbstractController;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.sys.entity.SysResource;
import indi.dsb.hardware.sys.entity.SysStaticData;
import indi.dsb.hardware.sys.service.SysResourceService;
import indi.dsb.hardware.sys.service.SysStaticDataService;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping(value = "staticData")
public class SysStaticDataController extends AbstractController<SysStaticData, Long> {
    private static final Logger logger = LoggerFactory.getLogger(SysStaticDataController.class);
    @Autowired
    private SysStaticDataService sysStaticDataService;
    @Autowired
    private SysResourceService sysResourceService;


    @RequestMapping(value = "/list")
    @Log(module = "数据管理", method = "数据列表")
    public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
        int start = ServletRequestUtils.getIntParameter(request, "start", 0);
        int length = ServletRequestUtils.getIntParameter(request, "length", 10);
        int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
        String dataKey = ServletRequestUtils.getStringParameter(request, "dataKey", null);
        String description = ServletRequestUtils.getStringParameter(request, "description", null);

        RowBounds rowBounds = new RowBounds(start, length);

        Example example = new Example(SysStaticData.class);
        if (StringUtils.isNotBlank(description)){
            example.createCriteria().andLike("description", "%"+description+"%");
        }
        if (StringUtils.isNotBlank(dataKey)){
            example.createCriteria().andLike("dataKey", "%"+dataKey+"%");
        }

        example.createCriteria().andEqualTo("isDeleted", false);
        example.setOrderByClause(" created_date desc");
        Page<SysStaticData> res = sysStaticDataService.selectPage(rowBounds, example);
        res.setDraw(draw);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", res);

        return modelAndView;
    }

    @RequestMapping(value = "remove")
    @Log(module = "数据管理", method = "删除数据")
    public @ResponseBody String remove(@RequestParam("id") Long id) {
        if (id == null)
            return new Response(ResponseCode.REQUEST_ERROR).toJson();
        Example example = new Example(SysStaticData.class);
        example.createCriteria().andEqualTo("id", id);

        List<SysStaticData> selectByExample = sysStaticDataService.selectByExample(example);
        if (selectByExample.size() > 0) {
            SysStaticData sysStaticData = selectByExample.get(0);
            sysStaticData.setIsDeleted(true);
            sysStaticDataService.update(sysStaticData);
        }
        return new Response(ResponseCode.SUCCESS).toJson();
    }

    @RequestMapping(value = "create")
    @Log(module = "数据管理", method = "添加数据")
    public @ResponseBody String create(@RequestBody SysStaticData sysStaticData) {
//        boolean exists = sysResourceService.exists(null, sysResource.getResUrl());
//        if (exists) {
//            return new Response(ResponseCode.URL_EXIST).toJson();
//        }
//
//        exists = sysResourceService.exists(sysResource.getResKey(), null);
//        if (exists) {
//            return new Response(ResponseCode.RES_KEY_EXIST).toJson();
//        }

        sysStaticDataService.insert(sysStaticData);
        return new Response(ResponseCode.SUCCESS).toJson();
    }

    @RequestMapping(value = "edit")
    @Log(module = "数据管理", method = "编辑数据")
    public @ResponseBody String edit(@RequestBody SysStaticData sysStaticData) {
        SysStaticData oData = sysStaticDataService.get(sysStaticData.getId());
        
        oData.setDataKey(sysStaticData.getDataKey());
        oData.setDataValueCn(sysStaticData.getDataValueCn());
        oData.setDataValueEn(sysStaticData.getDataValueEn());
        sysStaticDataService.update(oData);
        return new Response(ResponseCode.SUCCESS).toJson();
    }
    
    @Override
    public AbstractService<SysStaticData, Long> getService() {
        return sysStaticDataService;
    }

    @Override
    public SysResourceService getSysResourceService() {
        return sysResourceService;
    }

}