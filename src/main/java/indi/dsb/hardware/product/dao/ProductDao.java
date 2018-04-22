package indi.dsb.hardware.product.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.abstracts.AbstractDAO;
import indi.dsb.hardware.product.entity.Product;
import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 2017/12/8.
 */

public interface ProductDao extends AbstractDAO<Product,Long> {
    Page<Product> listPage(PageRequest pageRequest, Example example);
	
	/**
	 * <p>Title: findList</p> 
	 * <p>Description: 公用列表查询方法</p> 
	 * @date 2018-04-22 16:02:03 
	 * @author dsb
	 * @param status
	 * @param type
	 * @param createdDate
	 * @param keyword
	 * @param sort
	 * @param startNo
	 * @param pageSize
	 * @return
	 */
	List<Product> findList(
		Integer status,
		Integer type,
		Date createdDate,
		String keyword,
		String sort,
		Integer startNo,
		Integer pageSize
	);

	/**
	 * <p>Title: findListCount</p> 
	 * <p>Description: 公用列表条数查询方法</p> 
	 * @date 2018-04-22 16:02:23 
	 * @author dsb
	 * @param status
	 * @param type
	 * @param createdDate
	 * @param keyword
	 * @return
	 */
	int findListCount(
		Integer status,
		Integer type,
		Date createdDate,
		String keyword
	);
	
}
