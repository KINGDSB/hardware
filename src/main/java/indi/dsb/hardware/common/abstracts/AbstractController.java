package indi.dsb.hardware.common.abstracts;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.sys.service.SysResourceService;


abstract public class AbstractController<T, PK extends Serializable> {

	@RequestMapping(value = "/listPage")
	public ModelAndView listPage(HttpServletRequest request) {
		Long resId = ServletRequestUtils.getLongParameter(request, "resId", 0);
//		Long userId = WebUtils.getLoginUser().getId();
//		List<SysResource> btns = this.getSysResourceService().findByParentIdAndUserId(resId, userId);
		ModelAndView modelAndView = new ModelAndView(getControllerMapping(request.getRequestURI(), request.getContextPath()) + "list");
//		modelAndView.addObject("btns", btns);
		return modelAndView;
	}

	@RequestMapping(value = "/inputPage")
	public ModelAndView input(HttpServletRequest request) {
		String action = ServletRequestUtils.getStringParameter(request, "action", null);

		ModelAndView modelAndView = new ModelAndView(getControllerMapping(request.getRequestURI(), request.getContextPath()) + "input");
		modelAndView.addObject("action", action);
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

	public int pageFromStart(int start, int length) {
		return start == 0 ? start : start / length;
	}

	abstract public AbstractService<T, PK> getService();

	abstract public SysResourceService getSysResourceService();
}

