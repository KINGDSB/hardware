package com.honeylovely.center.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.honeylovely.center.common.Log;
import com.honeylovely.center.common.ResponseCode;
import com.honeylovely.center.common.abstracts.AbstractController;
import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.common.type.ProjectMemberType;
import com.honeylovely.center.common.utils.Response;
import com.honeylovely.center.sys.entity.Project;
import com.honeylovely.center.sys.entity.ProjectMember;
import com.honeylovely.center.sys.service.ProjectMemberService;
import com.honeylovely.center.sys.service.ProjectService;
import com.honeylovely.center.sys.service.SysResourceService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping(value = "project")
public class ProjectController extends AbstractController<Project, Long> {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public AbstractService<Project, Long> getService() {
        return projectService;
    }

    @Override
    public SysResourceService getSysResourceService() {
        return sysResourceService;
    }

    @ApiOperation(value = "获取项目下拉列表")
    @ApiImplicitParam(name = "project", value = "实体类", required = true, dataType = "Project")
    @RequestMapping(value = "/getSelect")
    @Log(module = "项目管理", method = "获取项目下拉列表")
    public ModelAndView getSelect(HttpServletRequest request, HttpServletResponse response) {

        Example example = new Example(Project.class);
        example.selectProperties("id", "name");
        example.createCriteria().andEqualTo("isDeleted", false);
        List<Project> projects = projectService.selectByExample(example);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", projects);

        return modelAndView;
    }

    @ApiOperation(value = "获取项目信息")
    @ApiImplicitParam(name = "project", value = "实体类", required = true, dataType = "Project")
    @RequestMapping(value = "/getProjectInfo")
    @Log(module = "项目管理", method = "获取项目信息")
    public ModelAndView getProjectInfo(HttpServletRequest request, HttpServletResponse response) {
        long projectId = ServletRequestUtils.getLongParameter(request, "projectId", 0);
        Project project = projectService.getProjectInfo(projectId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    @ApiOperation(value = "获取项目成员列表")
    @ApiImplicitParam(name = "project", value = "实体类", required = true, dataType = "Project")
    @RequestMapping(value = "/getMemberList")
    @Log(module = "项目管理", method = "获取项目成员下拉列表")
    public ModelAndView getMemberList(HttpServletRequest request, HttpServletResponse response) {

        Long projectId = ServletRequestUtils.getLongParameter(request, "projectId", 0);
        List<ProjectMember> projectMembers = projectService.getProjectMambersByProjectId(projectId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", projectMembers);

        return modelAndView;
    }
    
    @RequestMapping(value = "remove")
    @Log(module = "项目管理", method = "项目问题")
    public @ResponseBody
    String remove(@RequestParam("id") Long id) {
        if (id == null)
            return new Response(ResponseCode.REQUEST_ERROR).toJson();
        
        // 删除问题回复
        List<ProjectMember> members = projectService.getProjectMambersByProjectId(id);
        if (members.size() > 0) {
            for (ProjectMember member : members) {
                member.setIsDeleted(true);
                projectMemberService.update(member);
            }
        }
        
        // 删除问题
        Project project = projectService.get(id);
        if (null != project) {
            project.setIsDeleted(true);
            projectService.update(project);
        }
        
        return new Response(ResponseCode.SUCCESS).toJson();
    }

    @ApiOperation(value = "新增项目")
    @ApiImplicitParam(name = "project", required = true, dataType = "Project")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Log(module = "项目模块", method = "新增项目")
    public @ResponseBody String create(@RequestBody Project project) {
        try {
            projectService.insert(project);
        } catch (Exception e) {
            logger.error("新增项目出错", e);
            e.printStackTrace();
            return new Response(ResponseCode.SERVER_ERROR).toJson();
        }
        return new Response(ResponseCode.SUCCESS).toJson();
    }

    @ApiOperation(value = "保存项目成员列表")
    @RequestMapping(value = "/saveMemberList")
    @Log(module = "项目管理", method = "保存项目成员列表")
    public Response saveMemberList(@RequestBody JSONObject parameter) {

        Long projectId = parameter.getLong("projectId");
        Long productUid = parameter.getLong("productUid");
        
        if (null == projectId || null == productUid) {
            return new Response(ResponseCode.MISS_REQUIRED);
        }

        JSONArray memberIds = new JSONArray();
        Object objMids = parameter.get("memberUid");
        if (objMids instanceof String) {
            memberIds.add(objMids);
        } else if (objMids instanceof List) {
            memberIds = parameter.getJSONArray("memberUid");
        }
//        String[] memberIds = ServletRequestUtils.getStringParameters(request, "memberUid");

        Example example = new Example(ProjectMember.class);
        example.createCriteria().andEqualTo("isDeleted", false).andEqualTo("projectId", projectId);
        List<ProjectMember> projectMembers = projectMemberService.selectByExample(example);
        for (ProjectMember projectMember : projectMembers) {
            projectMember.setIsDeleted(true);
            projectMemberService.update(projectMember);
        }
//        List<ProjectMember> projectMembers = projectService.getProjectMambersByProjectId(projectId);
//        for (ProjectMember projectMember : projectMembers) {
//            projectMemberService.delete(projectMember.getId());
//        }
        
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectId(projectId);
        projectMember.setMemberId(productUid);
        projectMember.setType(ProjectMemberType.PRODUCT_USER.getCode());
        projectMemberService.insert(projectMember);
        
//        for (String memberId : memberIds) {
//            projectMember = new ProjectMember();
//            projectMember.setProjectId(projectId);
//            projectMember.setMemberId(Long.valueOf(memberId));
//            projectMember.setType(ProjectMemberType.MEMBER_USER.getCode());
//            projectMemberService.insert(projectMember);
//        }
        for (int i = 0, len = memberIds.size(); i < len; i++) {
            if (StringUtils.isNotBlank(memberIds.getString(i))) {
                projectMember = new ProjectMember();
                projectMember.setProjectId(projectId);
                projectMember.setMemberId(Long.valueOf(memberIds.getString(i)));
                projectMember.setType(ProjectMemberType.MEMBER_USER.getCode());
                projectMemberService.insert(projectMember);
            }
        }
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", projectMembers);

        return new Response(ResponseCode.SUCCESS);
    }
}