package indi.dsb.hardware.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import indi.dsb.hardware.common.ApiContant;
import indi.dsb.hardware.common.ResponseCode;
import indi.dsb.hardware.common.utils.DateUtil;
import indi.dsb.hardware.common.utils.PasswordHelper;
import indi.dsb.hardware.common.utils.Response;
import indi.dsb.hardware.sys.entity.SysResource;
import indi.dsb.hardware.sys.entity.SysStaticData;
import indi.dsb.hardware.sys.entity.SysUser;
import indi.dsb.hardware.sys.service.SysResourceService;
import indi.dsb.hardware.sys.service.SysStaticDataService;
import indi.dsb.hardware.sys.service.SysUserService;
import tk.mybatis.mapper.entity.Example;

/**
 * @Title: CommonController.java
 * @Package com.honeylovely.center.common.controller
 * @Description: 公共控制器
 * @author dsb
 * @date 2018年3月21日 上午9:32:41
 */
@Controller
public class CommonController {

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysStaticDataService sysStaticDataService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * @Title uploadFiles
     * @Description 批量上传文件
     * @param files
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public Response uploadFiles(MultipartFile[] files) throws IllegalStateException, IOException {

        if (null != files && files.length > 0) {

            StringBuffer urls = new StringBuffer();
            String nowDateStr = DateUtil.formatDate(new Date());
            // 文件存储路径 需要先创建好 F://files//center 文件夹
            String saveFilePath = ApiContant.SAVE_FILE_PATH + "//" + nowDateStr;
            File saveFolder = new File(saveFilePath);
            if (!saveFolder.exists() && !saveFolder.isDirectory()) {
                saveFolder.mkdir();
            }

            for (MultipartFile file : files) {
                // 原始名称
                String oldFileName = file.getOriginalFilename(); // 获取上传文件的原名

                // 上传图片
                if (file != null && oldFileName != null && oldFileName.length() > 0) {
                    // 新的图片名称
                    String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                    // 新图片
                    File newFile = new File(saveFilePath + "//" + newFileName);
                    // 将内存中的数据写入磁盘
                    file.transferTo(newFile);
                    urls.append("/" + nowDateStr + "/" + newFileName + ",");
                }
            }
            
            urls.deleteCharAt(urls.length() - 1);
            return new Response(ResponseCode.SUCCESS, urls.toString());
        }

        return new Response(ResponseCode.MISS_REQUIRED);
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
       	
    	// 设置 session 失效时间为 五个小时
    	request.getSession().setMaxInactiveInterval(18000);
        Map<String, SysStaticData> staticData = sysStaticDataService.getStaticData();
        request.getSession().setAttribute("staticData", staticData);
        
        ModelAndView modelAndView = new ModelAndView("/index");
        return modelAndView;
    }

    @RequestMapping(value = "/backindex")
    public ModelAndView backindex(HttpServletRequest request, HttpServletResponse response) {
    	if (null != request.getSession().getAttribute("user")) {
            return new ModelAndView("/WEB-INF/jsp/index");
		}
        return new ModelAndView("/WEB-INF/jsp/login");
    }
    
	@RequestMapping(value = "/logout")
	public @ResponseBody String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return new Response(ResponseCode.SUCCESS).toJson();
	}
    
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        String username = ServletRequestUtils.getStringParameter(request, "username", null);
        String password = ServletRequestUtils.getStringParameter(request, "password", null);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new ModelAndView("/WEB-INF/jsp/login");
		}
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("username", username);
        List<SysUser> users = sysUserService.selectByExample(example);
        if (users.size() > 0) {
        	SysUser user = users.get(0);
        	String pass = PasswordHelper.entryptPassword(password, user.getCredentialsSalt());
        	if (pass.equals(user.getPassword())) {
        		List<SysResource> resources = sysResourceService.findResourcesByUserId(user.getId());
        		
        		request.getSession().setAttribute("user", user);
        		request.getSession().setAttribute("resources", resources);
        		
    			ModelAndView modelAndView = new ModelAndView("/WEB-INF/jsp/index");
    	        return modelAndView;
			} else {
				return new ModelAndView("/WEB-INF/jsp/login");
			}
		} else {
			return new ModelAndView("/WEB-INF/jsp/login");
		}
    }
}