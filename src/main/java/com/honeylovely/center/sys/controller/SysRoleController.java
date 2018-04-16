package com.honeylovely.center.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.honeylovely.center.common.Log;
import com.honeylovely.center.common.Page;
import com.honeylovely.center.common.ResponseCode;
import com.honeylovely.center.common.abstracts.AbstractController;
import com.honeylovely.center.common.abstracts.AbstractService;
import com.honeylovely.center.common.utils.Response;
import com.honeylovely.center.sys.entity.SysResource;
import com.honeylovely.center.sys.entity.SysRole;
import com.honeylovely.center.sys.service.SysResourceService;
import com.honeylovely.center.sys.service.SysRoleService;

import tk.mybatis.mapper.entity.Example;


@Controller
@RequestMapping(value = "role")
public class SysRoleController extends AbstractController<SysRole, Long> {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysResourceService sysResourceService;

	public SysRoleController() {
	}

	public AbstractService<SysRole, Long> getService() {
		return this.sysRoleService;
	}

	public SysResourceService getSysResourceService() {
		return this.sysResourceService;
	}

	@RequestMapping(value = "/list")
	@Log(module = "角色管理", method = "角色列表")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int length = ServletRequestUtils.getIntParameter(request, "length", 10);
		int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
		String name = ServletRequestUtils.getStringParameter(request, "name", null);

		// int page = pageFromStart(start, length);

		// PageRequest pageRequest = new PageRequest(page, length);
		RowBounds rowBounds = new RowBounds(start, length);

		Example example = new Example(SysResource.class);
		example.setOrderByClause(" created_date desc");
		if (!StringUtils.isBlank(name))
			example.createCriteria().andLike("name", "%"+name+"%");

		example.createCriteria().andEqualTo("isDeleted", false);
		Page<SysRole> res = sysRoleService.selectPage(rowBounds, example);
		res.setDraw(draw);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", res);

		return modelAndView;
	}

	@RequestMapping(value = "remove")
	@Log(module = "角色管理", method = "删除角色")
	public @ResponseBody
    String remove(@RequestParam("id") Long id) {
		if (id == null)
			return new Response(ResponseCode.REQUEST_ERROR).toJson();
		Example example = new Example(SysRole.class);
		example.createCriteria().andEqualTo("id", id);

		List<SysRole> selectByExample = sysRoleService.selectByExample(example);
		if (selectByExample.size() > 0) {
			SysRole sysRole = selectByExample.get(0);
			sysRole.setIsDeleted(true);
			sysRoleService.update(sysRole);
		}
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "create")
	@Log(module = "角色管理", method = "添加角色")
	public @ResponseBody
    String create(@RequestBody SysRole sysRole) {
		sysRoleService.insert(sysRole);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "edit")
	@Log(module = "角色管理", method = "编辑角色")
	public @ResponseBody
    String edit(@RequestBody SysRole sysRole) {
		sysRoleService.update(sysRole);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "select")
	public @ResponseBody
    ModelAndView roleSelect() {
		return new ModelAndView("user/roleSelect");
	}

	@RequestMapping(value = "roles")
	@Log(module = "角色管理", method = "角色列表（配置）")
	public ModelAndView roles(@RequestParam("userId") Long userId) {
		List<SysRole> roles = sysRoleService.selectByExample(new Example(SysRole.class));

		List<SysRole> userRoles = sysRoleService.findUserRoles(userId);

		ModelAndView modelAndView = new ModelAndView("user/roleSelect");

		if (userRoles != null && userRoles.size() > 0) {
			List<SysRole> unselected = new ArrayList<>();
			for (SysRole role : roles) {
				boolean selected = false;
				for (SysRole userRole : userRoles) {
					if (role.getId() == userRole.getId()) {
						selected = true;
						break;
					}
				}

				if (!selected)
					unselected.add(role);
			}
			modelAndView.addObject("roles", unselected);
		} else {
			modelAndView.addObject("roles", roles);
		}

		modelAndView.addObject("userRoles", userRoles);
		return modelAndView;
	}

	@RequestMapping(value = "perms")
	@Log(module = "角色管理", method = "角色菜单配置")
	public @ResponseBody
    String editResource(@RequestParam("roleId") Long roleId,
                        @RequestParam("addIds") List<Long> addIds, @RequestParam("removeIds") List<Long> removeIds) {

		sysRoleService.saveResourceChange(roleId, addIds, removeIds);
		return new Response(ResponseCode.SUCCESS).toJson();
	}
}