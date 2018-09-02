package indi.dsb.hardware.product.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.product.dao.ProductPropertiesDao;
import indi.dsb.hardware.product.entity.ProductProperties;
import indi.dsb.hardware.product.mapper.ProductPropertiesMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class ProductPropertiesDaoImpl extends AbstractDAOImpl<ProductProperties, Long> implements ProductPropertiesDao {

    @Autowired
    private ProductPropertiesMapper productPropertiesMapper;

    @Override
    public Mapper<ProductProperties> getMapper() {
        return productPropertiesMapper;
    }

}
