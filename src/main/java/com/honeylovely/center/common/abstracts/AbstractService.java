package com.honeylovely.center.common.abstracts;

import com.honeylovely.center.common.Page;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;


/**
 * Created by Administrator on 10/31/2016.
 */
public interface AbstractService<T, PK extends Serializable> {
    T get(PK id);

    int insert(T t);

    int update(T t);

    int delete(PK id);

    List<T> selectByExample(Example example);

    Page<T> selectPage(RowBounds rowBounds, Example example);
}