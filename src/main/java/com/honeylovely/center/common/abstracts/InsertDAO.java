package com.honeylovely.center.common.abstracts;

import java.io.Serializable;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface InsertDAO<T, PK extends Serializable> {
    /**
     * 瀛樹竴涓疄浣擄紝null鐨勫睘鎬т細淇濆瓨锛屼笉浣跨敤鏁版嵁搴撻粯璁ゅ��
     * @param t
     * @return
     */
    public int insert(T t);

    /**
     * 瀛樹竴涓疄浣擄紝null鐨勫睘鎬т笉浼氫繚瀛橈紝浼氫娇鐢ㄦ暟鎹簱榛樿鍊�
     * @param t
     * @return
     */
    public int insertSelective(T t);
}