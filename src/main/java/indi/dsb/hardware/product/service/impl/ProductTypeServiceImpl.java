package indi.dsb.hardware.product.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.product.dao.ProductTypeDao;
import indi.dsb.hardware.product.entity.ProductType;
import indi.dsb.hardware.product.service.ProductTypeService;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("productTypeService")
public class ProductTypeServiceImpl extends AbstractServiceImpl<ProductType, Long> implements ProductTypeService {

    private static Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public AbstractDAO<ProductType, Long> getAbstractDAO() {
        return productTypeDao;
    }

}
