package indi.dsb.hardware.product.service;

import java.util.List;

import indi.dsb.hardware.common.abstracts.AbstractService;
import indi.dsb.hardware.product.entity.ProductProperties;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface ProductPropertiesService extends AbstractService<ProductProperties, Long> {

	/**
	 * <p>Title: getByProductId</p> 
	 * <p>Description: 根据产品id获取</p> 
	 * @date 2018-09-02 19:10:38 
	 * @author dsb
	 * @param productId
	 * @return
	 */
    List<ProductProperties> getByProductId(Integer productId);
	
}
