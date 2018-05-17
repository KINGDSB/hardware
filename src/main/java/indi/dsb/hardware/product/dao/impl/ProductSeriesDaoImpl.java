package indi.dsb.hardware.product.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.product.dao.ProductSeriesDao;
import indi.dsb.hardware.product.entity.ProductSeries;
import indi.dsb.hardware.product.mapper.ProductSeriesMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class ProductSeriesDaoImpl extends AbstractDAOImpl<ProductSeries, Long> implements ProductSeriesDao {

    @Autowired
    private ProductSeriesMapper productSeriesMapper;

    @Override
    public Mapper<ProductSeries> getMapper() {
        return productSeriesMapper;
    }

}
