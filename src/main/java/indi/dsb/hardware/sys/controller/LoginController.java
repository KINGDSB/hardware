package indi.dsb.hardware.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.Log;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.matter.service.MatterService;
import indi.dsb.hardware.sys.service.SysResourceService;

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
		
		
		ModelAndView modelAndView = new ModelAndView("index");
//		modelAndView.addObject("resources", resources);
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
