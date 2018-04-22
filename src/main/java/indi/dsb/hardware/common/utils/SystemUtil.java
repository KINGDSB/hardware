package indi.dsb.hardware.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import indi.dsb.hardware.sys.entity.SysUser;

public class SystemUtil {
	private static Properties prop = null;	
	
	private static void PropInit() throws IOException{
		prop = new Properties();
		InputStream in = SystemUtil.class.getResourceAsStream("/application.properties");
		prop.load(in);
	}

	public static String GetProperty(String key) throws IOException{
		if(null == prop){
			PropInit();
		}		
		String res = prop.getProperty(key);
		return res;
	}
	
	public static boolean IsProduction() throws IOException{
		if(null == prop){
			PropInit();
		}		
		String res = prop.getProperty("mode");
		return "PRODUCTION".equals(res);
	}
	
	public static SysUser getUser() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		HttpServletRequest request = sra.getRequest();
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return user;
	}
}
