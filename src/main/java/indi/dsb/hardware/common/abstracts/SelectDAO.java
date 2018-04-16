package indi.dsb.hardware.common.abstracts;

import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 11/17/2016.
 */
public interface SelectDAO<T, PK extends Serializable> {
    /**
     * 鏍规嵁瀹炰綋涓殑灞炴�у�艰繘琛屾煡璇紝鏌ヨ鏉′欢浣跨敤绛夊彿
     *
     * @param record
     * @return
     */
    List<T> select(T record);

    /**
     * 鏍规嵁涓婚敭瀛楁杩涜鏌ヨ锛屾柟娉曞弬鏁板繀椤诲寘鍚畬鏁寸殑涓婚敭灞炴�э紝鏌ヨ鏉′欢浣跨敤绛夊彿
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 鏌ヨ鍏ㄩ儴缁撴灉
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 鏍规嵁Example鏉′欢杩涜鏌ヨ銆�
     * Example example = new Example(Country.class);
     * example.createCriteria().andGreaterThan("id", 100);
     * countryList = countryDAO.selectByExample(example);
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 鏍规嵁example鏉′欢鍜孯owBounds杩涜鍒嗛〉鏌ヨ
     *
     * @param example
     * @param rowBounds
     * @return
     */
    List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds);

    /**
     * 鏍规嵁瀹炰綋灞炴�у拰RowBounds杩涜鍒嗛〉鏌ヨ
     *
     * @param record
     * @param rowBounds
     * @return
     */
    List<T> selectByRowBounds(T record, RowBounds rowBounds);

    /**
     * 鏍规嵁瀹炰綋涓殑灞炴�ф煡璇㈡�绘暟锛屾煡璇㈡潯浠朵娇鐢ㄧ瓑鍙�
     *
     * @param record
     * @return
     */
    int selectCount(T record);

    /**
     * 鏍规嵁Example鏉′欢杩涜鏌ヨ鎬绘暟
     *
     * @param example
     * @return
     */
    int selectCountByExample(Example example);

    /**
     * 鏍规嵁瀹炰綋涓殑灞炴�ц繘琛屾煡璇紝鍙兘鏈変竴涓繑鍥炲�硷紝鏈夊涓粨鏋滄槸鎶涘嚭寮傚父锛屾煡璇㈡潯浠朵娇鐢ㄧ瓑鍙�
     *
     * @param record
     * @return
     */
    T selectOne(T record);
}
