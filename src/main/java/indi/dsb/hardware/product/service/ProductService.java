package indi.dsb.hardware.product.service;


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
     * @Description 系统更新数据 为了解决定时任务中没有用户登录 因此获取修改人 报错
     * @param product
     * @return
     */
    int updateBySystem(Product product);
    
}
