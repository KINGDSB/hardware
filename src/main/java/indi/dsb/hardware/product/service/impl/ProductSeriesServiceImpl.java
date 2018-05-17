package indi.dsb.hardware.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.product.dao.ProductSeriesDao;
import indi.dsb.hardware.product.entity.ProductSeries;
import indi.dsb.hardware.product.service.ProductSeriesService;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("productSeriesService")
public class ProductSeriesServiceImpl extends AbstractServiceImpl<ProductSeries, Long> implements ProductSeriesService {

    private static Logger logger = LoggerFactory.getLogger(ProductSeries.class);

    @Autowired
    private ProductSeriesDao productSeriesDao;

    @Override
    public AbstractDAO<ProductSeries, Long> getAbstractDAO() {
        return productSeriesDao;
    }

    @Override
    public List<ProductSeries> selectAll() {
        return productSeriesDao.selectAll();
    }

}
