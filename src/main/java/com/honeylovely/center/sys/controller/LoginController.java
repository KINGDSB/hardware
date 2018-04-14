package com.honeylovely.center.sys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.honeylovely.center.common.Log;
import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.ResponseCode;
import com.honeylovely.center.common.type.MatterGrade;
import com.honeylovely.center.common.utils.Response;
import com.honeylovely.center.matter.entity.Matter;
import com.honeylovely.center.matter.service.MatterService;
import com.honeylovely.center.sys.entity.SysResource;
import com.honeylovely.center.sys.service.SysResourceService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private SysResourceService sysResourceService;
	@Autowired
	private MatterService matterService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView fail(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("index");

//		if ("org.apache.shiro.authc.AuthenticationException".equals(error)) {
//			model.addObject("errorMsg", "帐号或密码错误，请重试!");
//		} else if ("org.apache.shiro.authc.IncorrectCredentialsException".equals(error)) {
//			model.addObject("errorMsg", "用户名不存在，请重试!");
//		} else {
//			model.addObject("errorMsg", "帐号或密码错误，请重试!");
//		}
//
//		model.addObject(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return model;
	}
	
	@RequestMapping(value = "index")
	public ModelAndView index() {
//		Subject subject = SecurityUtils.getSubject();
//		if (subject == null) {
//			return new ModelAndView("login");
//		}
//		Session session = subject.getSession();
//		MapperRealm.LoginUser user = (MapperRealm.LoginUser) session.getAttribute("user");
		
//		List<SysResource> resources = sysResourceService.findResourcesByUserId(user.getId());
		
		// 获取问题数据
//		Map matterStatisticsData = matterService.statisticsData(user.getId());

		Sort sort = new Sort(Direction.DESC, Arrays.asList(new String[]{"t1.created_date"}));
        PageRequest pageRequest = new PageRequest(0, 5, sort);
        Matter matter = new Matter();
        matter.setGrade(MatterGrade.LEVEL_4.getCode());
        Page<Matter> dangerList = matterService.findList(matter, pageRequest);
        matter.setGrade(MatterGrade.LEVEL_3.getCode());
        Page<Matter> warningList = matterService.findList(matter, pageRequest);
        matter.setGrade(MatterGrade.LEVEL_2.getCode());
        Page<Matter> defaultList = matterService.findList(matter, pageRequest);
		
		ModelAndView modelAndView = new ModelAndView("index");
//		modelAndView.addObject("resources", resources);
//		modelAndView.addObject("matterStatisticsData", matterStatisticsData);
		modelAndView.addObject("dangerList", dangerList.getData());
		modelAndView.addObject("warningList", warningList.getData());
		modelAndView.addObject("defaultList", defaultList.getData());
		return modelAndView;
	}

	@RequestMapping(value = "logout")
	@Log(module = "登陆", method = "用户退出")
	public @ResponseBody
    String logout() {
//		Subject subject = SecurityUtils.getSubject();
//
//		if (subject == null) {
//			return new Response(ResponseCode.SUCCESS).toJson();
//		}
//
//		subject.logout();

		return new Response(ResponseCode.SUCCESS).toJson();
	}

}
