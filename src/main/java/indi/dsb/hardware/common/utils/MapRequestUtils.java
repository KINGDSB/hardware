package indi.dsb.hardware.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MapRequestUtils {
	
	private static final int TRUE = 1;

	public static String getString(Map<String, Object> map, String key,
			String defaultVal) {
		return map.containsKey(key) ? (String)map.get(key) : defaultVal;
	}

	public static Long getLong(Map<String, Object> map, String key, Long defaultVal) throws NumberFormatException {
		if(map.containsKey(key)){
			return Long.valueOf((String) map.get(key));
		} else {
			return defaultVal;
		}
	}
	
	public static Integer getInt(Map<String, Object> map, String key, Integer defaultVal) throws NumberFormatException {
		if(map.containsKey(key)){
			String value = (String) map.get(key);
			if(StringUtils.isNotBlank(value)){
				return Integer.valueOf(value);
			} else {
				return null;
			}
			
		} else {
			return defaultVal;
		}
	}

	public static boolean getBoolean(Map<String, Object> map, String key, Boolean defaultVal) throws NumberFormatException {
		if(map.containsKey(key)){
			String val = (String) map.get(key);
			return Integer.valueOf(val).equals(TRUE) ? true : false;
		} else {
			return defaultVal;
		}
	}
	
	public static Map<String, Object> getFirst(List<Map<String, Object>> mapList){
		if(mapList != null && mapList.size() > 0){
    		return mapList.get(0);
    	}
    	return null;
	}

	public static Date getDate(Map<String, Object> map, String key,
			Date defaultVal) throws Exception {
		if(map.containsKey(key)){
			return DateUtils.parseDate((String) map.get(key), "yyyy-MM-dd HH:mm:ss");
		} else {
			return defaultVal;
		}
	}

	public static Double getDouble(Map<String, Object> map, String key,
			Double defaultVal) {
		if(map.containsKey(key)){
			//return Double.valueOf((String) map.get(key));
			String value = (String) map.get(key);
			if(StringUtils.isNotBlank(value)){
				return Double.valueOf(value);
			} else {
				return null;
			}
		} else {
			return defaultVal;
		}
	}

	public static String[] getStringArray(Map<String, Object> map, String key, String[] defaultVal){
		if (map.containsKey(key)) {
			String value = map.get(key)+"";
			if(value.indexOf(",") != -1){
				value = value.substring(1, value.length() - 1);
				String replace = value.replace(" ", "");
				return  replace.split(",");
			}else{
				return  new String[]{value};
			}
		}
		return defaultVal;
	}

}
