package com.honeylovely.center.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.honeylovely.center.sys.entity.TreeView;
import com.honeylovely.center.sys.service.SysResourceService;

import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping(value = "resource")
public class SysResourceController extends AbstractController<SysResource, Long> {
	@Autowired
	private SysResourceService sysResourceService;

	@Override
	public AbstractService<SysResource, Long> getService() {
		return sysResourceService;
	}

	@Override
	public SysResourceService getSysResourceService() {
		return sysResourceService;
	}

	@RequestMapping(value = "/list")
	@Log(module = "菜单管理", method = "菜单列表")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int length = ServletRequestUtils.getIntParameter(request, "length", 10);
		int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
		String name = ServletRequestUtils.getStringParameter(request, "name", null);
		int level = ServletRequestUtils.getIntParameter(request, "level", -1);

		RowBounds rowBounds = new RowBounds(start, length);

		Example example = new Example(SysResource.class);
		example.setOrderByClause(" created_date desc ");
		if (!StringUtils.isBlank(name))
			example.createCriteria().andLike("name", "%"+name+"%");
		if (level >= 0)
			example.createCriteria().andEqualTo("level", level);

		example.createCriteria().andEqualTo("isDeleted", false);
		Page<SysResource> res = sysResourceService.selectPage(rowBounds, example);
		res.setDraw(draw);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", res);

		return modelAndView;
	}

	@RequestMapping(value = "remove")
	@Log(module = "菜单管理", method = "删除菜单")
	public @ResponseBody String remove(@RequestParam("id") Long id) {
		if (id == null)
			return new Response(ResponseCode.REQUEST_ERROR).toJson();
		Example example = new Example(SysResource.class);
		example.createCriteria().andEqualTo("id", id);

		List<SysResource> selectByExample = sysResourceService.selectByExample(example);
		if (selectByExample.size() > 0) {
			SysResource sysResource = selectByExample.get(0);
			sysResource.setIsDeleted(true);
			sysResourceService.update(sysResource);
		}
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "create")
	@Log(module = "菜单管理", method = "添加菜单")
	public @ResponseBody String create(@RequestBody SysResource sysResource) {
		boolean exists = sysResourceService.exists(null, sysResource.getResUrl());
		if (exists) {
			return new Response(ResponseCode.URL_EXIST).toJson();
		}

		exists = sysResourceService.exists(sysResource.getResKey(), null);
		if (exists) {
			return new Response(ResponseCode.RES_KEY_EXIST).toJson();
		}

		if (!levelCheck(sysResource)) {
			return new Response(ResponseCode.LEVEL_ERROR).toJson();
		}

		sysResourceService.insert(sysResource);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	private boolean levelCheck(SysResource sysResource) {
		Long parentId = sysResource.getParentId();

		if (parentId != null) {
			SysResource resource = sysResourceService.get(parentId);
			return resource.getLevel() + 1 == sysResource.getLevel();
		} else {
			return sysResource.getLevel() == SysResource.Level.CATALOG.getValue();
		}
	}

	@RequestMapping(value = "edit")
	@Log(module = "菜单管理", method = "编辑菜单")
	public @ResponseBody String edit(@RequestBody SysResource sysResource) {
		SysResource oResource = sysResourceService.get(sysResource.getId());

		boolean exists = false;
		if (!oResource.getResUrl().equals(sysResource.getResUrl())) {
			exists = sysResourceService.exists(null, sysResource.getResUrl());
		}

		if (exists) {
			return new Response(ResponseCode.URL_EXIST).toJson();
		}

		if (!oResource.getResKey().equals(sysResource.getResKey())) {
			exists = sysResourceService.exists(sysResource.getResKey(), null);
		}

		if (exists) {
			return new Response(ResponseCode.RES_KEY_EXIST).toJson();
		}

		if (!levelCheck(sysResource)) {
			return new Response(ResponseCode.LEVEL_ERROR).toJson();
		}

		oResource.setName(sysResource.getName());
		oResource.setParentId(sysResource.getParentId());
		oResource.setResKey(sysResource.getResKey());
		oResource.setResUrl(sysResource.getResUrl());
		oResource.setDescription(sysResource.getDescription());
		oResource.setLevel(sysResource.getLevel());
		oResource.setIcon(sysResource.getIcon());
		oResource.setIsHidden(sysResource.getIsHidden());
		oResource.setSeq(sysResource.getSeq());

		sysResourceService.update(oResource);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "select")
	public @ResponseBody ModelAndView roleSelect() {
		return new ModelAndView("role/resourceSelect");
	}

	@RequestMapping(value = "listSelect")
	@Log(module = "菜单管理", method = "列出菜单（配置）")
	public ModelAndView listForSelect(@RequestParam("roleId") Long roleId) {
		ModelAndView modelAndView = new ModelAndView("role/resourceSelect");

		List<Long> selectedResourceIds = sysResourceService.findResourceIdsByRoleId(roleId);

		Example example = new Example(SysResource.class);
		example.createCriteria().andEqualTo("isDeleted", false);
		List<SysResource> resources = sysResourceService.selectByExample(example);

		TreeView treeView = TreeView.buildResourceTree(resources, selectedResourceIds);
		modelAndView.addObject(treeView);

		return modelAndView;
	}

	@RequestMapping(value = "forCreate")
	@Log(module = "菜单管理", method = "菜单列表（创建）")
	public ModelAndView listForCreate() {

		Example example = new Example(SysResource.class);
		example.setOrderByClause(" level asc ");

		example.createCriteria().andLessThan("level", 2);
		TreeView treeView = TreeView.buildResourceTree(sysResourceService.listByWhere(example), null);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(treeView);
		return modelAndView;
	}
}