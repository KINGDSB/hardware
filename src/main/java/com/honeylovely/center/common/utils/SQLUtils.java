package com.honeylovely.center.common.utils;

import java.util.Iterator;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * @Title: SQLUtils.java 
 * @Package com.honeylovely.center.common.utils 
 * @Description: SQL工具类
 * @author dsb
 * @date 2018年3月16日 下午2:40:06
 */
public class SQLUtils {
	
	/**
	 * @Title getSortString 
	 * @Description 获取排序字段
	 * @param sort
	 * @return
	 */
    public static String getSortString(Sort sort) {
    	if (sort == null) {
			return null;
		}

    	StringBuilder sb = new StringBuilder();
		for (Iterator<Order> iterator = sort.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			sb.append(order.getProperty() + " " + order.getDirection() + ",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
        return sb.toString();
    }
}

