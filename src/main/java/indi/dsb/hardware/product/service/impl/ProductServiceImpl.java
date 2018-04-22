package indi.dsb.hardware.product.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.common.abstracts.AbstractServiceImpl;
import indi.dsb.hardware.common.utils.SQLUtils;
import indi.dsb.hardware.product.dao.ProductDao;
import indi.dsb.hardware.product.entity.Product;
import indi.dsb.hardware.product.service.ProductService;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/7.
 */
@Service("productService")
public class ProductServiceImpl extends AbstractServiceImpl<Product,Long> implements ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Override
    public AbstractDAO<Product, Long> getAbstractDAO() {
        return productDao;
    }

    @Override
    public Page<Product> listPage(PageRequest pageRequest, Example example) {
        return productDao.listPage(pageRequest,example);
    }
    
	@Override
	public Page<Product> findList(Product product, PageRequest pageRequest) {
		List<Product> list = productDao.findList(
			product.getStatus(), 
			product.getType(), 
			product.getCreatedDate(), 
			product.getKeyWord(), 
			SQLUtils.getSortString(pageRequest.getSort()),
			pageRequest.getPageNumber(), 
			pageRequest.getPageSize()
		);
		int total = productDao.findListCount(
			product.getStatus(), 
			product.getType(), 
			product.getCreatedDate(), 
			product.getKeyWord());

		if (pageRequest.getPageNumber() > total) {
			return new Page<Product>(0, total, total, Collections.<Product>emptyList());
		}
		return new Page<Product>(0, total, total, list);
	}

    @Override
    public int updateBySystem(Product product) {
        product.setUpdatedDate(new Date());
        product.setUpdatedBy("0");
        return getAbstractDAO().updateByPrimaryKey(product);
    }

}