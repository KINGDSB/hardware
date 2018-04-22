package indi.dsb.hardware.product.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.product.dao.ProductTypeDao;
import indi.dsb.hardware.product.entity.ProductType;
import indi.dsb.hardware.product.mapper.ProductTypeMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class ProductTypeDaoImpl extends AbstractDAOImpl<ProductType, Long> implements ProductTypeDao {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public Mapper<ProductType> getMapper() {
        return productTypeMapper;
    }

}
