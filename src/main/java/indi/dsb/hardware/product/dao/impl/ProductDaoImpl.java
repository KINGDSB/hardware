package indi.dsb.hardware.product.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import indi.dsb.hardware.common.abstracts.AbstractDAOImpl;
import indi.dsb.hardware.product.dao.ProductDao;
import indi.dsb.hardware.product.entity.Product;
import indi.dsb.hardware.product.mapper.ProductMapper;
import tk.mybatis.mapper.common.Mapper;


/**
 * Created by Administrator on 2017/12/8.
 */
@Repository
public class ProductDaoImpl extends AbstractDAOImpl<Product,Long> implements ProductDao {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Mapper<Product> getMapper() {
        return productMapper;
    }

	@Override
	public List<Product> findList(		
			Integer status,
			Integer type,
			Date createdDate,
			String keyword,
			String sort,
			Integer startNo,
			Integer pageSize) {
		return productMapper.findList(status, type, createdDate, keyword, sort, startNo, pageSize);
	}

	@Override
	public int findListCount(Integer status, Integer type, Date createdDate, String keyword) {
		return productMapper.findListCount(status, type, createdDate, keyword	);
	}

}
