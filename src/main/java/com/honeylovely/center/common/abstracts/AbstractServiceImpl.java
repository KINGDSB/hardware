package com.honeylovely.center.common.abstracts;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.honeylovely.center.common.Page;
import org.apache.ibatis.session.RowBounds;


import tk.mybatis.mapper.entity.Example;

/**
 * Created by Administrator on 10/31/2016.
 */
abstract public class AbstractServiceImpl<T, PK extends Serializable> implements AbstractService<T, PK> {
	@Override
	public T get(PK id) {
		return getAbstractDAO().selectByPrimaryKey(id);
	}

	@Override
	public int insert(T entity) {
//		if (entity instanceof AbstractEntity) {
//			((AbstractEntity) entity).setCreatedDate(new Date());
//			((AbstractEntity) entity).setCreatedBy(WebUtils.getLoginUser().getId()+"");
//		}
//		if (entity instanceof AbstractEntity) {
//			((AbstractEntity) entity).setUpdatedDate(new Date());
//			((AbstractEntity) entity).setUpdatedBy(WebUtils.getLoginUser().getId()+"");
//		}
		return getAbstractDAO().insert(entity);
	}

	@Override
	public int update(T entity) {
//		if (entity instanceof AbstractEntity) {
//			((AbstractEntity) entity).setUpdatedDate(new Date());
//			((AbstractEntity) entity).setUpdatedBy(WebUtils.getLoginUser().getId()+"");
//		}
//		if (entity instanceof AbstractEntity) {
//			((AbstractEntity) entity).setUpdatedDate(new Date());
//			((AbstractEntity) entity).setUpdatedBy(WebUtils.getLoginUser().getUsername());
//		}
		return getAbstractDAO().updateByPrimaryKey(entity);
	}

	@Override
	public int delete(PK id) {
		return getAbstractDAO().deleteByPrimaryKey(id);
	}

	@Override
	public List<T> selectByExample(Example example) {
		return getAbstractDAO().selectByExample(example);
	}

	@Override
	public Page<T> selectPage(RowBounds rowBounds, Example example) {
		List<T> list = getAbstractDAO().selectByExampleAndRowBounds(example, rowBounds);
		int total = getAbstractDAO().selectCountByExample(example);
		int start = rowBounds.getOffset();

		if (start > total) {
			return new Page<T>(0, total, total, Collections.<T>emptyList());
		}
		return new Page<T>(0, total, total, list);
	}

	abstract public AbstractDAO<T, PK> getAbstractDAO();
}