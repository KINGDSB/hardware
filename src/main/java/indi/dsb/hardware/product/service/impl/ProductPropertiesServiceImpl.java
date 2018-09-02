package indi.dsb.hardware.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.product.dao.ProductPropertiesDao;
import indi.dsb.hardware.product.entity.ProductProperties;
import indi.dsb.hardware.product.service.ProductPropertiesService;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("productPropertiesService")
public class ProductPropertiesServiceImpl extends AbstractServiceImpl<ProductProperties, Long> implements ProductPropertiesService {

    private static Logger logger = LoggerFactory.getLogger(ProductPropertiesServiceImpl.class);

    @Autowired
    private ProductPropertiesDao productPropertiesDao;

    @Override
    public AbstractDAO<ProductProperties, Long> getAbstractDAO() {
        return productPropertiesDao;
    }

	@Override
	public List<ProductProperties> getByProductId(Integer productId) {
		Example example = new Example(ProductProperties.class);
		example.createCriteria().andEqualTo("productId", productId);
		List<ProductProperties> list = selectByExample(example);
		return list;
	}

}
