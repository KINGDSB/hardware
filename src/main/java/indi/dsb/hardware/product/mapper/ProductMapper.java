package indi.dsb.hardware.product.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import indi.dsb.hardware.product.entity.Product;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 2017/12/7.
 */

public interface ProductMapper extends Mapper<Product> {

	/**
	 * @Title findList
	 * @Description 公用列表查询方法
	 * @date 2018-04-22 16:05:16
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
	@Select("<script>"
			+ " SELECT " 
			+ " t1.id, t1.name_cn AS 'nameCn', t1.name_en AS 'nameEn', "
			+ " t1.pic_urls AS 'picUrls', t1.description, "
			+ " t1.amount_old AS 'amountOld', t1.amount, t1.type, "
			+ " t2.id AS 'productType.id', t2.name_cn AS 'productType.nameCn', t2.name_en AS 'productType.nameEn', "
			+ " t1.status, t1.remark, t3.name AS 'createdUser.name', t1.created_date AS 'createdDate' " 
			+ " FROM f_product t1 "
			+ " LEFT JOIN f_product_type t2 ON t2.id = t1.type AND t2.is_deleted = 0 "
			+ " LEFT JOIN sys_user t3 ON t3.id = t1.created_by AND t3.is_deleted = 0 " 
			+ " WHERE t1.is_deleted = 0 "
			+ "<if test='status != null and status > 0'>" 
			+ "	AND t1.status = #{status}" 
			+ "</if>"
			+ "<if test='type != null and type > 0'>" 
			+ " AND t1.type = #{type}" 
			+ "</if>"
			+ "<if test='createdDate != null'>"
			+ "	AND DATE_FORMAT(t1.created_date, '%Y-%m-%d') = DATE_FORMAT(#{createdDate}, '%Y-%m-%d')" 
			+ "</if>"
			+ "<if test='keyword != null'>" 
			+ "	AND t1.description LIKE concat('%', #{keyword}, '%')" 
			+ "</if>"
			+ "<if test='sort == null'>" 
			+ " ORDER BY t1.created_date DESC" 
			+ "</if>" + "<if test='sort != null'>"
			+ " ORDER BY #{sort}" 
			+ "</if>" 
			+ "<if test='startNo != null and pageSize != null'>"
			+ "	LIMIT #{startNo},#{pageSize}" 
			+ "</if>" 
			+ "</script>")
	List<Product> findList(@Param("status") Integer status, @Param("type") Integer type,
			@Param("createdDate") Date createdDate, @Param("keyword") String keyword, @Param("sort") String sort,
			@Param("startNo") Integer startNo, @Param("pageSize") Integer pageSize);

	/**
	 * @Title findListCount
	 * @Description 公用列表查询方法
	 * @date 2018-04-22 16:05:32
	 * @author dsb
	 * @param status
	 * @param type
	 * @param createdDate
	 * @param keyword
	 * @return
	 */
	@Select("<script>" 
			+ " SELECT count(1) FROM f_product t1 "
			+ " LEFT JOIN f_product_type t2 ON t2.id = t1.type AND t2.is_deleted = 0 "
			+ " LEFT JOIN sys_user t3 ON t3.id = t1.created_by AND t3.is_deleted = 0 " 
			+ " WHERE t1.is_deleted = 0 "
			+ "<if test='status != null and status > 0'>" 
			+ "	AND t1.status = #{status}" 
			+ "</if>"
			+ "<if test='type != null and type > 0'>" 
			+ " AND t1.type = #{type}" 
			+ "</if>"
			+ "<if test='createdDate != null'>"
			+ "	AND DATE_FORMAT(t1.created_date, '%Y-%m-%d') = DATE_FORMAT(#{createdDate}, '%Y-%m-%d')" 
			+ "</if>"
			+ "<if test='keyword != null'>" 
			+ "	AND t1.description LIKE concat('%', #{keyword}, '%')" 
			+ "</if>"
			+ "</script>")
	int findListCount(@Param("status") Integer status, @Param("type") Integer type,
			@Param("createdDate") Date createdDate, @Param("keyword") String keyword);
	
    @Select(" SELECT "
        + " t1.id, t1.name_cn AS 'nameCn', t1.name_en AS 'nameEn', "
        + " t1.pic_urls AS 'picUrls', t1.description, "
        + " t1.amount_old AS 'amountOld', t1.amount, t1.type, "
        + " t2.id AS 'productType.id', t2.name_cn AS 'productType.nameCn', t2.name_en AS 'productType.nameEn', "
        + " t1.status, t1.remark, t3.name AS 'createdUser.name', t1.created_date AS 'createdDate' " 
        + " FROM f_product t1 "
        + " LEFT JOIN f_product_type t2 ON t2.id = t1.type AND t2.is_deleted = 0 "
        + " LEFT JOIN sys_user t3 ON t3.id = t1.created_by AND t3.is_deleted = 0 " 
        + " WHERE t1.is_deleted = 0 "
        + " AND t1.id = #{id}")
	Product getById(@Param("id") Integer id);

	/**
	 * <p>Title: randList</p> 
	 * <p>Description: 随机获取列表</p> 
	 * @date 2018-05-04 00:45:15 
	 * @author dsb
	 * @param keyword
	 * @param size
	 * @return
	 */
	@Select("<script>"
			+ " SELECT " 
			+ " t1.id, t1.name_cn AS 'nameCn', t1.name_en AS 'nameEn', "
			+ " t1.pic_urls AS 'picUrls', t1.description, "
			+ " t1.amount_old AS 'amountOld', t1.amount, t1.type, "
			+ " t2.id AS 'productType.id', t2.name_cn AS 'productType.nameCn', t2.name_en AS 'productType.nameEn', "
			+ " t1.status, t1.remark, t3.name AS 'createdUser.name', t1.created_date AS 'createdDate' " 
			+ " FROM f_product t1 "
			+ " LEFT JOIN f_product_type t2 ON t2.id = t1.type AND t2.is_deleted = 0 "
			+ " LEFT JOIN sys_user t3 ON t3.id = t1.created_by AND t3.is_deleted = 0 " 
			+ " WHERE t1.is_deleted = 0 AND t1.status = 2 "
			+ "	AND t1.id >= ((SELECT MAX(id) FROM f_product) - (SELECT MIN(id) FROM f_product)) * RAND() + (SELECT MIN(id) FROM f_product) "
			+ "<if test='keyword != null'>" 
			+ "	AND t1.name_en LIKE concat('%', #{keyword}, '%')" 
			+ "</if>"
			+ "<if test='size != null'>"
			+ "	LIMIT #{size}" 
			+ "</if>" 
			+ "</script>")
	List<Product> randList(@Param("keyword") String keyword, @Param("size") Integer size);
    
}
