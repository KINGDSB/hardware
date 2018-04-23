package indi.dsb.hardware.sys.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.Log;
import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.abstracts.AbstractController;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.common.utils.PasswordHelper;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.sys.entity.SysResource;
import indi.dsb.hardware.sys.entity.SysRole;
import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.service.SysResourceService;
import indi.dsb.hardware.sys.service.SysRoleService;
import indi.dsb.hardware.sys.service.SysUserService;
import tk.mybatis.mapper.entity.Example;


@Controller
@RequestMapping(value = "user")
public class SysUserController extends AbstractController<SysUser, Long> {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysResourceService sysResourceService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public AbstractService<SysUser, Long> getService() {
		return sysUserService;
	}

	@Override
	public SysResourceService getSysResourceService() {
		return sysResourceService;
	}

	@RequestMapping(value = "/list")
	@Log(module = "用户管理", method = "用户列表")
	public ModelAndView find(HttpServletRequest request, HttpServletResponse response) {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int length = ServletRequestUtils.getIntParameter(request, "length", 10);
		int draw = ServletRequestUtils.getIntParameter(request, "draw", 1);
		String name = ServletRequestUtils.getStringParameter(request, "name", null);
		String username = ServletRequestUtils.getStringParameter(request, "username", null);

		RowBounds rowBounds = new RowBounds(start, length);

		Example example = new Example(SysResource.class);
		if (!StringUtils.isBlank(name))
			example.createCriteria().andLike("name", "%"+name+"%");
		if (!StringUtils.isBlank(username))
			example.createCriteria().andLike("username", "%"+username+"%");

		example.createCriteria().andEqualTo("isDeleted", false);
		Page<SysUser> res = sysUserService.selectPage(rowBounds, example);
		res.setDraw(draw);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", res);

		return modelAndView;
	}

	@RequestMapping(value = "remove")
	@Log(module = "用户管理", method = "删除用户")
	public @ResponseBody
    String remove(@RequestParam("id") Long id) {
		if (id == null)
			return new Response(ResponseCode.REQUEST_ERROR).toJson();
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("id", id);

		List<SysUser> selectByExample = sysUserService.selectByExample(example);
		if (selectByExample.size() > 0) {
			SysUser sysUser = selectByExample.get(0);
			sysUser.setIsDeleted(true);
			sysUserService.update(sysUser);
		}
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "create")
	@Log(module = "用户管理", method = "添加用户")
	public @ResponseBody
    String create(@RequestBody SysUser user) {
		boolean isExists = sysUserService.exist(user.getUsername());

		if (isExists) {
			return new Response(ResponseCode.USERNAME_EXIST).toJson();
		}

		String salt = PasswordHelper.salt();
		user.setCredentialsSalt(salt);
		String password = PasswordHelper.entryptPassword(user.getPassword(), salt);
		user.setPassword(password);
		sysUserService.insert(user);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "edit")
	@Log(module = "用户管理", method = "编辑用户")
	public @ResponseBody
    String edit(@RequestBody SysUser user) {
		Long userId = user.getId();
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("id", userId);
		SysUser oUser = sysUserService.selectByExample(example).get(0);

		oUser.setBirthday(user.getBirthday());
		oUser.setDescription(user.getDescription());
		oUser.setEmail(user.getEmail());
		oUser.setGender(user.getGender());
		oUser.setIsLocked(user.getIsLocked());
		oUser.setMobile(user.getMobile());
		oUser.setName(user.getName());

		sysUserService.update(oUser);
		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "role", method = RequestMethod.POST)
	@Log(module = "用户管理", method = "用户角色配置")
	public @ResponseBody
    String editRole(@RequestParam("userId") Long userId,
                    @RequestParam("roleIds") List<Long> roleIds) {
		List<SysRole> userRoles = sysRoleService.findUserRoles(userId);

		boolean changed = false;
		boolean hasRoles = userRoles != null && userRoles.size() > 0;
		if (hasRoles) {
			if (roleIds == null || roleIds.size() != userRoles.size()) {
				changed = true;
			} else {
				for (int i = 0; i < userRoles.size() && !changed; i++) {
					for (Long roleId : roleIds) {
						if (roleId != userRoles.get(i).getId()) {
							changed = true;
							break;
						}
					}
				}
			}
		} else {
			changed = roleIds != null && roleIds.size() > 0;
		}

		if (changed)
			sysUserService.saveUserRoles(userId, roleIds, hasRoles);

		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "username/check")
	@Log(module = "用户管理", method = "账户名验证")
	public @ResponseBody
    String usernameCheck(@RequestParam("username") String username) {
		return "{\"valid\": " + !sysUserService.exist(username) + "}";
	}

	@RequestMapping(value = "password")
	// @RequiresPermissions("sys:user:pwd")
	@Log(module = "用户管理", method = "密码修改页面")
	public ModelAndView password(HttpServletRequest request) {
		String action = ServletRequestUtils.getStringParameter(request, "action", null);

		ModelAndView modelAndView = new ModelAndView("/WEB-INF/jsp/user/password");
		modelAndView.addObject("action", action);
		return modelAndView;
	}

	@RequestMapping(value = "updatePassword")
	@Log(module = "用户管理", method = "更新密码")
	public @ResponseBody
    String updatePassword(@RequestParam("newPassword") String newPassword,
                          @RequestParam("userId") Long userId) {
		String salt = PasswordHelper.salt();
		String password = PasswordHelper.entryptPassword(newPassword, salt);

		sysUserService.updatePassword(password, salt, userId);

		return new Response(ResponseCode.SUCCESS).toJson();
	}

	@RequestMapping(value = "modifyPassword")
	@Log(module = "用户管理", method = "用户列表")
	public @ResponseBody
    String modifyPassword(@RequestParam("newPassword") String newPassword,
                          @RequestParam("oldPassword") String oldPassword) {
//		MapperRealm.LoginUser loginUser = WebUtils.getLoginUser();
//		SysUser sysUser = sysUserService.get(loginUser.getId());
//
//		boolean pass = PasswordHelper.checkPassword(oldPassword, sysUser.getPassword(), sysUser.getCredentialsSalt());
//
//		if (!pass) {
//			return new Response(ResponseCode.OLD_PASSWORD_ERROR).toJson();
//		}
//
//		String salt = PasswordHelper.salt();
//		String password = PasswordHelper.entryptPassword(newPassword, salt);
//
//		sysUserService.updatePassword(password, salt, sysUser.getId());

		return new Response(ResponseCode.SUCCESS).toJson();
	}

    @RequestMapping(value = "/getSelect")
    @Log(module = "用户管理", method = "获取用户下拉列表")
    public ModelAndView getSelect(HttpServletRequest request, HttpServletResponse response) {

        Example example = new Example(SysResource.class);
        example.createCriteria().andEqualTo("isDeleted", false);
        List<SysUser> users = sysUserService.selectByExample(example);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("option", users);

        return modelAndView;
    }
    
    @RequestMapping(value = "/checkUserIsExist")
    @Log(module = "用户管理", method = "检查用户是否存在")
    public ModelAndView checkUserIsExist(HttpServletRequest request, HttpServletResponse response) {

        Example example = new Example(SysResource.class);
        example.createCriteria().andEqualTo("name", ServletRequestUtils.getStringParameter(request, "name", null));
        List<SysUser> users = sysUserService.selectByExample(example);

        ModelAndView modelAndView = new ModelAndView();
        if (users.size() > 0) {
            modelAndView.addObject("user", users.get(0));
        } else {
            modelAndView.addObject("user", null);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/login")
    @Log(module = "用户管理", method = "登录")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/WEB-INF/jsp/login");

        return modelAndView;
    }

}