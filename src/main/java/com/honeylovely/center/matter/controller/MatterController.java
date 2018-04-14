package com.honeylovely.center.matter.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.honeylovely.center.common.ApiContant;
import com.honeylovely.center.common.Log;
import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.ResponseCode;
import com.honeylovely.center.common.abstracts.AbstractController;
import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.common.type.MatterGrade;
import com.honeylovely.center.common.type.MatterStatus;
import com.honeylovely.center.common.type.ReplyStatus;
import com.honeylovely.center.common.type.ReplyType;
import com.honeylovely.center.common.utils.DateUtil;
import com.honeylovely.center.common.utils.HttpUtil;
import com.honeylovely.center.common.utils.Response;
import com.honeylovely.center.matter.entity.Matter;
import com.honeylovely.center.matter.entity.MatterType;
import com.honeylovely.center.matter.entity.Reply;
import com.honeylovely.center.matter.service.MatterService;
import com.honeylovely.center.matter.service.MatterTypeService;
import com.honeylovely.center.matter.service.ReplyService;
import com.honeylovely.center.sys.entity.SysUser;
import com.honeylovely.center.sys.service.ProjectService;
import com.honeylovely.center.sys.service.SysResourceService;
import com.honeylovely.center.sys.service.SysUserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/7.
 */

@RestController
@RequestMapping("/matter")
public class MatterController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(MatterController.class);

    @Autowired
    private MatterService matterService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private MatterTypeService matterTypeService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ProjectService projectService;

    @Override
    public AbstractService getService() {
        return matterService;
    }

    @Override
    public SysResourceService getSysResourceService() {
        return sysResourceService;
    }

    @ApiOperation(value = "查询问题列表")
    @ApiImplicitParam(name = "matter", value = "实体类", required = true, dataType = "Matter")
    @Log(method = "问题列表", module = "问题管理")
    @RequestMapping(value = "/list")
    public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
        int start = ServletRequestUtils.getIntParameter(request, "start", 0);
        int length = ServletRequestUtils.getIntParameter(request, "length", 10);
        int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
        long projectId = ServletRequestUtils.getLongParameter(request, "projectId", 0);
        Date createdDate = null;
        String strCreatedDate = ServletRequestUtils.getStringParameter(request, "createdDate", null);
        if (StringUtils.isNotBlank(strCreatedDate)) {
            createdDate = DateUtil.stringToDate(strCreatedDate);
        }
        int processStatus = ServletRequestUtils.getIntParameter(request, "processStatus", 0);
        int type = ServletRequestUtils.getIntParameter(request, "type", 0);
        int grade = ServletRequestUtils.getIntParameter(request, "grade", 0);
        String keyWord = ServletRequestUtils.getStringParameter(request, "keyWord", null);

        Matter matter = new Matter();
        matter.setIsDeleted(false);
        if (projectId > 0) {
            matter.setProjectId(projectId);
        }
        if (processStatus > 0) {
            matter.setProcessStatus(processStatus);
        }
        if (type > 0) {
            matter.setType(type);
        }
        if (grade > 0) {
            matter.setGrade(grade);
        }
        if (StringUtils.isNotBlank(keyWord)) {
            matter.setKeyWord(keyWord);
        }
        if (null != createdDate) {
            matter.setCreatedDate(createdDate);
        }

        Sort sort = new Sort(Direction.DESC, Arrays.asList(new String[] { "t1.created_date" }));
        PageRequest pageRequest = new PageRequest(start, length, sort);
        Page<Matter> list = matterService.findList(matter, pageRequest);
        list.setDraw(draw);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @ApiOperation(value = "添加问题")
    @ApiImplicitParam(name = "matter", required = true, dataType = "Matter")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Log(module = "问题模块", method = "添加问题")
    public @ResponseBody String create(@RequestBody Matter matter) {
        try {
            matter.setProcessStatus(MatterStatus.WAIT_HANDLE.getCode());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR_OF_DAY, MatterGrade.getByCode(matter.getGrade()).getProcessingTime());
            matter.setExpectTime(calendar.getTime());
            matterService.insert(matter);
        } catch (Exception e) {
            logger.error("添加问题出错", e);
            e.printStackTrace();
            return new Response(ResponseCode.SERVER_ERROR).toJson();
        }
        return new Response(ResponseCode.SUCCESS).toJson();
    }

    @RequestMapping(value = "/getMatterTypeSelect")
    @Log(module = "问题模块", method = "获取问题类型下拉列表")
    public ModelAndView getMatterTypeSelect(HttpServletRequest request, HttpServletResponse response) {

        Example example = new Example(MatterType.class);
        example.createCriteria().andEqualTo("isDeleted", false);
        List<MatterType> options = matterTypeService.selectByExample(example);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("options", options);

        return modelAndView;
    }

    @RequestMapping(value = "/assignMatter")
    @Log(module = "问题模块", method = "分配问题")
    public Response assignMatter(HttpServletRequest request, HttpServletResponse response) {
        
        String replyUids = ServletRequestUtils.getStringParameter(request, "replyUids", null);
        long id = ServletRequestUtils.getLongParameter(request, "id", 0);
        String solution = ServletRequestUtils.getStringParameter(request, "solution", null);
        String attachment = ServletRequestUtils.getStringParameter(request, "attachment", null);
        
        if (id > 0) {
            Matter matter = matterService.get(id);
            
            // 项目经理评论
            Reply reply = new Reply();
            reply.setMatterId(id);
            reply.setType(ReplyType.PRODUCT_USER_REPLY.getCode());
            reply.setSolution(solution);
            reply.setAttachment(attachment);
            reply.setSubStatus(ReplyStatus.HANDLED.getCode());
            replyService.insert(reply);
            
            SysUser replyUser = null;
            // 指派负责人
            if (StringUtils.isNotBlank(replyUids)) {
                for (String replyId : replyUids.split(",")) {
                    reply = new Reply();
                    reply.setMatterId(id);
                    reply.setType(ReplyType.PROCESS_USER_REPLY.getCode());
                    reply.setUserId(Long.valueOf(replyId));
                    reply.setSubStatus(ReplyStatus.PENDING.getCode());
                    replyService.insert(reply);
                    
                    replyUser = sysUserService.get(Long.valueOf(replyId));
                }
                matter.setReplyUid(replyUids);
                matter.setProcessStatus(MatterStatus.PROCESSING.getCode());
            } else {
                matter.setProcessStatus(MatterStatus.CONFIRM.getCode());
            }
            matterService.update(matter);
            return new Response(ResponseCode.SUCCESS);
        } else {
            return new Response(ResponseCode.MISS_REQUIRED);
        }
    }

    @RequestMapping(value = "/replySubmit")
    @Log(module = "问题模块", method = "提交回复问题")
    public Response replySubmit(HttpServletRequest request, HttpServletResponse response) {

        long id = ServletRequestUtils.getLongParameter(request, "id", 0);
        String solution = ServletRequestUtils.getStringParameter(request, "solution", null);
        String attachment = ServletRequestUtils.getStringParameter(request, "attachment", null);
        // 提交回复
        Reply reply = replyService.get(id);
        reply.setSolution(solution);
        reply.setAttachment(attachment);
        reply.setSubStatus(ReplyStatus.HANDLED.getCode());
        replyService.update(reply);
        
        // 所有人回复完毕则变为待确认
        Example example = new Example(Reply.class);
        example.createCriteria().andEqualTo("matterId", reply.getMatterId())
            .andEqualTo("subStatus", ReplyStatus.PENDING.getCode())
            .andEqualTo("isDeleted", false);
        List<Reply> replies = replyService.selectByExample(example);
        if (null == replies || replies.size() == 0) {
            Matter matter = matterService.get(reply.getMatterId());
            matter.setProcessStatus(MatterStatus.CONFIRM.getCode());
            matter.setTrueTime(new Date());
            matterService.update(matter);
        }

        return new Response(ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/applyDelay")
    @Log(module = "问题模块", method = "申请延期")
    public Response applyDelay(HttpServletRequest request, HttpServletResponse response) {

        long id = ServletRequestUtils.getLongParameter(request, "id", 0);
        Matter matter = matterService.get(id);

        JSONObject param = new JSONObject();
        param.put("projectName", projectService.get(matter.getProjectId()).getName());
        param.put("matterType", matterTypeService.get(matter.getType().longValue()).getName());
        param.put("grade", matter.getGrade());
        param.put("feedbackUname", sysUserService.get(matter.getFeedbackUid()).getName());
        param.put("productUname", sysUserService.get(matter.getProductUid()).getName());
        param.put("createdDate", DateUtil.formatDateYMd(matter.getCreatedDate()));
        param.put("attachment", matter.getAttachment());
        param.put("description", matter.getDescription());
        param.put("expectDate", DateUtil.formatDateYMd(matter.getExpectTime()));
        param.put("expectTime", DateUtil.formatTime(matter.getExpectTime()));
        
        String paramStr = param.toJSONString();
        String result = HttpUtil.postData(ApiContant.SERVICE_OA_WORKFLOW_SAVEPOSTPONE, paramStr, "application/json");
        if (StringUtils.isBlank(result)) {
            logger.error("[savePostpone] 接口异常");
            logger.error("[savePostpone] url:" + ApiContant.SERVICE_OA_WORKFLOW_SAVEPOSTPONE);
            logger.error("[savePostpone] data:" + paramStr);
            logger.error("[savePostpone] result:" + result);
            return new Response(ResponseCode.SERVER_ERROR);
        }
        logger.error("[savePostpone] result" + result);
        JSONObject jsonResult = JSONObject.parseObject(result);
        if (200 != jsonResult.getIntValue("code")) {
            return new Response(ResponseCode.SERVER_ERROR, jsonResult.getString("desc"));
        }
        
        matter.setProcessStatus(MatterStatus.APPLY_DELAYING.getCode());
        matter.setRemark(jsonResult.getInteger("result").toString());
        matterService.update(matter);
        
        return new Response(ResponseCode.SUCCESS);
    }

    @RequestMapping(value = "/auditMatter")
    @Log(module = "问题模块", method = "审核问题")
    public Response auditMatter(HttpServletRequest request, HttpServletResponse response) {

        long id = ServletRequestUtils.getLongParameter(request, "id", 0);
        int processStatus = ServletRequestUtils.getIntParameter(request, "processStatus", 0);

        Matter matter = matterService.get(id);
        matter.setProcessStatus(processStatus);
        matterService.update(matter);
        return new Response(ResponseCode.SUCCESS);
    }

    @Log(method = "问题回复列表", module = "问题管理")
    @RequestMapping(value = "/replyList")
    public ModelAndView replyList(HttpServletRequest request, HttpServletResponse response) {
        long matterId = ServletRequestUtils.getLongParameter(request, "matterId", 0);

        List<Reply> list = replyService.findList(matterId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        return modelAndView;
    }
    
    @RequestMapping(value = "remove")
    @Log(module = "问题管理", method = "删除问题")
    public @ResponseBody
    String remove(@RequestParam("id") Long id) {
        if (id == null)
            return new Response(ResponseCode.REQUEST_ERROR).toJson();
        
        // 删除问题回复
        Example example = new Example(Reply.class);
        example.createCriteria().andEqualTo("matterId", id)
            .andEqualTo("isDeleted", false);
        List<Reply> replies = replyService.selectByExample(example);
        if (replies.size() > 0) {
            for (Reply reply : replies) {
                reply.setIsDeleted(true);
                replyService.update(reply);
            }
        }
        
        // 删除问题
        Matter matter = matterService.get(id);
        if (null != matter) {
            matter.setIsDeleted(true);
            matterService.update(matter);
        }
        
        return new Response(ResponseCode.SUCCESS).toJson();
    }
    
    @RequestMapping(value = "/detail")
    public ModelAndView detail(HttpServletRequest request) {
        String action = ServletRequestUtils.getStringParameter(request, "action", null);

        ModelAndView modelAndView = new ModelAndView(getControllerMapping(request.getRequestURI(), request.getContextPath()) + "detail");
        modelAndView.addObject("action", action);
        return modelAndView;
    }
    

}
