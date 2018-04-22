package indi.dsb.hardware.common.abstracts;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.web.context.request.RequestContextHolder;

import indi.dsb.hardware.common.Page;
import indi.dsb.hardware.common.utils.SystemUtil;
import indi.dsb.hardware.sys.entity.SysUser;
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

		if (entity instanceof AbstractEntity) {
			((AbstractEntity) entity).setCreatedDate(new Date());
			((AbstractEntity) entity).setCreatedBy(SystemUtil.getUser().getId()+"");
		}
		if (entity instanceof AbstractEntity) {
			((AbstractEntity) entity).setUpdatedDate(new Date());
			((AbstractEntity) entity).setUpdatedBy(SystemUtil.getUser().getId()+"");
		}
		return getAbstractDAO().insert(entity);
	}

	@Override
	public int update(T entity) {
		if (entity instanceof AbstractEntity) {
			((AbstractEntity) entity).setUpdatedDate(new Date());
			((AbstractEntity) entity).setUpdatedBy(SystemUtil.getUser().getId()+"");
		}
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