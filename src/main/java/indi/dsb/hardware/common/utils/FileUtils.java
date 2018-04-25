package indi.dsb.hardware.common.utils;

import org.apache.commons.lang.StringUtils;

import indi.dsb.hardware.common.ApiContant;

/**
 * @Title: FileUtils.java 
 * @Package com.honeylovely.center.common.utils 
 * @Description: 文件工具类
 * @author dsb
 * @date 2018年4月19日 下午5:34:52
 */
public class FileUtils {

    /**
     * @Title getFileUrls 
     * @Description 项目相对路径改为带ip的
     * @param urls
     * @return
     */
    public static String getFileUrls(String urls){
        if (StringUtils.isNotBlank(urls)) {
            StringBuilder sbUrls = new StringBuilder();
            for (String url : urls.split(",")) {
                sbUrls.append(ApiContant.READ_FILE_PATH).append(url).append(",");
            }
            sbUrls.deleteCharAt(sbUrls.length() - 1);
            return sbUrls.toString();
        }
        return null;
    }
    
}
