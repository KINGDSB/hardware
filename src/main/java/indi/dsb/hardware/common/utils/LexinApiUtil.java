package indi.dsb.hardware.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import indi.dsb.hardware.common.ApiContant;

/**
 * 乐欣api请求工具
 * 
 * @author admin
 *
 */
public class LexinApiUtil {
    
    private static String TOKEN = freshToken();

	private static Logger logger = LoggerFactory.getLogger(LexinApiUtil.class);

	/**
	 * 请求乐欣api
	 * @param urlSuffix 请求地址后缀
	 * @param data 请求参数
	 */
	public static JSONObject postRequest(String urlSuffix, JSONObject data) {
		long beginTime = DateUtil.getNowDate().getTime();
		
		if (null == data) {
			data = new JSONObject();
		}
		data.put("token", TOKEN);

		String requestUrl = ApiContant.BASE_URL + urlSuffix;

		logger.info("======================= Lexin Request begin =======================");
		logger.info("requestUrl：" + requestUrl);
		logger.info("requestData：" + data.toJSONString());
		String result = HttpUtil.postData(requestUrl, data.toJSONString(), "application/json");
		if (StringUtils.isBlank(result)) {
			System.out.println("Lexin 接口异常！！！");
			JSONObject errorResult = new JSONObject();
			errorResult.put("errcode", "-9999"); // 自己内部定义的异常码 防止调用获取errcode时空指针
			errorResult.put("data", new JSONObject());
			return errorResult;
		}
		JSONObject jsonResult = JSON.parseObject(result);
        int errcode = jsonResult.getIntValue("errcode");
        if (40001 == errcode || 42001 == errcode) { // token失效时更新token再试一次
            TOKEN = freshToken();
            data.put("token", TOKEN);
            result = HttpUtil.postData(requestUrl, data.toJSONString(), "application/json");

        }
		logger.info("responseData：" + jsonResult.toJSONString());

		long endTime = DateUtil.getNowDate().getTime();
		logger.info("总耗时：" + (endTime - beginTime) + " ms");
		logger.info("======================= Lexin Request end =======================");

		return jsonResult;
	}

	/**
	 * 获取token 刷新token
	 * @return
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String freshToken() {
		JSONObject data = new JSONObject();
		data.put("app_id", ApiContant.APP_ID);
		data.put("app_secret", ApiContant.APP_SECRET);
		System.out.println("======================= freshToken begin =======================");
		System.out.println("requestData:" + data.toJSONString());
		String result = HttpUtil.postData(ApiContant.BASE_URL + ApiContant.SERVICE_TOKEN, data.toJSONString(),
				"application/json");
		System.out.println("responseData:" + result);
		JSONObject jsonResult = JSON.parseObject(result);
		System.out.println("======================= freshToken end =======================");
		return jsonResult.getString("token");
	}
	
}
