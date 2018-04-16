package com.honeylovely.center.common.abstracts;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Administrator on 11/17/2016.
 */
abstract public class BaseDAOImpl<T> {
    abstract public Mapper<T> getMapper();
}
