package indi.dsb.hardware.product.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.product.entity.Product;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface ProductService extends AbstractService<Product,Long> {

	/**
	 * @Title findList 
	 * @Description 通用列表查询
	 * @param product
	 * @param pageRequest
	 * @return
	 */
    Page<Product> findList(Product product, PageRequest pageRequest);

    /**
     * @Title updateBySystem 
     * @Description 系统更新数据 
     * @param product
     * @return
     */
    int updateBySystem(Product product);
    
    /**
     * @Title getById 
     * @Description 根据id获取
     * @param id
     * @return
     */
    Product getById(Integer id);

	/**
	 * @Title randList 
	 * @Description 随机获取列表
	 * @param product
	 * @param pageRequest
	 * @return
	 */
    List<Product> randList(String keyword,int size);

	List<Map<Object, Object>> getProductTable(Integer productId);
}
