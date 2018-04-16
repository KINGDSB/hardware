package com.honeylovely.center.sys.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honeylovely.center.common.ApiContant;
import com.honeylovely.center.common.Log;
import com.honeylovely.center.common.utils.LexinApiUtil;
import com.honeylovely.center.sys.entity.DeptTreeView;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "dept")
public class DepartmentController {
    
    @ApiOperation(value = "部门树形菜单")
    @RequestMapping(value = "/treeView")
    @Log(module = "部门模块", method = "部门树形菜单")
    public ModelAndView deptTree() {
        JSONArray deptArr = LexinApiUtil.postRequest(ApiContant.SERVICE_DEPARTMENT_GET_LIST, null).getJSONArray("data");

        // 排序
        List<JSONObject> deptList = new ArrayList<JSONObject> ();
        JSONObject jsonObj = null;
        for (int i = 0; i < deptArr.size(); i++) {
            jsonObj = (JSONObject)deptArr.get(i);
            deptList.add(jsonObj);
        }
        Collections.sort(deptList,new Comparator<JSONObject>(){
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                int id1 = o1.getInteger("parent_id");
                int id2 = o2.getInteger("parent_id");
                return id1 - id2;
            }
        });
        
        ModelAndView modelAndView = new ModelAndView();
        DeptTreeView treeView = DeptTreeView.buildResourceTree(deptList, Collections.emptyList());
        modelAndView.addObject(treeView);
        
        return modelAndView;
    }

    @RequestMapping(value = "/deptSelect")
    public ModelAndView input(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(request.getRequestURI().replace("center/dept", "project"));
        return modelAndView;
    }

    public String getControllerMapping(String requestURI, String contextPath) {
        String[] rs = requestURI.split("/");

        if (rs.length < 2) {
            return contextPath + "/index";
        } else if (rs.length < 3) {
            return "/index";
        }

        StringBuilder mapping = new StringBuilder();
        for (int i = 2; i < rs.length - 1; i++) {
            mapping.append(rs[i]);
            mapping.append("/");
        }

        return mapping.toString();
    }
}