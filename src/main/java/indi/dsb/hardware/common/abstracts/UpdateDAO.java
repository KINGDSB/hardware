package indi.dsb.hardware.common.abstracts;

import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface UpdateDAO<T, PK extends Serializable> {
    /**
     * 鏍规嵁涓婚敭鏇存柊瀹炰綋鍏ㄩ儴瀛楁锛宯ull鍊间細琚洿鏂�
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 鏍规嵁Example鏉′欢鏇存柊瀹炰綋`record`鍖呭惈鐨勫叏閮ㄥ睘鎬э紝null鍊间細琚洿鏂�
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(T record, Example example);

    /**
     * 鏍规嵁Example鏉′欢鏇存柊瀹炰綋`record`鍖呭惈鐨勪笉鏄痭ull鐨勫睘鎬у��
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(T record, Example example);

    /**
     * 鏍规嵁涓婚敭鏇存柊灞炴�т笉涓簄ull鐨勫��
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);
}
